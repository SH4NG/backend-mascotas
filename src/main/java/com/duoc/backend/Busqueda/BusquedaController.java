package com.duoc.backend.Busqueda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.backend.Mascota.Mascota;

@RestController
@RequestMapping("/busqueda")
public class BusquedaController {

    @Autowired
    private BusquedaService busquedaService;

    @GetMapping
    public List<Mascota> buscar(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String raza,
            @RequestParam(required = false) String color) {

        return busquedaService.buscar(tipo, raza, color);
    }
}