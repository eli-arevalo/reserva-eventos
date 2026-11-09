package com.duoc.reservaEventos.controller;

import com.duoc.reservaEventos.dto.ReservaRegistroDTO;
import com.duoc.reservaEventos.dto.ReservaRespuestaDTO;
import com.duoc.reservaEventos.service.ReservaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;




@RestController 
@RequestMapping("/api/reservas")
public class ReservaController {
    
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaRespuestaDTO> crearReserva(@Valid @RequestBody ReservaRegistroDTO reservaDTO) {
       ReservaRespuestaDTO nuevaReserva = reservaService.crearReserva(reservaDTO);

       return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        
    }

    @GetMapping
    public ResponseEntity<List<ReservaRespuestaDTO>> listarReservas() {
        return ResponseEntity.ok(reservaService.listarReservas());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<ReservaRespuestaDTO> obtenerReservaPorId(@PathVariable Long id) {
        Optional<ReservaRespuestaDTO> reserva = reservaService.obtenerReservaPorId(id);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
