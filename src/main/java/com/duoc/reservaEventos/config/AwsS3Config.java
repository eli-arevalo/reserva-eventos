package com.duoc.reservaEventos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.regions.Region;

//esta clase tiene la responsabilidad de crear y configurar el cliente
@Configuration
public class AwsS3Config {

    @Bean
    public S3Client s3Client (@Value("${aws.region}") String region){
        return S3Client.builder().region(Region.of(region)).build();
    }

}
