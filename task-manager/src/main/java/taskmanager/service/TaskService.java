package taskmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskmanager.model.Task;
import taskmanager.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getOverdueTasks() {
        return taskRepository.findByDeadlineBefore(LocalDateTime.now());
    }

    public List<Task> getTasksByCategory(String category) {
        return taskRepository.findByCategory(category);
    }

    public List<Task> searchTasksByDescription(String keyword) {
        return taskRepository.searchByDescription(keyword);
    }

    public List<Task> searchTasksBySubtaskName(String keyword) {
        return taskRepository.searchBySubtaskName(keyword);
    }

    public List<Task> getSubtasksByCategory(String category) {
        return taskRepository.findSubtasksByCategory(category);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
