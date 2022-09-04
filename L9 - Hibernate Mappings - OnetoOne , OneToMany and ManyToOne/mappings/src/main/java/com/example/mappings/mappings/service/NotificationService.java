package com.example.mappings.mappings.service;

import com.example.mappings.mappings.requests.CreateNotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class NotificationService {

    /**
     * "{\n    \"idempotencyKey\" : \"12345678\",\n    \"source\" : \"user service\",\n    \"channelValue\" : \"9911991199\",\n    \"channelType\" : \"SMS\",\n    \"templateId\" : \"template1\",\n    \"dynamicFields\": {\n        \"name\" : \"Joey\",\n        \"amount\" : \"100\",\n        \"currency\" : \"USD\"\n    }\n\n}"
     */

    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    @SneakyThrows
    public void sendNotification(String source) {
        CreateNotificationRequest createNotificationRequest = CreateNotificationRequest.builder()
                .idempotencyKey(UUID.randomUUID().toString())
                .source(source)
                // fetch from user the channel value
                .channelType("SMS")
                .channelValue("9911991199")
                .templateId("tempalte1")
                .dynamicFields(new HashMap<>())
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(createNotificationRequest));
        Request request = new Request.Builder()
                .url("http://localhost:8081/v2/notifications")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        log.info(" response received {} ", response);
    }
}
