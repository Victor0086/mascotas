package com.mascotas.mascotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.mascotas.model.Mascotas;

public interface MascotasRepository extends JpaRepository<Mascotas, Long>{

    
}
