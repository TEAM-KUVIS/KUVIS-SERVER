package com.kuvis.server.global.external;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws-property.s3-bucket-name}")
    private String bucketName;

    @Value("${aws-property.s3-substring}")
    private String s3Substring;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    // PreSigned URL 만료시간 60분
    private static final Long PRE_SIGNED_URL_EXPIRE_MINUTE = 60L;
    // 파일 확장자 제한 pdf만 허용
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("application/pdf");
    // 단일 PUT 요청 파일 크기 제한 (500MB)
    private static final long MAX_FILE_SIZE = 500 * 1024L * 1024L; // 500MB
    private final static String profilePath = "pdfs/";

    public PreSignedUrlResponse getPdfUploadPreSignedUrl() {
        try {
            // UUID 파일명 생성
            String uuidFileName = UUID.randomUUID().toString() + ".pdf";
            // 경로 + 파일 이름
            String key = profilePath + uuidFileName;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            // S3에서 업로드는 PUT 요청
            PutObjectPresignRequest preSignedUrlRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
                    .putObjectRequest(putObjectRequest)
                    .build();

            // Persigned URL 생성
            URL url = s3Presigner.presignPutObject(preSignedUrlRequest).url();

            return PreSignedUrlResponse.of("pdfs/"+uuidFileName, url.toString());

        } catch (RuntimeException e) {
            throw new RuntimeException("presgned url 생성 에러");
        }
    }
}
