package com.project.diary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer>{

	public List<DiaryEntity> findAllByOrderByIdDesc();
}
