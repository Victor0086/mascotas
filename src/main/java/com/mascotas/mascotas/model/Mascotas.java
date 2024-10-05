package com.mascotas.mascotas.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ProductosMascotas")
public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "No puede dejar TipoProducto vacío")
    @NotBlank(message = "No puede dejar TipoProducto vacío")
    @Column(name = "TipoProducto")
    private String tipoProducto; 

    @Size(min = 6, max = 50, message = "El destinatario debe tener entre 6 y 50 caracteres")
    @NotNull(message = "El destinatario no puede ser nulo")
    @NotBlank(message = "El destinatario no puede estar vacío")
    @Column(name = "destinatario")
    private String destinatario;

    @NotNull(message = "La dirección de destino no puede ser nula")
    @NotBlank(message = "La dirección de destino no puede estar vacía")
    @Column(name = "direccionDestino") 
    private String direccionDestino;

    @NotNull(message = "La fecha de envío no puede ser nula")
    @Column(name = "fechaEnvio")
    private LocalDateTime fechaEnvio; 

    @NotNull(message = "La ubicación actual no puede ser nula")
    @NotBlank(message = "La ubicación actual no puede estar vacía")
    @Column(name = "ubicacionActual")
    private String ubicacionActual;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }
}
