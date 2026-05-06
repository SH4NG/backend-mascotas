package com.duoc.backend.Notificacion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;      //destinatario
    private String mensaje;

    private boolean leida = false;

    private Long mascotaId;   // opcional
    private String fecha;

    public Notificacion() {}

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getMensaje() { return mensaje; }
    public boolean isLeida() { return leida; }
    public Long getMascotaId() { return mascotaId; }
    public String getFecha() { return fecha; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public void setLeida(boolean leida) { this.leida = leida; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}