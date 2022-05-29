package com.dianny.practicademo.app.service.implementation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.dianny.practicademo.app.entity.Categoria;
import com.dianny.practicademo.app.repository.CategoriaRepository;
import com.dianny.practicademo.app.service.CategoriaService;

@Service
public class CategoriaImplementation implements CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	@Override
	public ResponseEntity<?> get(){
        try {
            ArrayList<Categoria> data = (ArrayList<Categoria>) repository.findAll();            
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch (Exception e){
            System.err.println("ERROR: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }		
	}
	
	@Override	
    public ResponseEntity<?> getById(Long id) {
        if (id != null){
        	Categoria categoria = repository.findById(id).orElse(null);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }    
    
    @Override
    public ResponseEntity<?> post(Categoria categoria) {
    	Categoria categoriadb = repository.save(categoria);
        return new ResponseEntity<>(categoriadb, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> put(Long id, Categoria categoria) {
    	categoria.setId_categoria(id);
    	Categoria categoriadb = repository.save(categoria);
        return new ResponseEntity<>(categoriadb, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> patch(Long id, Map<Object, Object> fields) {
    	Categoria categoriadb = repository.findById(id).orElse(null);
        fields.forEach((key, value) -> {
            Field f = ReflectionUtils.findField(Categoria.class, (String) key);
            if (f != null) {
                f.setAccessible(true);
                ReflectionUtils.setField(f, categoriadb, value);
            }
        });
        Categoria response = repository.save(categoriadb);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> delete(Long id) {
    	Categoria c = new Categoria();
        c.setId_categoria(id);
        repository.delete(c);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
