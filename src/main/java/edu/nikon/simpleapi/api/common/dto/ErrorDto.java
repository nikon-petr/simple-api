package edu.nikon.simpleapi.api.common.dto;

import java.util.List;

public class ErrorDto {

    private List<String> messages;

    public ErrorDto(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return String.format("ErrorDto{message=%s}", messages);
    }
}
