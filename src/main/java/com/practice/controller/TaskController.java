package com.practice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.service.TaskService;
import com.practice.dtos.CreateTaskDto;
import com.practice.entities.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService taskService;
	public TaskController(TaskService taskService) {
		this.taskService= taskService;
	}
	@GetMapping("/")
	public ResponseEntity<List<TaskEntity>> getTasks(){
		var tasks =taskService.getTasks();
		return ResponseEntity.ok(tasks);
	}
	@GetMapping("/{id}")
	public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
		var task = taskService.getTaskById(id);
		if(task==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}
	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body){
		var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadLine());
		
		return ResponseEntity.ok(task);
		
	}
}
