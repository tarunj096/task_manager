package com.spring.task_manager.controller;

import com.spring.task_manager.dto.CreateTaskDTO;
import com.spring.task_manager.entities.TaskEntity;
import com.spring.task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        var tasks = taskService.getTasks();
        return  ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task==null){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO taskDTO){
        var task = taskService.addTask(taskDTO.getTitle(),taskDTO.getDescription(), taskDTO.getDeadline());
        return ResponseEntity.ok(task);
    }

}
