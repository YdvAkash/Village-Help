package Clarify.demo.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class PostUploadService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadPost(
            MultipartFile image,
            String state,
            String district,
            String tehsil,
            String villageCode,
            String postId
    ) throws IOException {

        String extension = getFileExtension(image.getOriginalFilename());

        // ✅ FINAL S3 PATH
        String s3Key = String.format(
                "%s/%s/%s/%s/%s.%s",
                sanitize(state),
                sanitize(district),
                sanitize(tehsil),
                sanitize(villageCode),
                sanitize(postId),
                extension
        );

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType(image.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(image.getBytes())
        );

        return getFileUrl(s3Key);
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "jpg";
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    private String sanitize(String value) {
        return value.trim().replaceAll("\\s+", "_");
    }

    private String getFileUrl(String key) {
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }
}
