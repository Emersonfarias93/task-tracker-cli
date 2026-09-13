package br.com.esdevcode.service;

import br.com.esdevcode.model.Task;
import br.com.esdevcode.model.TaskStatus;
import br.com.esdevcode.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository = new TaskRepository();
    private int nextId = 1;

    public TaskService() {
        this.nextId = findNextId();
    }

    public Task add(String description) {
        validateDescription(description);

        Task task = new Task(nextId, description);
        taskRepository.save(task);
        nextId++;
        return task;
    }

    public List<Task> list() {
        return taskRepository.findAll();
    }

    public List<Task> listByStatus(TaskStatus status) {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public void update(int id, String description) {
        validateDescription(description);

        List<Task> tasks = taskRepository.findAll();
        Task task = findById(tasks, id);
        task.setDescription(description);
        taskRepository.saveAll(tasks);
    }

    public void delete(int id) {
        List<Task> tasks = taskRepository.findAll();
        Task task = findById(tasks, id);
        tasks.remove(task);
        taskRepository.saveAll(tasks);
    }

    public void markInProgress(int id) {
        updateStatus(id, TaskStatus.IN_PROGRESS);
    }

    public void markDone(int id) {
        updateStatus(id, TaskStatus.DONE);
    }

    private void updateStatus(int id, TaskStatus status) {
        List<Task> tasks = taskRepository.findAll();
        Task task = findById(tasks, id);
        task.setStatus(status);
        taskRepository.saveAll(tasks);
    }

    private Task findById(List<Task> tasks, int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tarefa nao encontrada com ID: " + id));
    }

    private void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("A descricao da tarefa nao pode ser vazia.");
        }
    }

    private int findNextId() {
        return taskRepository.findAll()
                .stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }
}
