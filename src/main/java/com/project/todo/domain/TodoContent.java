package com.project.todo.domain;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TodoContent {

	private int id;
	private int userId;
	private String todoDay;
	private int dayId;
	private String content;
	@Column(columnDefinition = "TINYINT(1)")
	private boolean checkboxYn;
	private Date createdAt;
	private Date updatedAt;
	
}
