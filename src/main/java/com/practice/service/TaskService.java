package com.practice.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.practice.entities.TaskEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.practice.entities.*;

@Service
public class TaskService {

	private ArrayList<TaskEntity> tasks=new ArrayList<>();
	private int taskId=1;
	private final SimpleDateFormat deadLineFormatter= new SimpleDateFormat("yyyy-MM-dd");
	
	public TaskEntity addTask(String title,String description,String deadline) throws ParseException {
		TaskEntity task = new TaskEntity();
		task.setId(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setDeadline(deadLineFormatter.parse(deadline));
		task.setCompleted(false);
		tasks.add(task);
		taskId++;
		return task;
	}
	
	public ArrayList<TaskEntity> getTasks(){
		return tasks;
	}
	public TaskEntity getTaskById(int id) {
		for(TaskEntity task : tasks) {
		if(task.getId()==id) {
			return task;
		}
	}
		return null;
}
	public TaskEntity updateTask( int id,String description,String deadline,Boolean completed) throws ParseException{
		TaskEntity task =getTaskById(id);
		if(task==null) {
			return null;
		}
		if(description!=null) {
			task.setDescription(description);
		}
		if(deadline!=null) {
			task.setDeadline(deadLineFormatter.parse(deadline));
			
		}
		if(completed!=null) {
			task.setCompleted(completed);
		}
		return task;
	}
}
