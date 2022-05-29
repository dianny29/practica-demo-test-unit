package com.dianny.practicademo.app.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;

import com.dianny.practicademo.app.entity.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Serializable>{

}
