package com.example.Multiple.DB.service;

public interface LogService {

	void logInfo(String message);

	void logError(String errorMessage, String stackTrace);

}
