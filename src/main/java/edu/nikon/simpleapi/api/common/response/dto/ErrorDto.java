package edu.nikon.simpleapi.api.common.response.dto;

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
        final StringBuilder sb = new StringBuilder("ErrorDto{");
        sb.append("messages=").append(messages);
        sb.append('}');
        return sb.toString();
    }
}
