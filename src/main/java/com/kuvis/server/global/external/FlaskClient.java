package com.kuvis.server.global.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "flaskClient", url = "${flask.url}")
public interface FlaskClient {

    @PostMapping("/process")
    FlaskResponseDto process(@RequestBody FlaskRequestDto requestDto);
}