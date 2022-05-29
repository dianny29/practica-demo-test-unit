package com.dianny.practicademo.app.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.dianny.practicademo.app.entity.Categoria;

public interface CategoriaService {
	
	ResponseEntity<?> get();
	
	ResponseEntity<?> getById(Long id);
	
	ResponseEntity<?> post(Categoria categoria);
	
	ResponseEntity<?> put(Long id, Categoria categoria);
	
	ResponseEntity<?> patch(Long id, Map<Object, Object> fields);
	
	ResponseEntity<?> delete(Long id);
}
