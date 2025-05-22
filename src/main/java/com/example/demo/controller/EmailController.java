package com.example.demo.controller;

import com.example.demo.dto.EmailRequest;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmails(@RequestBody List<EmailRequest> emailRequests) {
        for (EmailRequest request : emailRequests) {
            String subject = "Application for " + request.getPosition() + " Position - Awadhesh Sharma";

            String htmlBody =
                    "<p>Dear Hiring Manager,</p>" +

                            "<p>I was excited to see the <strong>" + request.getPosition() + "</strong> opening at <strong>" + request.getCompany() + "</strong> on LinkedIn and am eager to apply. " +
                            "With over 3+ years of experience in front-end development, I specialize in Angular and have a strong track record " +
                            "of delivering scalable, responsive web applications that drive user engagement and satisfaction.</p>" +

                            "<p>" +
                            "<a href=\"https://drive.google.com/file/d/17fpc_EwLpJCKKFGPfzZiHuOamPHaILri/view\" target=\"_blank\" " +
                            "style=\"display:inline-block; padding:6px; background-color:#28a745; color:white; text-decoration:none; border-radius:5px;\">" +
                            "Download Resume</a>&nbsp;&nbsp;" +
                            "<a href=\"https://awadhesh-portfolio.vercel.app/\" target=\"_blank\" " +
                            "style=\"display:inline-block; padding:6px; background-color:#007bff; color:white; text-decoration:none; border-radius:5px;\">" +
                            "View Portfolio</a>" +
                            "</p>" +

                            "<p>Thank you for considering my application. I look forward to the opportunity to contribute my skills " +
                            "and experience to <strong>" + request.getCompany() + "</strong>.</p>" +

                            "<p>Sincerely,<br>" +
                            "Awadhesh Sharma<br>" +
                            "Mob: +917571869952</p>";

            emailService.sendHtmlEmail(request.getRecipient(), subject, htmlBody);
        }

        return "All HTML emails sent successfully.";
    }
}
