package ru.stqa.jpfste.mantis.model;

public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }

    @Override
    public String toString() {
        return "MailMessage [to=" + to + ", text=" + text + "]";
    }
}