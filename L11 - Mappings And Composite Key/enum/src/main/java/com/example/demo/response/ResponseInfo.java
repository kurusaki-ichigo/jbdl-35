package com.example.demo.response;

import com.example.demo.enums.StatusCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo<T> {

    T data;
    String errorMessage;
    String errorCode;
    String traceId;



    public ResponseInfo(StatusCodes statusCodes){
        this.errorCode = statusCodes.getMessageCode();
        this.errorMessage = statusCodes.getMessage();
        this.traceId = MDC.get("traceId");
    }




}
