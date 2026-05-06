package com.duoc.backend.Mascota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // GET ALL
    @GetMapping
    public Iterable<Mascota> getAllMascotas() {
        return mascotaService.getAllMascotas();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Mascota getMascotaById(@PathVariable Long id) {
        return mascotaService.getMascotaById(id);
    }

    @PostMapping
    public Mascota saveMascota(@RequestBody Mascota mascota) {
        return mascotaService.saveMascota(mascota);
    }

  
    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new RuntimeException("Solo ADMIN puede eliminar mascotas");
        }

        mascotaService.deleteMascota(id);
    }

   
    @PutMapping("/{id}")
    public Mascota updateMascota(@PathVariable Long id, @RequestBody Mascota mascotaActualizada) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new RuntimeException("Solo ADMIN puede editar mascotas");
        }

        Mascota mascota = mascotaService.getMascotaById(id);

        mascota.setNombre(mascotaActualizada.getNombre());
        mascota.setTipo(mascotaActualizada.getTipo());
        mascota.setRaza(mascotaActualizada.getRaza());
        mascota.setColor(mascotaActualizada.getColor());
        mascota.setEdad(mascotaActualizada.getEdad());

        return mascotaService.saveMascota(mascota);
    }
}