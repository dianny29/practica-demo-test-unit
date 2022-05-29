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

import com.dianny.practicademo.app.entity.Producto;
import com.dianny.practicademo.app.service.ProductoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	ProductoService service;
	
	//all	
	@GetMapping()
	@ApiOperation(value = "Obtiene la lista de todos los productos", notes = "")
	public ResponseEntity<?> producto(){
		return service.get();
	}
	
	//id
	@GetMapping(value = "/{id}", produces = "application/json")
	@ApiOperation(value = "Consulta un producto de la lista de productos", notes = "")
	public ResponseEntity<?> producto(@PathVariable(name = "id") final Long id){
		return service.getById(id);
	}
	
	//new
    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Agrega un nuevo producto", notes = "")
    public ResponseEntity<?> producto(@RequestBody final Producto entity) {
        return service.post(entity);
    }
    
    //update
    @PutMapping(value = "/{id}",produces = "application/json")
    @ApiOperation(value = "Actualiza un producto", notes = "Actualiza todos los campos")
    public ResponseEntity<?> producto(@PathVariable(name = "id") final Long id, @RequestBody final Producto entity) {
        return service.put(id, entity);
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Actualiza parcialmente la información de un producto", notes = "Permite actualizar solo unos campos del producto")
    public ResponseEntity<?> productos(@PathVariable(name = "id") final Long id, @RequestBody final Map<Object, Object> fields) {
        return service.patch(id, fields);
    }

    @DeleteMapping(value = "/{id}",  produces = "application/json")
    @ApiOperation(value = "Elimina un producto", notes = "")
    public ResponseEntity<?> productos(@PathVariable(name = "id") final Long id) {
        return service.delete(id);
    }
	
	
}
