package dev.guptaakshay.command;

import dev.guptaakshay.dao.LoanDao;
import dev.guptaakshay.dao.LoanDaoImpl;
import dev.guptaakshay.exception.InvalidCommandException;
import dev.guptaakshay.model.Command;
import dev.guptaakshay.service.LoanService;
import dev.guptaakshay.service.LoanServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {
    private static LoanDao loanDao;
    private static LoanService loanService ;
    private static CommandFactory commandFactory ;

    @BeforeAll
    static void init() {
        loanDao = new LoanDaoImpl();
        loanService = new LoanServiceImpl(loanDao);
        commandFactory = new CommandFactory(loanService);
    }

    @Test
    public void getCommandValid(){
        Command balance = new Command("BALANCE IDIDI Dale 3");
        Command payment = new Command("PAYMENT IDIDI Dale 1000 5");
        Command loan = new Command("LOAN IDIDI Dale 5000 1 6");
        assertNotNull(commandFactory.getCommandExecutor(loan));
        assertNotNull(commandFactory.getCommandExecutor(balance));
        assertNotNull(commandFactory.getCommandExecutor(payment));
    }

    @Test
    public void getCommandInValid(){
        Command hello = new Command("HELLO IDIDI Dale 3");
        assertThrows(InvalidCommandException.class, () ->commandFactory.getCommandExecutor(hello));
    }
}