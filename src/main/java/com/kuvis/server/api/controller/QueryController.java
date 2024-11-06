package com.kuvis.server.api.controller;

import com.kuvis.server.api.dto.PdfNameResponse;
import com.kuvis.server.api.dto.QueryRequest;
import com.kuvis.server.api.dto.StorePdfRequest;
import com.kuvis.server.api.service.QueryService;
import com.kuvis.server.global.external.FlaskClient;
import com.kuvis.server.global.external.FlaskRequestDto;
import com.kuvis.server.global.external.FlaskResponseDto;
import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryController {

    private final FlaskClient flaskClient;
    private final QueryService queryService;

    @PostMapping("/sendToFlask")
    public FlaskResponseDto sendToFlask(@RequestBody FlaskRequestDto requestDto) {
        // Flask 서버로 요청 전송
        return flaskClient.process(requestDto);
    }

    @PostMapping("/store")
    public ResponseEntity<Void> storePdf(
            @RequestBody StorePdfRequest storePdfRequest
            ){
        flaskClient.downloadPdf(storePdfRequest.filename());
        queryService.createPdf(storePdfRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pdfs")
    public List<PdfNameResponse> getPdfs() {
        // Flask 서버로 요청 전송
        return queryService.getPdfNames();
    }

    @PostMapping("/query")
    public FlaskResponseDto sendQuery(
            @RequestBody QueryRequest queryRequest
            ) {
        return queryService.sendQuery(queryRequest);
    }
}
