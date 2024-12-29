package com.example.Multiple.DB.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Multiple.DB.secondary.entity.ErrorLog;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
