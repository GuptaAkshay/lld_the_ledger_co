package dev.guptaakshay.mode;

import dev.guptaakshay.command.CommandFactory;
import dev.guptaakshay.model.Command;

import java.io.*;

public class FileMode extends Mode{
    private String fileName;

    public FileMode(CommandFactory commandFactory, String fileName) {
        super(commandFactory);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        File file = new File(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}
