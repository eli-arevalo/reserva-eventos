package com.duoc.reservaEventos.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

//esta clase utilizará al cliente creado por awsS3Config para subir, descargar, modificar y eliminar los archivos :D
@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(S3Client s3Client, @Value("${aws.s3.bucket-name}") String bucketName){
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public void subirPdf(Long idReserva, byte[] pdf){

        String key = idReserva + "/resumen-compra-" + idReserva + ".pdf"; //construimos la ubi del archivo

        //preparamos la solicitud que enviaremos al s3
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName).key(key).contentType("application/pdf").build();

        //le decimos al s3Client que envie nuestros bytes del pdf al s3 usando la info que preparamos antes
        s3Client.putObject(request, RequestBody.fromBytes(pdf));
    }

    public byte[] descargarPdf(Long idReserva){//devolveremos el pdf como un arreglo de bytes

        String key = idReserva + "/resumen-compra-" + idReserva + ".pdf";

        GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(key).build();

        return s3Client.getObjectAsBytes(request).asByteArray();
    }

    public void eliminarPdf(Long idReserva){

        String key = idReserva + "/resumen-compra-" + idReserva + ".pdf";

        DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucketName).key(key).build();

        s3Client.deleteObject(request);
    }
}
