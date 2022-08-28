package com.example.mappings.mappings.utils;

import com.example.mappings.mappings.enums.StatusCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator {



    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @SneakyThrows
    public ResponseEntity<String> generateResponse(StatusCodes status){
        ResponseInfo<String> responseInfo = new ResponseInfo<>();
        responseInfo.setErrorCode(status.getMessageCode());
        responseInfo.setErrorMessage(status.getMessage());
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), status.getStatus());
    }

    @SneakyThrows
    public <T> ResponseEntity<String> generateResponse(T data , HttpStatus status){
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setData(data);
        responseInfo.setTraceId(MDC.get("traceId"));
        return new ResponseEntity<>(mapper.writeValueAsString(responseInfo), status);
    }


}
