package com.example.userapi.client;

import com.example.userapi.client.mailgun.SendMailForm;
import feign.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandbox91995d712787470eb9211e689fc5550f.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);
}
