package com.dianny.practicademo.app.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dianny.practicademo.app.entity.Categoria;
import com.dianny.practicademo.app.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RequestMapping("/categorias")
public class CategoriaController {	
	@Autowired
	CategoriaService service;
	
	//all	
	@GetMapping(produces = "application/json")
	@ApiOperation(value = "Obtiene la lista de todas las categorias", notes = "")
	public ResponseEntity<?> categorias(){
		return service.get();
	}
	
	//id
	@GetMapping(value = "/{id}", produces = "application/json")
	@ApiOperation(value = "Consulta una categoría de la lista de productos", notes = "")
	public ResponseEntity<?> categoria(@PathVariable(name = "id") final Long id){
		return service.getById(id);
	}
	
	//new
    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Agrega una nueva categoría", notes = "")
    public ResponseEntity<?> categoria(@RequestBody final Categoria entity) {
        return service.post(entity);
    }
    
    //update
    @PutMapping(value = "/{id}",produces = "application/json")
    @ApiOperation(value = "Actualiza todos los valores de una nueva categoría", notes = "")
    public ResponseEntity<?> categoria(@PathVariable(name = "id") final Long id, @RequestBody final Categoria entity) {
        return service.put(id, entity);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Actualiza parcialmente  los valores de una nueva categoría", notes = "Solo los que se necesitan")
    public ResponseEntity<?> categorias(@PathVariable(name = "id") final Long id, @RequestBody final Map<Object, Object> fields) {
        return service.patch(id, fields);
    }

    @DeleteMapping(value = "/{id}",  produces = "application/json")
    @ApiOperation(value = "Elimina una categoría", notes = "Solo los que se necesitan")
    public ResponseEntity<?> categorias(@PathVariable(name = "id") final Long id) {
        return service.delete(id);
    }
        
}
