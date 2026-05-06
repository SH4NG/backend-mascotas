package com.duoc.backend.Busqueda;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duoc.backend.Mascota.Mascota;

public interface BusquedaRepository extends JpaRepository<Mascota, Long> {

    @Query("""
    SELECT m FROM Mascota m
    WHERE (:tipo IS NULL OR LOWER(m.tipo) = LOWER(:tipo))
    AND (:raza IS NULL OR LOWER(m.raza) = LOWER(:raza))
    AND (:color IS NULL OR LOWER(m.color) = LOWER(:color))
    """)
    List<Mascota> buscarConFiltros(
        @Param("tipo") String tipo,
        @Param("raza") String raza,
        @Param("color") String color
    );
}