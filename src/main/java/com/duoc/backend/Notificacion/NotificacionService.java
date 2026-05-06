package com.duoc.backend.Notificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository repo;

    public Notificacion crear(Notificacion n) {
        return repo.save(n);
    }

    public List<Notificacion> obtenerPorUsuario(Long userId) {
        return repo.findByUserId(userId);
    }

    public void marcarLeida(Long id) {
        Notificacion n = repo.findById(id).orElseThrow();
        n.setLeida(true);
        repo.save(n);
    }
}