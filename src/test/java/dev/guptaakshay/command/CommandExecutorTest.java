package dev.guptaakshay.command;

import dev.guptaakshay.dao.LoanDao;
import dev.guptaakshay.dao.LoanDaoImpl;
import dev.guptaakshay.model.Command;
import dev.guptaakshay.service.LoanService;
import dev.guptaakshay.service.LoanServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandExecutorTest {

    private static LoanDao loanDao;
    private static LoanService loanService ;
    private static CommandFactory commandFactory ;

    @BeforeAll
    static void init(){
        loanDao = new LoanDaoImpl();
        loanService = new LoanServiceImpl(loanDao);
        commandFactory = new CommandFactory(loanService);
    }

    @Test
    public void validateLoanCommandExecutorValidateMethod(){
        Command validCommand = new Command("LOAN IDIDI Dale 5000 1 6");
        Command inValidCommand = new Command("LOAN IDIDI Dale 5000 1");

        assertTrue(commandFactory.getCommandExecutor(validCommand).validate(validCommand));
        assertFalse(commandFactory.getCommandExecutor(inValidCommand).validate(inValidCommand));
    }

    @Test
    public void validatePaymentCommandExecutorValidateMethod(){
        Command validCommand = new Command("PAYMENT IDIDI Dale 1000 5");
        Command inValidCommand = new Command("PAYMENT IDIDI Dale 1000");

        assertTrue(commandFactory.getCommandExecutor(validCommand).validate(validCommand));
        assertFalse(commandFactory.getCommandExecutor(inValidCommand).validate(inValidCommand));
    }

    @Test
    public void validateBalanceCommandExecutorValidateMethod(){
        Command validCommand = new Command("BALANCE IDIDI Dale 3");
        Command inValidCommand = new Command("BALANCE IDIDI Dale");

        assertTrue(commandFactory.getCommandExecutor(validCommand).validate(validCommand));
        assertFalse(commandFactory.getCommandExecutor(inValidCommand).validate(inValidCommand));
    }

}