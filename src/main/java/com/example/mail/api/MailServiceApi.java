package com.example.mail.api;

import com.example.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class MailServiceApi {
    // 이메일 인증
    private final MailService mailService;

    @PostMapping("{email}")
    @ResponseBody
    public String mailConfirm(@PathVariable String email) throws Exception{
        return mailService.sendSimpleMessage(email);
    }
}