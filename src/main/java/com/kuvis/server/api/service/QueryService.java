package com.kuvis.server.api.service;

import com.kuvis.server.api.dto.PdfNameResponse;
import com.kuvis.server.api.dto.QueryRequest;
import com.kuvis.server.api.dto.StorePdfRequest;
import com.kuvis.server.api.repository.HistoryEntity;
import com.kuvis.server.api.repository.HistoryRepository;
import com.kuvis.server.api.repository.PdfEntity;
import com.kuvis.server.api.repository.PdfRepository;
import com.kuvis.server.global.external.FlaskClient;
import com.kuvis.server.global.external.FlaskResponseDto;
import com.kuvis.server.global.external.QueryPythonRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final PdfRepository pdfRepository;
    private final FlaskClient flaskClient;
    private final HistoryRepository historyRepository;

    @Transactional
    public void createPdf(StorePdfRequest storePdfRequest) {
        String filename = storePdfRequest.filename().replaceFirst("^pdfs/", "");
        PdfEntity pdf = new PdfEntity(filename, storePdfRequest.name());
        pdfRepository.save(pdf);
    }

    @Transactional(readOnly = true)
    public List<PdfNameResponse> getPdfNames() {
        return pdfRepository.findAll().stream()
                .map(pdf -> new PdfNameResponse(pdf.getId(), pdf.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public FlaskResponseDto sendQuery(QueryRequest queryRequest){
        PdfEntity pdf = null;
        try {
            pdf = pdfRepository.findById(queryRequest.pdfId())
                    .orElseThrow(NotFoundException::new);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        FlaskResponseDto response = flaskClient.sendQueryToPython(
                new QueryPythonRequest(queryRequest.query(), pdf.getFilename()));
        HistoryEntity history = new HistoryEntity(queryRequest.query(), response.getOutputVal(), pdf);
        historyRepository.save(history);
        return response;
    }
}
