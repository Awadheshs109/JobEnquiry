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

            String htmlBody = String.format("""
                        <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; font-size: 15px; color: #333; line-height: 1.6;">
                            <p style="margin-bottom: 20px;">Dear Hiring Manager,</p>
                    
                            <p style="margin-bottom: 20px;">
                                I was excited to see the <strong>%s</strong> opening at <strong>%s</strong> on LinkedIn and am eager to apply.
                                With over 3+ years of experience in front-end development, I specialize in Angular and have a strong track record
                                of delivering scalable, responsive web applications that drive user engagement and satisfaction.
                            </p>
                    
                            <p style="margin-bottom: 20px;">
                                <a href="https://drive.google.com/file/d/17fpc_EwLpJCKKFGPfzZiHuOamPHaILri/view" target="_blank"
                                   style="display:inline-block; padding:10px 20px; background-color:#28a745; color:white; 
                                   text-decoration:none; border-radius:4px; font-weight: bold; margin-right: 10px;">📄 Download Resume</a>
                                <a href="https://awadhesh-portfolio.vercel.app/" target="_blank"
                                   style="display:inline-block; padding:10px 20px; background-color:#007bff; color:white; 
                                   text-decoration:none; border-radius:4px; font-weight: bold;">🌐 View Portfolio</a>
                            </p>
                    
                            <p style="margin-bottom: 20px;">
                                Thank you for considering my application. I look forward to the opportunity to contribute my skills and experience to <strong>%s</strong>.
                            </p>
                    
                            <p style="margin-top: 30px;">
                                Sincerely,<br>
                                <strong>Awadhesh Sharma</strong><br>
                                Mob: +91-7571869952
                            </p>
                        </div>
                    """, request.getPosition(), request.getCompany(), request.getCompany());


            emailService.sendHtmlEmail(request.getRecipient(), subject, htmlBody);
        }

        return "All HTML emails sent successfully.";
    }
}
