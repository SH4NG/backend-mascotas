package com.duoc.backend.Reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.backend.Usuario.User;
import com.duoc.backend.Usuario.UserRepository;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private UserRepository userRepository;

    // CREAR REPORTE (SIN RELACIONES)
    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // AHORA USAMOS ID
        reporte.setUserId(user.getId().longValue());

        // estado por defecto
        reporte.setEstado("ENCONTRADO");

        return reporteService.guardarReporte(reporte);
    }

    // VER SOLO ENCONTRADAS
    @GetMapping("/encontradas")
    public List<Reporte> getEncontradas() {
        return reporteService.obtenerReportesEncontrados();
    }

    //  VER TODOS
    @GetMapping
    public List<Reporte> getAllReportes() {
        return reporteService.getAllReportes();
    }

    // SOLO ADMIN BORRA
    @DeleteMapping("/{id}")
    public void deleteReporte(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new RuntimeException("Solo ADMIN puede eliminar reportes");
        }

        reporteService.deleteReporte(id);
    }
}