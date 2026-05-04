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

import com.duoc.backend.Usuario.User;
import com.duoc.backend.Usuario.UserRepository;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Iterable<Mascota> getAllMascotas() {
        return mascotaService.getAllMascotas();
    }

    @GetMapping("/{id}")
    public Mascota getMascotaById(@PathVariable Long id) {
        return mascotaService.getMascotaById(id);
    }

    //CREAR CON USUARIO DEL TOKEN
    @PostMapping
    public Mascota saveMascota(@RequestBody Mascota mascota) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        mascota.setUser(user);

        return mascotaService.saveMascota(mascota);
    }

    //DELETE SOLO DUEÑO O ADMIN
    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable Long id) {

        Mascota mascota = mascotaService.getMascotaById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!mascota.getUser().getUsername().equals(username) && !isAdmin) {
            throw new RuntimeException("No tienes permiso para eliminar esta mascota");
        }

        mascotaService.deleteMascota(id);
    }

    //UPDATE SOLO DUEÑO O ADMIN
    @PutMapping("/{id}")
    public Mascota updateMascota(@PathVariable Long id, @RequestBody Mascota mascotaActualizada) {

        Mascota mascota = mascotaService.getMascotaById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!mascota.getUser().getUsername().equals(username) && !isAdmin) {
            throw new RuntimeException("No tienes permiso para editar esta mascota");
        }

        mascota.setNombre(mascotaActualizada.getNombre());
        mascota.setTipo(mascotaActualizada.getTipo());
        mascota.setRaza(mascotaActualizada.getRaza());
        mascota.setColor(mascotaActualizada.getColor());
        mascota.setEdad(mascotaActualizada.getEdad());

        return mascotaService.saveMascota(mascota);
    }
}