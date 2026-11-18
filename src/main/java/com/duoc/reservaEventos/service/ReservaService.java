package com.duoc.reservaEventos.service;
import com.duoc.reservaEventos.dto.ReservaRespuestaDTO;
import com.duoc.reservaEventos.dto.ReservaRegistroDTO;

import java.util.Optional;
import java.io.IOException;
import java.util.List;

public interface ReservaService {
    ReservaRespuestaDTO crearReserva(ReservaRegistroDTO reservaDTO);

    List<ReservaRespuestaDTO> listarReservas();

    Optional<ReservaRespuestaDTO> obtenerReservaPorId(Long id);

    byte[] generarPdf(Long idReserva) throws IOException;//el metodo devolverá el cont. binario del pdf

    void subirPdfS3(Long idReserva) throws IOException;

    byte[] descargarPdfS3(Long idReserva);

    void actualizarPdfS3(Long idReserva, byte[] nuevoPdf);

    void eliminarPdfS3(Long idReserva);
}
