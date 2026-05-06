package com.duoc.backend.Busqueda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.backend.Mascota.Mascota;
import com.duoc.backend.Mascota.MascotaRepository;

@Service
public class BusquedaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> buscar(String tipo, String raza, String color) {

        Iterable<Mascota> mascotas = mascotaRepository.findAll();

        return StreamSupport.stream(mascotas.spliterator(), false)
                .filter(m -> tipo == null || m.getTipo().equalsIgnoreCase(tipo))
                .filter(m -> raza == null || m.getRaza().equalsIgnoreCase(raza))
                .filter(m -> color == null || m.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}