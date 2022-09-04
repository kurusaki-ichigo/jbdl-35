package com.example.demo.request;

import com.example.demo.entities.idClass.Accounts;
import com.example.demo.enums.ChannelType;
import com.example.demo.entities.embeddable.NotificationId;
import com.example.demo.entities.embeddable.Notifications;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class CreateNotificationDto {

    @NotBlank
    String idempotencyKey;
    @NotBlank
    String source;
    @NotBlank
    String channelValue;
    ChannelType channelType;
    @NotBlank
    String templateId;
    Map<String, String> dynamicFields;



    public Notifications toNotifications(){
        return Notifications.builder()
                .notificationId(new NotificationId(idempotencyKey, source))
                .channelValue(channelValue)
                .channelType(channelType)
                .templateId(templateId)
                .dynamicFields(dynamicFields).build();
    }

    public Accounts toAccounts(){
        return Accounts.builder()
                .idempotencyKey(idempotencyKey)
                .source(source)
                .channelValue(channelValue)
                .channelType(channelType)
                .templateId(templateId)
                .dynamicFields(dynamicFields).build();
    }
}
