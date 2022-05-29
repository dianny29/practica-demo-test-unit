package com.dianny.practicademo.app.service;

import java.util.Map;
import org.springframework.http.ResponseEntity;

import com.dianny.practicademo.app.entity.Producto;

public interface ProductoService {
	ResponseEntity<?> get();
	
	ResponseEntity<?> getById(Long id);
	
	ResponseEntity<?> post(Producto producto);
	
	ResponseEntity<?> put(Long id, Producto producto);
	
	ResponseEntity<?> patch(Long id, Map<Object, Object> fields);
	
	ResponseEntity<?> delete(Long id);
}
