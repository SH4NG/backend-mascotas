package com.duoc.backend.Reporte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    // 🔥 ver mascotas encontradas
    List<Reporte> findByEstado(String estado);
}