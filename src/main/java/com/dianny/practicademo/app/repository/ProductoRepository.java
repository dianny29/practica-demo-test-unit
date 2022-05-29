package com.dianny.practicademo.app.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

import com.dianny.practicademo.app.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Serializable>{

}
