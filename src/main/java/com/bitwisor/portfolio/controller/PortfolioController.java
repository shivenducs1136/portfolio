package com.bitwisor.portfolio.controller;

import com.bitwisor.portfolio.model.MessageModel;
import com.bitwisor.portfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController
public class PortfolioController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/message")
    public ResponseEntity<String> newMessage(@RequestBody MessageModel msgModel) {
        try{
            boolean b  =emailService.sendEmail(msgModel.getEmail(),msgModel.getSubject(), msgModel.getContent());
            if( b ){
                return new ResponseEntity<>("Email sent", HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Email sending failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (InvalidParameterException e){
            return new ResponseEntity<>("Email provided is not found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
