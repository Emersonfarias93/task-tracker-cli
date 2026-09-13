package br.com.esdevcode.repository;

import br.com.esdevcode.model.Task;
import br.com.esdevcode.model.TaskStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskRepository {

    private static final Path FILE_PATH = Path.of("tasks.json");
    private static final Pattern OBJECT_PATTERN = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);

    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        tasks.addAll(load());
    }

    public void save(Task task) {
        tasks.add(task);
        write();
    }

    public void saveAll(List<Task> updatedTasks) {
        tasks.clear();
        tasks.addAll(updatedTasks);
        write();
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    private List<Task> load() {
        try {
            if (Files.notExists(FILE_PATH)) {
                Files.writeString(FILE_PATH, "[]");
                return new ArrayList<>();
            }

            String json = Files.readString(FILE_PATH).trim();
            if (json.isEmpty() || json.equals("[]")) {
                return new ArrayList<>();
            }

            return parseTasks(json);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao ler o arquivo tasks.json", e);
        }
    }

    private List<Task> parseTasks(String json) {
        List<Task> parsedTasks = new ArrayList<>();
        Matcher matcher = OBJECT_PATTERN.matcher(json);

        while (matcher.find()) {
            String object = matcher.group(1);

            int id = Integer.parseInt(extractValue(object, "id"));
            String description = unescape(extractValue(object, "description"));
            TaskStatus status = TaskStatus.fromValue(extractValue(object, "status"));
            LocalDateTime createdAt = LocalDateTime.parse(extractValue(object, "createdAt"));
            LocalDateTime updatedAt = LocalDateTime.parse(extractValue(object, "updatedAt"));

            parsedTasks.add(new Task(id, description, status, createdAt, updatedAt));
        }

        return parsedTasks;
    }

    private String extractValue(String object, String fieldName) {
        Pattern pattern = Pattern.compile("\"" + fieldName + "\"\\s*:\\s*(\"((?:\\\\.|[^\"])*)\"|\\d+)");
        Matcher matcher = pattern.matcher(object);

        if (!matcher.find()) {
            throw new IllegalStateException("Campo nao encontrado no JSON: " + fieldName);
        }

        String value = matcher.group(1);
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }

        return value;
    }

    private void write() {
        try {
            Files.writeString(FILE_PATH, toJson());
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao gravar o arquivo tasks.json", e);
        }
    }

    private String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            json.append("  {\n");
            json.append("    \"id\": ").append(task.getId()).append(",\n");
            json.append("    \"description\": \"").append(escape(task.getDescription())).append("\",\n");
            json.append("    \"status\": \"").append(task.getStatus().getValue()).append("\",\n");
            json.append("    \"createdAt\": \"").append(task.getCreatedAt()).append("\",\n");
            json.append("    \"updatedAt\": \"").append(task.getUpdatedAt()).append("\"\n");
            json.append("  }");

            if (i < tasks.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }

        json.append("]\n");
        return json.toString();
    }

    private String escape(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private String unescape(String value) {
        return value
                .replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }
}
