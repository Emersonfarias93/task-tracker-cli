package br.com.esdevcode;

import br.com.esdevcode.model.Task;
import br.com.esdevcode.model.TaskStatus;
import br.com.esdevcode.service.TaskService;

import java.util.Arrays;
import java.util.List;

public class TaskCliApplication {

    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                showHelp();
                return;
            }

            String command = args[0];

            switch (command) {
                case "add" -> handleAdd(args);
                case "update" -> handleUpdate(args);
                case "delete" -> handleDelete(args);
                case "mark-in-progress" -> handleMarkInProgress(args);
                case "mark-done" -> handleMarkDone(args);
                case "list" -> handleList(args);
                default -> {
                    System.out.println("Comando invalido: " + command);
                    showHelp();
                }
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void handleAdd(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso correto: task-cli add \"Descricao da tarefa\"");
            return;
        }

        String description = args[1];
        Task task = taskService.add(description);

        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    private static void handleUpdate(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso correto: task-cli update <id> \"Nova descricao\"");
            return;
        }

        int id = parseId(args[1]);
        String description = args[2];
        taskService.update(id, description);

        System.out.println("Task updated successfully (ID: " + id + ")");
    }

    private static void handleDelete(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso correto: task-cli delete <id>");
            return;
        }

        int id = parseId(args[1]);
        taskService.delete(id);

        System.out.println("Task deleted successfully (ID: " + id + ")");
    }

    private static void handleMarkInProgress(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso correto: task-cli mark-in-progress <id>");
            return;
        }

        int id = parseId(args[1]);
        taskService.markInProgress(id);

        System.out.println("Task marked as in progress (ID: " + id + ")");
    }

    private static void handleMarkDone(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso correto: task-cli mark-done <id>");
            return;
        }

        int id = parseId(args[1]);
        taskService.markDone(id);

        System.out.println("Task marked as done (ID: " + id + ")");
    }

    private static void handleList(String[] args) {
        if (args.length > 2) {
            System.out.println("Uso correto: task-cli list <todo|in-progress|done>");
            return;
        }

        if (args.length == 1) {
            listTasks();
            return;
        }

        String status = args[1];
        listTasks(taskService.listByStatus(TaskStatus.fromValue(status)));
    }

    private static void listTasks() {
        listTasks(taskService.list());
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static int parseId(String value) {
        try {
            int id = Integer.parseInt(value);
            if (id <= 0) {
                throw new IllegalArgumentException("O ID deve ser maior que zero.");
            }

            return id;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID invalido: " + value);
        }
    }

    private static void showHelp() {
        System.out.println("Task Tracker CLI");
        System.out.println();
        System.out.println("Comandos disponiveis:");
        System.out.println("  add \"Descricao da tarefa\"");
        System.out.println("  update <id> \"Nova descricao\"");
        System.out.println("  delete <id>");
        System.out.println("  mark-in-progress <id>");
        System.out.println("  mark-done <id>");
        System.out.println("  list");
        System.out.println("  list <todo|in-progress|done>");
    }
}