package jobnest.amine.com.service.impl;

import org.springframework.stereotype.Service;

import jobnest.amine.com.dto.ApiResponse;
import jobnest.amine.com.exception.ResourceNotFoundException;
import jobnest.amine.com.model.Task;
import jobnest.amine.com.model.User;
import jobnest.amine.com.repository.TaskRepository;
import jobnest.amine.com.repository.UserRepository;
import jobnest.amine.com.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public ApiResponse createTask(Task task, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found, Id: " + userId));
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        return new ApiResponse("Task Saved", savedTask);
    }

    @Override
    public ApiResponse getTaskById(Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task not found, Id: " + taskId));
        return new ApiResponse("Found task", task);
    }

    @Override
    public List<Task> getAllTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found, Id" + userId));
        List<Task> taskList = taskRepository.findAllByUserId(user.getId());
        return taskList;
    }
    
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
    

    @Override
    public ApiResponse updateTask(Task task, Integer id) {
        Task foundTask = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found, Id" + id));
        foundTask.setTask(task.getTask());
        foundTask.setCompleted(task.getCompleted()); 
        foundTask.setAssignedUser(task.getAssignedUser());
        foundTask.setDeadline(task.getDeadline());
        Task updatedTask = taskRepository.save(foundTask);
        return new ApiResponse("Task updated!",updatedTask);
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found, Id" + id));
        taskRepository.delete(task);
    }

    @Override
    public ApiResponse doneTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task not found, Id" +id));
        task.setCompleted(true);
        
        return new ApiResponse("Task done", taskRepository.save(task));
    }

    @Override
    public ApiResponse pendingTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + id));
        task.setCompleted(false);
        return new ApiResponse("Task pending", taskRepository.save(task));
    }
}
