package com.mascotas.mascotas.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MascotasNotFoundException extends RuntimeException {

    public MascotasNotFoundException(String message) {
        super(message);
    }
    
}
