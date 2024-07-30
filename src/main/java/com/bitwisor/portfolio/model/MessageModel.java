package com.bitwisor.portfolio.model;

public class MessageModel {
    private  String email;
    private String content;
    private  String subject;

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
