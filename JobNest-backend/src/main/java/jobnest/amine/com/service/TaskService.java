package jobnest.amine.com.service;

import java.util.List;

import jobnest.amine.com.dto.ApiResponse;
import jobnest.amine.com.model.Task;

public interface TaskService {
    ApiResponse createTask(Task task, Long userId);

    ApiResponse getTaskById(Integer taskId);

    List<Task> getAllTasks(Long userId);

    ApiResponse updateTask(Task task, Integer id);

    public void deleteTask(Integer id);

    ApiResponse doneTask(Integer id);

    ApiResponse pendingTask(Integer id);
    
    List<Task> getAll();
}