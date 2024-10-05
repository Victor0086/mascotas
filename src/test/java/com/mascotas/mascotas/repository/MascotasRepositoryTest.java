package com.mascotas.mascotas.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mascotas.mascotas.model.Mascotas;

import java.util.Optional;

@SpringBootTest
@Transactional
public class MascotasRepositoryTest {

    @Autowired
    private MascotasRepository mascotasRepository;

    @Test
    public void guardarMascotasTest() {
        Mascotas mascotas = new Mascotas();
        mascotas.setTipoProducto("Master Cat 10kg");
        mascotas.setDireccionDestino("Calle Falsa 123");
        mascotas.setDestinatario("Juan Pérez");
        mascotas.setUbicacionActual("Bodega A");
        mascotas.setFechaEnvio(LocalDateTime.of(2024, 9, 30, 0, 0)); 
    
        Mascotas resultado = mascotasRepository.save(mascotas);
    
        assertNotNull(resultado.getId());
        assertEquals("Master Cat 10kg", resultado.getTipoProducto());
    }
    
    @Test
    public void encontrarPorIdTest() {
        Mascotas mascotas = new Mascotas();
        mascotas.setTipoProducto("Master Dog 20kg");
        mascotas.setDireccionDestino("Calle Falsa 123");
        mascotas.setDestinatario("Juan Pérez");
        mascotas.setUbicacionActual("Bodega A");
        mascotas.setFechaEnvio(LocalDateTime.of(2024, 9, 30, 0, 0));  
    
        Mascotas savedMascotas = mascotasRepository.save(mascotas);
    
        Optional<Mascotas> resultado = mascotasRepository.findById(savedMascotas.getId());
    
        assertTrue(resultado.isPresent());
        assertEquals("Master Dog 20kg", resultado.get().getTipoProducto());
    }

    @Test
    public void eliminarMascotasTest() {
        Mascotas mascotas = new Mascotas();
        mascotas.setTipoProducto("Master Dog 20kg");
        mascotas.setDireccionDestino("Calle Falsa 123");
        mascotas.setDestinatario("Juan Pérez");
        mascotas.setUbicacionActual("Bodega A");
        mascotas.setFechaEnvio(LocalDateTime.of(2024, 9, 30, 0, 0)); 

        Mascotas savedMascotas = mascotasRepository.save(mascotas);

        mascotasRepository.deleteById(savedMascotas.getId());

        Optional<Mascotas> resultado = mascotasRepository.findById(savedMascotas.getId());
        assertFalse(resultado.isPresent());
    }
}
