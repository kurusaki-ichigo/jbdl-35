package com.example.mappings.mappings.requests;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CreateNotificationRequest {

    String idempotencyKey;
    String source;
    String channelValue;
    String channelType;
    String templateId;
    Map<String, String> dynamicFields;

}
