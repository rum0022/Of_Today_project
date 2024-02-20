package com.project.comment.domain;

import com.project.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView {

	private Comment comment;
	private UserEntity user;
}
