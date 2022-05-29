package com.dianny.practicademo.service.implementtion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.dianny.practicademo.app.entity.Categoria;
import com.dianny.practicademo.app.service.CategoriaService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoriaImplementationTest {
	
	@Autowired
	CategoriaService service;	
	
	ArrayList<Categoria> data;
	
	@InjectMocks
	Categoria c = new Categoria();
	
	Map<Object, Object> field = new HashMap<>(); 		

	@Before
	void setUp() throws Exception {
	}

	public void Datos(int i){
		if (i==1) { //nuevo
			c.setNombre("Categoria demo");
			c.setDescripcion("Categoria demo para pruebas de inserción (POST)");
		} else if(i==2) { //modificación
			c.setNombre("Categoria demo modificación");
			c.setDescripcion("Categoria demo para pruebas de modificación (PUT)");			
		} else if(i==3) {//modificación parcial
			field.put("descripcion", "Categoria para modificación parcial (PATCH)");
		}
	}		
		
	@Test
	@Order(1)
	void getAllCategoria() {	
		ArrayList<Categoria> response = (ArrayList<Categoria>) service.get().getBody();
		assertThat(response).isNotNull();
	}	
	
	@Test
	@Order(2)
	void getUnaCategoria() {
		Categoria ct = (Categoria) service.getById(2L).getBody();
		assertEquals(ct.getId_categoria(), 2L);
	}		

	@Test
	@Order(3)
	void postGrabar() {
		Datos(1);
		Categoria ct = (Categoria) service.post(c).getBody();
		assertThat(ct).isNotNull();		
		assertEquals(c.getNombre(),ct.getNombre());		
		assertEquals(c.getDescripcion(),ct.getDescripcion());				
	}
	
	@Test
	@Order(4)
	void putActualizacionFull() {
		Datos(2);
		Categoria ct = (Categoria) service.put(7L, c).getBody();
		assertThat(ct).isNotNull();
		assertEquals(c.getNombre(),ct.getNombre());		
		assertEquals(c.getDescripcion(),ct.getDescripcion());
	}
	
	@Test
	@Order(5)
	void patchActualizacionParcial() {
		Datos(3);
		Categoria ct = (Categoria) service.patch(8L, field).getBody();
		assertThat(ct).isNotNull();
		assertEquals(field.get("descripcion").toString() ,ct.getDescripcion());		
	}
	
	@Test
	@Order(6)
	void Delete() {
		Boolean  response = (Boolean) service.delete(6L).getBody();
		assertThat(response).isNotNull();
		assertEquals(true,response);	
	}			
}
