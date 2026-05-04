package com.duoc.backend.Reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public List<Reporte> obtenerReportesEncontrados() {
        return reporteRepository.findByEstado("ENCONTRADO");
    }
    public List<Reporte> getAllReportes() {
    return reporteRepository.findAll();
    }
    public void deleteReporte(Long id) {
    if (reporteRepository.existsById(id)) {
        reporteRepository.deleteById(id);
     }
    }
}