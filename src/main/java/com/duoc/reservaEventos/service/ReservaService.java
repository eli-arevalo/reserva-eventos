package com.duoc.reservaEventos.service;
import com.duoc.reservaEventos.dto.ReservaRespuestaDTO;
import com.duoc.reservaEventos.dto.ReservaRegistroDTO;

import java.util.Optional;
import java.util.List;

public interface ReservaService {
    ReservaRespuestaDTO crearReserva(ReservaRegistroDTO reservaDTO);

    List<ReservaRespuestaDTO> listarReservas();

    Optional<ReservaRespuestaDTO> obtenerReservaPorId(Long id);
}
