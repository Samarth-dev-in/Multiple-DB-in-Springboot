package com.example.Multiple.DB.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Multiple.DB.primary.entity.PrimaryEntity;

public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {
}