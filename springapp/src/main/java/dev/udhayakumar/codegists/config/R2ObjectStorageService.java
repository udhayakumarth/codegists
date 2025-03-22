package dev.udhayakumar.codegists.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
public class R2ObjectStorageService {
    private final S3Client r2Client;
    private final String bucketName;

    public R2ObjectStorageService(
            @Value("${r2.endpoint}") String endpoint,
            @Value("${r2.accessKey}") String accessKey,
            @Value("${r2.secretKey}") String secretKey,
            @Value("${r2.bucketName}") String bucketName) {

        this.bucketName = bucketName;

        this.r2Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint)) // R2 uses custom endpoints
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.US_EAST_1) // R2 does not use regions, but AWS SDK requires it
                .build();
    }

    public String uploadFile(String fileId, String fileContent) {

        r2Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileId)
                        .build(),
                RequestBody.fromBytes(fileContent.getBytes())
        );

        return fileId;
    }
}
