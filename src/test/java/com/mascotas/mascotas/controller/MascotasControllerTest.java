package com.mascotas.mascotas.controller;

import com.mascotas.mascotas.Controller.MascotasController;
import com.mascotas.mascotas.model.Mascotas;
import com.mascotas.mascotas.service.MascotasService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MascotasController.class) 
public class MascotasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MascotasService mascotasService;

    @Test
    public void obtenerTodosTest() throws Exception {
        Mascotas mascotas1 = new Mascotas();
        mascotas1.setTipoProducto("Master Cat 10kg");
        mascotas1.setId(1L);

        Mascotas mascotas2 = new Mascotas();
        mascotas2.setTipoProducto("Master Dog 20kg");
        mascotas2.setId(2L);

        List<Mascotas> mascotas = Arrays.asList(mascotas1, mascotas2);
        Mockito.when(mascotasService.getAllMascotas()).thenReturn(mascotas);

        mockMvc.perform(MockMvcRequestBuilders.get("/mascotas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].tipoProducto", is("Master Cat 10kg")))
                .andExpect(jsonPath("$[1].tipoProducto", is("Master Dog 20kg")));
    }

    @Test
    public void testGetMascotaByIdHateoasLinks() throws Exception {
        Mascotas mockMascota = new Mascotas();
        mockMascota.setId(1L);
        mockMascota.setTipoProducto("Mock Producto");

        Mockito.when(mascotasService.getMascotasById(anyLong())).thenReturn(Optional.of(mockMascota));

        mockMvc.perform(MockMvcRequestBuilders.get("/mascotas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.all-mascotas.href").exists());
    }
}
