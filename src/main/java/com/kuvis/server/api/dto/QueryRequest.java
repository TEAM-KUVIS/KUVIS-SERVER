package com.kuvis.server.api.dto;

public record QueryRequest(
        Long pdfId,
        String query
) {
}
