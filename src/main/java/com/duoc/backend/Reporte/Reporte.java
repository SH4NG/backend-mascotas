package com.duoc.backend.Reporte;

import com.duoc.backend.Mascota.Mascota;
import com.duoc.backend.Usuario.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 Mascota reportada
    @ManyToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    // 🔥 Usuario que reporta
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String telefono;
    private String ubicacion;

    // 🔥 link de imagen
    private String imagenUrl;

    // estado: encontrado / pendiente / resuelto
    private String estado;

    public Reporte() {}

    // getters y setters
    public Long getId() { return id; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}