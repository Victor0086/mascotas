package com.mascotas.mascotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mascotas.mascotas.model.Mascotas;
import com.mascotas.mascotas.repository.MascotasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MascotasServiceImpl implements MascotasService{
    @Autowired
    private MascotasRepository mascotasRepository;

    @Autowired
    public List<Mascotas> getAllMascotas(){
        return mascotasRepository.findAll();
    }

    @Override 
    public Optional<Mascotas> getMascotasById(Long id){
        return mascotasRepository.findById(id);
    }

    @Override 
    public Mascotas createMascotas(Mascotas mascotas){
        return mascotasRepository.save(mascotas);
    }
    @Override
    public Mascotas updateMascotas(Long id, Mascotas mascotas){
        if(mascotasRepository.existsById(id)){
            mascotas.setId(id);
            return mascotasRepository.save(mascotas);
        }   else{
                return null;
        }
        
    }

    @Override
    public void deleteMascotas (Long id){
        mascotasRepository.deleteById(id);
    }    
}
