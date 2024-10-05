package com.mascotas.mascotas.service;

import com.mascotas.mascotas.model.Mascotas;
import java.util.List;
import java.util.Optional;

public interface MascotasService {
    List<Mascotas> getAllMascotas();
    Optional<Mascotas> getMascotasById(Long id);
    Mascotas createMascotas(Mascotas mascotas);
    Mascotas updateMascotas (Long id, Mascotas mascotas);
    void deleteMascotas(Long id);
    
}
