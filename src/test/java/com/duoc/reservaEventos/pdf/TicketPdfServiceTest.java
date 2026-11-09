package com.duoc.reservaEventos.pdf;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

import com.duoc.reservaEventos.repository.ReservaRepository;
import com.duoc.reservaEventos.entity.Reserva;

@SpringBootTest 
public class TicketPdfServiceTest {

    @Autowired 
    private TicketPdfService ticketPdfService;

    @Autowired 
    private ReservaRepository reservaRepository;

    @Test 
    void generarPdfDeReservaExistente() throws IOException{

        Reserva reserva = reservaRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Reserva no encontrada"));

        byte[] pdf = ticketPdfService.generarPdf(reserva);
        assertTrue(pdf.length > 0);

        Path archivo = Files.createTempFile("ticket-prueba-", ".pdf");
        Files.write(archivo, pdf);

        System.out.println("PDF generado en: " + archivo.toAbsolutePath());
    }

    
}
