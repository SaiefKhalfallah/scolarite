package com.example.st2i.service;

import com.example.st2i.exception.EmailNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class EmailService {
    @Autowired(required=true)
    private JavaMailSender mailSender;

    @Autowired(required=true)
    private TemplateEngine templateEngine;


    public void sendEmailNotifConfirmation(
           // String from,
            String to,
            String subject,
            LocalDate date,
            //String firstname,
            String status,
            String teacherFirstName,
            String teacherLastName,
            String studentFirstName,
            String studentLastName,
            Date absenceDate,
            String absenceTime,
            String studentClass,
            String matier
    ) throws MessagingException, IOException {
        String from="";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("yassin2016.attoui@gmail.com");
//        helper.setReplyTo(new InternetAddress("yassineatoui95@gmail.com"));
        helper.setTo(to);
        helper.setSubject(subject);

        Context context = new Context();
       // context.setVariable("firstname", firstname);
        context.setVariable("date", date);
        context.setVariable("status", status);
        context.setVariable("teacherFirstName", teacherFirstName);
        context.setVariable("teacherLastName", teacherLastName);
        context.setVariable("studentFirstName", studentFirstName);
        context.setVariable("studentLastName", studentLastName);
        context.setVariable("absenceDate", absenceDate);
        context.setVariable("absenceTime", absenceTime);
        context.setVariable("studentClass", studentClass);
        context.setVariable("matier", matier);
        String htmlContent = templateEngine.process("email", context);

        helper.setText(htmlContent, true);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            if (e.getCause() instanceof jakarta.mail.SendFailedException) {
                throw new EmailNotFoundException("The from email address is not found: " + from);
            }
            throw e;
        }
    }


    public void sendEmailSanctionConfirmation(
            // String from,
            String to,
            String subject,
            LocalDate date,
            //String firstname,
            String status,
            String teacherFirstName,
            String teacherLastName,
            String studentFirstName,
            String studentLastName,
            LocalDateTime sanctionDate,
            String type,
            String studentClass,
            String description
    ) throws MessagingException, IOException {
        String from="";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("yassin2016.attoui@gmail.com");
//        helper.setReplyTo(new InternetAddress("yassineatoui95@gmail.com"));
        helper.setTo(to);
        helper.setSubject(subject);

        Context context = new Context();
        // context.setVariable("firstname", firstname);
        context.setVariable("date", date);
        context.setVariable("status", status);
        context.setVariable("teacherFirstName", teacherFirstName);
        context.setVariable("teacherLastName", teacherLastName);
        context.setVariable("studentFirstName", studentFirstName);
        context.setVariable("studentLastName", studentLastName);
        context.setVariable("sanctionDate", sanctionDate);
        context.setVariable("type", type);
        context.setVariable("studentClass", studentClass);
        context.setVariable("description", description);
        String htmlContent = templateEngine.process("emailsanction", context);

        helper.setText(htmlContent, true);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            if (e.getCause() instanceof jakarta.mail.SendFailedException) {
                throw new EmailNotFoundException("The from email address is not found: " + from);
            }
            throw e;
        }
    }

}
