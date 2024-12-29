package com.example.Multiple.DB.service.implementation;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Multiple.DB.secondary.entity.ErrorLog;
import com.example.Multiple.DB.secondary.entity.InfoLog;
import com.example.Multiple.DB.secondary.repository.ErrorLogRepository;
import com.example.Multiple.DB.secondary.repository.InfoLogRepository;
import com.example.Multiple.DB.service.LogService;

@Service
public class LogServiceImplementation implements LogService {

	@Autowired
	private InfoLogRepository infoLogRepository;

	@Autowired
	private ErrorLogRepository errorLogRepository;

	@Override
	public void logInfo(String message) {
		InfoLog infoLog = new InfoLog();
		infoLog.setInfoMessage(message);
		infoLog.setTimestamp(LocalDateTime.now());

		this.infoLogRepository.save(infoLog);
	}

	public void logError(String errorMessage, String stackTrace) {
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorMessage(errorMessage);
		errorLog.setStackTrace(stackTrace);
		errorLog.setTimestamp(LocalDateTime.now());

		this.errorLogRepository.save(errorLog);
	}

}
