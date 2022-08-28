package com.example.mappings.mappings.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Getter
@Setter
@ToString
public class UserConfiguration implements InitializingBean {

    @Value("${user.quota}")
    Integer quota;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(" user properties {} ", this);
    }
}
