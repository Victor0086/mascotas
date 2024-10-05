package com.mascotas.mascotas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mascotas.mascotas.model.Mascotas;
import com.mascotas.mascotas.repository.MascotasRepository;


@ExtendWith(MockitoExtension.class)
public class MascotasServiceTest {

    @InjectMocks
    private MascotasServiceImpl mascotasServicio;

    @Mock
    private MascotasRepository mascotasRepositoryMock;

    @Test
    public void guardarMascotasTest() {
        Mascotas mascotas = new Mascotas();
        mascotas.setTipoProducto("Master Dog 20kg");

        when(mascotasRepositoryMock.save(any())).thenReturn(mascotas);

        Mascotas resultado = mascotasServicio.createMascotas(mascotas);

        assertEquals("Master Dog 20kg", resultado.getTipoProducto());
    }

    @Test
    public void actualizarMascotasTest() {
        Mascotas mascotas = new Mascotas();
        mascotas.setTipoProducto("Master Cat 10kg");

        when(mascotasRepositoryMock.existsById(anyLong())).thenReturn(true);
        when(mascotasRepositoryMock.save(any())).thenReturn(mascotas);

        Mascotas resultado = mascotasServicio.updateMascotas(1L, mascotas);

        assertNotNull(resultado);
        assertEquals("Master Cat 10kg", resultado.getTipoProducto());
    }

    @Test
    public void actualizarMascotasNoExistenteTest() {
        Mascotas mascotas = new Mascotas();
        when(mascotasRepositoryMock.existsById(anyLong())).thenReturn(false);

        Mascotas resultado = mascotasServicio.updateMascotas(1L, mascotas);

        assertNull(resultado);
    }

    @Test
    public void eliminarMascotasTest() {
        mascotasServicio.deleteMascotas(1L);
        verify(mascotasRepositoryMock, times(1)).deleteById(1L);
    }
}
