package dev.guptaakshay;

import dev.guptaakshay.command.CommandFactory;
import dev.guptaakshay.dao.LoanDao;
import dev.guptaakshay.dao.LoanDaoImpl;
import dev.guptaakshay.mode.FileMode;
import dev.guptaakshay.service.LoanService;
import dev.guptaakshay.service.LoanServiceImpl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        LoanDao loanDao = new LoanDaoImpl();
        LoanService loanService = new LoanServiceImpl(loanDao);
        CommandFactory commandFactory = new CommandFactory(loanService);
        new FileMode(commandFactory, args[0]).process();
    }
}
