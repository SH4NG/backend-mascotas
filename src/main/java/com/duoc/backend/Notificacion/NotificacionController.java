package com.duoc.backend.Notificacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService service;

    @Autowired
    private UserRepository userRepository;

    // CREAR NOTIFICACIÓN
    @PostMapping
    public Notificacion crear(@RequestBody Notificacion n) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        n.setUserId(user.getId().longValue()); // 🔥 asigna desde token

        return service.crear(n);
    }

    // VER MIS NOTIFICACIONES
    @GetMapping
    public List<Notificacion> misNotificaciones() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        return service.obtenerPorUsuario(user.getId().longValue());
    }

    //MARCAR COMO LEÍDA
    @PutMapping("/{id}/leer")
    public void marcarLeida(@PathVariable Long id) {
        service.marcarLeida(id);
    }
}