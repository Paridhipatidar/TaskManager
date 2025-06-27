 package com.practice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.service.TaskService;
import com.practice.dtos.CreateTaskDto;
import com.practice.dtos.ErrorResponseDto;
import com.practice.dtos.UpdateDto;
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
	@PatchMapping("/{id}")
	public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id,@RequestBody UpdateDto body) throws ParseException{
		var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
		if(task==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}
	
	@PostMapping("")
	public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDto body) throws ParseException{
		var task = taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
		
		return ResponseEntity.ok(task);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleErrors(Exception e){
		if(e instanceof ParseException) {
			return ResponseEntity.badRequest().body(new ErrorResponseDto("invalid date formate"));
		}
		e.printStackTrace();
		return ResponseEntity.internalServerError().body(new ErrorResponseDto("Internal server error"));
		
	}
}
