package org.cubeville.commons.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandResponse {
    private List<String> messages;
    private boolean baseMessageSet;

    public CommandResponse() {
        messages = new ArrayList<>();
        baseMessageSet = false;
    }
        
    public CommandResponse(String message) {
        messages = new ArrayList<>();
        messages.add(message);
        baseMessageSet = true;
    }
    
    public CommandResponse(String... messages) {
    	this.messages = new ArrayList<>();
    	for(String message: messages) {
    		this.messages.add(message);
    	}
    	baseMessageSet = true;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
        
    public void setBaseMessage(String message) {
        if(baseMessageSet) {
            messages.set(0, message);
        }
        else {
            messages.add(0, message);
            baseMessageSet = true;
        }
    }

    public List<String> getMessages() {
        if(messages.size() == 0) return null;
        return messages;
    }
}
    
