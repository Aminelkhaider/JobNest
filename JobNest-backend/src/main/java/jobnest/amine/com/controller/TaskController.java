package jobnest.amine.com.controller;


import jakarta.validation.Valid;
import jobnest.amine.com.dto.ApiResponse;
import jobnest.amine.com.model.Task;
import jobnest.amine.com.service.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<ApiResponse> createTask(@Valid @RequestBody Task task, @PathVariable("id") Long userId) {
        return new ResponseEntity<>(service.createTask(task, userId), HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable Integer taskId) {
        return new ResponseEntity<>(service.getTaskById(taskId), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(service.getAllTasks(userId), HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return service.getAll();
    }
        
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Integer id, @Valid @RequestBody Task task) {
        return new ResponseEntity<>(service.updateTask(task, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
        service.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PatchMapping("/{id}/task-done")
    public ResponseEntity<ApiResponse> completedTodo(@PathVariable Integer id) {
        return  ResponseEntity.ok(service.doneTask(id));
    }

    @PatchMapping("/{id}/task-pending")
    public ResponseEntity<ApiResponse> inCompletedTodo(@PathVariable Integer id){
        return ResponseEntity.ok(service.pendingTask(id));
    }
}
