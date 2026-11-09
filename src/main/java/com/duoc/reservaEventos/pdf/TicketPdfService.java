package com.duoc.reservaEventos.pdf;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import com.duoc.reservaEventos.entity.Reserva;

//en esta clase recibimos los datos de una reserva y contruimos un pdf :D
@Service 
public class TicketPdfService {

    public byte[] generarPdf(Reserva reserva) throws IOException{

        ByteArrayOutputStream salida = new ByteArrayOutputStream();

        try (PDDocument documento = new PDDocument()){//creamos un doc. vacio

            PDPage pagina = new PDPage();//y una pág. en blanco
            documento.addPage(pagina);//agregamos pág. al doc.

            //contenido :O
            PDType1Font fuente = new PDType1Font(
                Standard14Fonts.FontName.HELVETICA
            );
            Long idReserva = reserva.getIdReserva();
            String nombreEvento = reserva.getEvento().getNombreEvento();
            Integer cantidad = reserva.getCantidadSolicitada();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String fecha = reserva.getEvento().getFechaEvento().format(formato);

            try(PDPageContentStream contenido = new PDPageContentStream(documento, pagina)){

                contenido.beginText();//acá iniciamos la op. de escritura de texto

                contenido.setFont(fuente, 16);
                contenido.setLeading(22);
                contenido.newLineAtOffset(50, 750);//establecemos la pocisión del texto

                contenido.showText("Ticket de reserva");
                contenido.newLine();

                contenido.showText("Numero de reserva: " + idReserva);
                contenido.newLine();

                contenido.showText("Evento:" + nombreEvento);
                contenido.newLine();
                
                contenido.showText("Fecha: " + fecha);
                contenido.newLine();
                contenido.showText("Entradas reservadas: " + cantidad);

                contenido.endText();
            }
            documento.save(salida);

        }
        return salida.toByteArray();
    }
}
