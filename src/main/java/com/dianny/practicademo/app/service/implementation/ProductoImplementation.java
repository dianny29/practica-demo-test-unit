package com.dianny.practicademo.app.service.implementation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.dianny.practicademo.app.entity.Producto;
import com.dianny.practicademo.app.repository.ProductoRepository;
import com.dianny.practicademo.app.service.ProductoService;


@Service
public class ProductoImplementation implements ProductoService{
	@Autowired
	ProductoRepository repository;
	
	@Override
	public ResponseEntity<?> get(){
        try {
            ArrayList<Producto> data = (ArrayList<Producto>) repository.findAll();            
            return new ResponseEntity<>(data, HttpStatus.OK);          
        }catch (Exception e){
            System.err.println("ERROR: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }		
	}
	
    @Override	
    public ResponseEntity<?> getById(Long id) {
        if (id != null){
        	Producto producto = repository.findById(id).orElse(null);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }    
    
    @Override
    public ResponseEntity<?> post(Producto producto) {
    	Producto productodb = repository.save(producto);
        return new ResponseEntity<>(productodb, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> put(Long id, Producto producto) {
    	producto.setId_producto(id);
    	Producto productodb = repository.save(producto);
        return new ResponseEntity<>(productodb, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> patch(Long id, Map<Object, Object> fields) {
    	Producto productodb = repository.findById(id).orElse(null);
        fields.forEach((key, value) -> {
            Field f = ReflectionUtils.findField(Producto.class, (String) key);
            if (f != null) {
                f.setAccessible(true);
                ReflectionUtils.setField(f, productodb, value);
            }
        });
        Producto response = repository.save(productodb);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> delete(Long id) {
    	Producto p = new Producto();
        p.setId_producto(id);
        repository.delete(p);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
