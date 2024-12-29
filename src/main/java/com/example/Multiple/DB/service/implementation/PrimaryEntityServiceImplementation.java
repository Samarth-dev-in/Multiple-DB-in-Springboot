package com.example.Multiple.DB.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Multiple.DB.primary.entity.PrimaryEntity;
import com.example.Multiple.DB.primary.repository.PrimaryRepository;
import com.example.Multiple.DB.service.PrimaryEntityService;

@Service
public class PrimaryEntityServiceImplementation implements PrimaryEntityService {

	@Autowired
	private PrimaryRepository primaryRepository;

	@Override
	public void saveRecord(PrimaryEntity primaryEntity) {
		this.primaryRepository.save(primaryEntity);
	}

}
