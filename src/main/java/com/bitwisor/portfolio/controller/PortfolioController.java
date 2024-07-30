package com.bitwisor.portfolio.controller;

import com.bitwisor.portfolio.model.MessageModel;
import com.bitwisor.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/message")
    public ResponseEntity<String> newMessage(@RequestBody MessageModel msgModel) {
        if( emailService.sendEmail(msgModel.getEmail(),msgModel.getSubject(), msgModel.getContent())){
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Email sending failed", HttpStatus.BAD_REQUEST);
        }
    }

}
