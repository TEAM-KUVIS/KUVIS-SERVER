package com.kuvis.server.api.controller;

import com.kuvis.server.global.external.FlaskClient;
import com.kuvis.server.global.external.FlaskRequestDto;
import com.kuvis.server.global.external.FlaskResponseDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueryController {

    private final FlaskClient flaskClient;

    @PostMapping("/sendToFlask")
    public FlaskResponseDto sendToFlask(@RequestBody FlaskRequestDto requestDto) {
        // Flask 서버로 요청 전송
        return flaskClient.process(requestDto);
    }
}
