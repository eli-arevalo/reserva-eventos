package com.duoc.reservaEventos.controller;

import com.duoc.reservaEventos.dto.ReservaRegistroDTO;
import com.duoc.reservaEventos.dto.ReservaRespuestaDTO;
import com.duoc.reservaEventos.service.ReservaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


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

   @GetMapping("/{id}/pdf")
   public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) throws IOException {

       byte[] pdf = reservaService.generarPdf(id);

       return ResponseEntity.ok()//devuleve un 200 ok si todo va bien
            .contentType(MediaType.APPLICATION_PDF)//identifica el contenido como un pdf :O
            .header//indica que es un archivo descargable y establece su name
                (HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"resumen-compra" + id + ".pdf\""
            )
            .body(pdf);//coloca los bytes del pdf que recibimos del service dentro de la respuesta,
   }

   @PostMapping("/{id}/pdf/s3")
   public ResponseEntity<String> subirPdfS3(@PathVariable Long id) throws IOException{

        reservaService.subirPdfS3(id);

        return ResponseEntity.ok("PDF subido con éxito a S3 >:D");
   }

   @GetMapping("/{id}/pdf/s3")
   public ResponseEntity<byte[]> descargarPdfS3(@PathVariable Long id){

        byte[] pdf = reservaService.descargarPdfS3(id);//llamamos a nuestro reservaService, que a su vez usa S3Service para recuperar el arch. guardado en el s3

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"resumen-compra-" + id + ".pdf\"")
            .body(pdf);
   }

   @PutMapping(value = "/{id}/pdf/s3", consumes = MediaType.APPLICATION_PDF_VALUE)//consemes= indica que el endpoint espera recibir un pdf
   public ResponseEntity<String> actualizarPdfS3(@PathVariable Long id, @RequestBody byte[] nuevoPdf){

    reservaService.actualizarPdfS3(id, nuevoPdf);

    return ResponseEntity.ok("PDF acutalizado con éxito en S3 >:D");
   }

   @DeleteMapping("/{id}/pdf/s3")
   public ResponseEntity<String> eliminarPdfS3(@PathVariable Long id){

        reservaService.eliminarPdfS3(id);

        return ResponseEntity.ok("PDF eliminado de forma exitosa en S3 :D");
   }
}
