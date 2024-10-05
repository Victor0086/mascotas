package com.mascotas.mascotas.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mascotas.mascotas.model.Mascotas;
import com.mascotas.mascotas.service.MascotasService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;


@RestController
@RequestMapping("/mascotas")
public class MascotasController {


    private static final Logger log = LoggerFactory.getLogger(MascotasController.class);

    @Autowired
    private MascotasService mascotasService;

    @GetMapping
    public List<Mascotas> getAllMascotas(){
        return mascotasService.getAllMascotas();
    }

  @GetMapping("/{id}")
public ResponseEntity<Object> getMascotasById(@PathVariable Long id) {
    Optional<Mascotas> mascota = mascotasService.getMascotasById(id);
    if (mascota.isPresent()) {
        EntityModel<Mascotas> resource = EntityModel.of(mascota.get());
        // Añadir enlaces HATEOAS
        resource.add(linkTo(methodOn(MascotasController.class).getMascotasById(id)).withSelfRel());
        resource.add(linkTo(methodOn(MascotasController.class).getAllMascotas()).withRel("all-mascotas"));
        return ResponseEntity.ok(resource);
    } else {
        log.error("No se encontró el producto de mascota con ID {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("No se encontró el producto de mascota con ID " + id));
    }
}
    @PostMapping
    public ResponseEntity<Object> createMascotas(@Validated  @RequestBody Mascotas mascotas) {
        Mascotas createdMascotas = mascotasService.createMascotas(mascotas);
        if (createdMascotas == null ) {
            log.error("Error al crear el Producto {}", mascotas);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el Producto"));
        }
        return ResponseEntity.ok("Mascota creada exitosamente");
    }

    @PutMapping ("/{id}")
    public Mascotas updateMascotas(@PathVariable Long id, @RequestBody Mascotas mascotas){
        return mascotasService.updateMascotas(id, mascotas);
    }
    @DeleteMapping ("/{id}")
    public void deleteMascotas(@PathVariable Long id){
        mascotasService.deleteMascotas(id);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }



}
