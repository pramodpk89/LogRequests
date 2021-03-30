package com.ppk.requestLogger.controller;

import com.ppk.requestLogger.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class RestControllers {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/logRequest")
    public Message logRequest(@RequestHeader MultiValueMap<String, String> headers){
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });

        Message respMessage=new Message();
        respMessage.setId(String.valueOf(System.currentTimeMillis()));
        respMessage.setMessage("Number of hits "+String.valueOf(counter.incrementAndGet()));
        respMessage.setIpAddr(headers.getFirst("host").toString());
        respMessage.setUserAgent(String.valueOf(headers.get("user-agent").get(0)));
        return respMessage;
    }
}
