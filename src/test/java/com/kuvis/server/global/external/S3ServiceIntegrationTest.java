package com.kuvis.server.global.external;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class S3ServiceIntegrationTest {

    @Autowired
    private S3Service s3Service;

    @Test
    void testGetUploadPreSignedUrl() throws Exception {
        // Pre-Signed URL 생성
        PreSignedUrlResponse response = s3Service.getPdfUploadPreSignedUrl();

        // Assertions: Pre-Signed URL이 유효한지 확인
        assertThat(response).isNotNull();
        assertThat(response.fileName()).isNotEmpty();
        assertThat(response.url()).isNotEmpty();

        // 이미지 파일 읽기
        File file = new File("/Users/seokbeom/graduate-project/server/src/test/resources/한권으로 배우는 도커 & 쿠버네티스.pdf");
        byte[] imageData;
        try (InputStream inputStream = new FileInputStream(file)) {
            imageData = inputStream.readAllBytes();
        }

        // Pre-Signed URL로 PUT 요청 보내기
        URL url = new URL(response.url());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/pdf");
        connection.setRequestProperty("Content-Length", String.valueOf(imageData.length));

        // 이미지 데이터 전송
        try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                connection.getOutputStream().write(buffer, 0, bytesRead);
            }
        }

        int responseCode = connection.getResponseCode();
        connection.disconnect();

        // Assertions: HTTP 응답 코드가 200 (성공)인지 확인
        assertThat(responseCode).isEqualTo(200);
    }
}
