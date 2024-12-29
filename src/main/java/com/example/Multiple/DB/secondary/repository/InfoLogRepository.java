package com.example.Multiple.DB.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Multiple.DB.secondary.entity.InfoLog;

public interface InfoLogRepository extends JpaRepository<InfoLog, Long> {
	
}
