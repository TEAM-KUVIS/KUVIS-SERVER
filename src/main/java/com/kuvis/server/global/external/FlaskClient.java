package com.kuvis.server.global.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "flaskClient", url = "${flask.url}")
public interface FlaskClient {

    @PostMapping("/process")
    FlaskResponseDto process(@RequestBody FlaskRequestDto requestDto);

    @GetMapping("/download-pdf")
    void downloadPdf(@RequestParam("file_name") String fileName);

    @PostMapping("/query")
    FlaskResponseDto sendQueryToPython(@RequestBody QueryPythonRequest queryPythonRequest);
}