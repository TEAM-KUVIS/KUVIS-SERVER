package com.kuvis.server.global.external;

import lombok.Getter;

@Getter
public class QueryPythonRequest {
    private String query;
    private String file_name;

    public QueryPythonRequest(String query, String file_name) {
        this.query = query;
        this.file_name = file_name;
    }
}