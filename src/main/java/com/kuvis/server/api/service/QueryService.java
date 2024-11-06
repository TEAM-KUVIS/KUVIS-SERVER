package com.kuvis.server.api.service;

import com.kuvis.server.api.dto.PdfNameResponse;
import com.kuvis.server.api.dto.StorePdfRequest;
import com.kuvis.server.api.repository.PdfEntity;
import com.kuvis.server.api.repository.PdfRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final PdfRepository pdfRepository;

    @Transactional
    public void createPdf(StorePdfRequest storePdfRequest) {
        PdfEntity pdf = new PdfEntity(storePdfRequest.filename(), storePdfRequest.name());
        pdfRepository.save(pdf);
    }

    @Transactional(readOnly = true)
    public List<PdfNameResponse> getPdfNames() {
        return pdfRepository.findAll().stream()
                .map(pdf -> new PdfNameResponse(pdf.getId(), pdf.getName()))
                .collect(Collectors.toList());
    }
}
