package com.amdev.qrcode.infra;

import com.amdev.qrcode.ports.StoragePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Component
public class S3StorageAdapter implements StoragePort {

    private final S3Client s3Client;
    private final String bucketName;

    public S3StorageAdapter(
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket.name}") String bucketName,
            @Value("${aws.s3.endpoint}") String endpoint,
            @Value("${aws.s3.access_key}") String accessKey,
            @Value("${aws.s3.secret_key}") String secretKey
    ) {
        this.bucketName = bucketName;
        this.s3Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .forcePathStyle(true)
                .build();
    }

    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        PutObjectRequest putObjectRequest = PutObjectRequest
                .builder()
                .bucket(this.bucketName)
                .key(fileName)
                .contentType(contentType)
                .build();

        this.s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));

        return String.format("https://storage.am-dev.app.br/%s/%s", this.bucketName, fileName);
    }
}

