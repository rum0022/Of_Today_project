package com.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.entity.UserEntity;
import com.project.user.repository.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	public UserEntity getUserLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	public Integer addUser(String loginId, String password, String name, String email) {
		UserEntity userEntity = userRepository.save(
					UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.build()
				);
		return 	userEntity == null ? null : userEntity.getId();	
	}
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
	
	// commentView필요
	public UserEntity getUserEntityByUserId(int id) {
		return userRepository.findById(id).orElse(null);
	}
}
