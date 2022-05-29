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
import com.dianny.practicademo.app.entity.Producto;
import com.dianny.practicademo.app.service.ProductoService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductoImplementationTest {

	@Autowired
	ProductoService service;	
	
	ArrayList<Producto> data;
	
	@InjectMocks
	Producto p = new Producto();
	
	Map<Object, Object> field = new HashMap<>(); 	

	@Before
	public void setUp() throws Exception {
		
	}

	public void Datos(int i){
		if (i==1) { //nuevo
			p.setNombre("Producto demo");
			p.setDescripcion("Producto demo para pruebas de inserción (POST)");
			p.setExistencia((float) 10.0);
			p.setPrecio((float) 25.0);
			p.setId_categoria((long) 1);
		} else if(i==2) { //modificación
			p.setNombre("Producto demo modificación");
			p.setDescripcion("Producto demo para pruebas de modificación (PUT)");
			p.setExistencia((float) 5.0);
			p.setPrecio((float) 50.0);
			p.setId_categoria((long) 3);			
		} else if(i==3) {//modificación parcial
			field.put("nombre", "Producto Mod");
			field.put("descripcion", "producto para modificación parcial (PATCH)");
		}
	}	
	
	@Test
	@Order(1)
	void getAllProducto() {	
		ArrayList<Producto> response = (ArrayList<Producto>) service.get().getBody();
		assertThat(response).isNotNull();
	}	
	
	@Test
	@Order(2)
	void getUnProducto() {
		Producto pr = (Producto) service.getById(2L).getBody();
		assertEquals(pr.getId_producto(), 2L);
	}		

	@Test
	@Order(3)
	void postGrabar() {
		Datos(1);
		Producto pr = (Producto) service.post(p).getBody();
		assertThat(pr).isNotNull();		
		assertEquals(p.getNombre(),pr.getNombre());		
		assertEquals(p.getDescripcion(),pr.getDescripcion());
		assertEquals(p.getExistencia(),pr.getExistencia());
		assertEquals(p.getPrecio(),pr.getPrecio());
		assertEquals(p.getId_categoria(),pr.getId_categoria());				
	}
	
	@Test
	@Order(4)
	void putActualizacionFull() {
		Datos(2);
		Producto pr = (Producto) service.put(3L, p).getBody();
		assertThat(pr).isNotNull();
		assertEquals(p.getNombre(),pr.getNombre());		
		assertEquals(p.getDescripcion(),pr.getDescripcion());
		assertEquals(p.getExistencia(),pr.getExistencia());
		assertEquals(p.getPrecio(),pr.getPrecio());
		assertEquals(p.getId_categoria(),pr.getId_categoria());
	}
	
	@Test
	@Order(5)
	void patchActualizacionParcial() {
		Datos(3);
		Producto pr = (Producto) service.patch(4L, field).getBody();
		assertThat(pr).isNotNull();
		assertEquals(field.get("descripcion").toString() ,pr.getDescripcion());
		assertEquals(field.get("nombre").toString() ,pr.getNombre());		
	}
	
	@Test
	@Order(6)
	void Delete() {
		Boolean  response = (Boolean) service.delete(80L).getBody();
		assertThat(response).isNotNull();
		assertEquals(true,response);	
	}			
}
