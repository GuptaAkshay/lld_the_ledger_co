package dev.guptaakshay.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final String SPACE = " ";
    private String command;
    private List<String> params;

    public String getCommand() {
        return command;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(String commandString) {
        List<String> tokens = Arrays.asList(commandString.trim().split(SPACE)).stream()
                .map(String::trim).filter(token -> token.length() > 0).collect(Collectors.toList());

        command = tokens.get(0).toUpperCase();
        tokens.remove(0);
        params = tokens;
    }
}
