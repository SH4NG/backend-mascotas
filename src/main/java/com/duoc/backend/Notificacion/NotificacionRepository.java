package com.duoc.backend.Notificacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByUserId(Long userId);
}