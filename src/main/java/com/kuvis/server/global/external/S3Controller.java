package com.kuvis.server.global.external;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/pdf")
    public ResponseEntity<PreSignedUrlResponse> getProfilePreSignedUrl() {
        return ResponseEntity.ok(s3Service.getPdfUploadPreSignedUrl());
    }
}
