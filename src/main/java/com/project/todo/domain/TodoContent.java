package com.project.todo.domain;

import java.util.Date;

public class TodoContent {

	private int id;
	private int userId;
	private String todoDay;
	private int dayId;
	private String content;
	private boolean checkboxYn;
	private Date createdAt;
	private Date updatedAt;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTodoDay() {
		return todoDay;
	}
	public void setTodoDay(String todoDay) {
		this.todoDay = todoDay;
	}
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isCheckboxYn() {
		return checkboxYn;
	}
	public void setCheckboxYn(boolean checkboxYn) {
		this.checkboxYn = checkboxYn;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
