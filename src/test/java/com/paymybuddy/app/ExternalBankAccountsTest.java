package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.BankAccountAlreadyCreatedException;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.forms.NewExternalBankAccountForm;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.ExternalBankAccountsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.ExternalBankAccountsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class ExternalBankAccountsTest {

    @Autowired
    private ExternalBankAccountsService externalBankAccountsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExternalBankAccountsRepository externalBankAccountsRepository;

    private User user;

    @BeforeEach
    public void setupTest() throws UserAlreadyCreatedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));

        authenticationService.registerUser(user);
        this.user = usersRepository.findUserByEmail(user.getEmail());
    }


    @Test
    public void addBankAccountTest() {
        NewExternalBankAccountForm newExternalBankAccountForm = new NewExternalBankAccountForm("lcl", "123456789");

        assertDoesNotThrow(() -> externalBankAccountsService.addBankAccount(newExternalBankAccountForm, user));
        Collection<ExternalBankAccount> externalBankAccounts = externalBankAccountsRepository.findExternalBankAccountsByUserId(user.getId());
        ExternalBankAccount externalBankAccount = ((List<ExternalBankAccount>) externalBankAccounts).get(0);


        assertNotNull(externalBankAccount);
        assertEquals(externalBankAccount.getName(), "lcl");
        assertEquals(externalBankAccount.getRib(), "123456789");
    }

    @Test
    public void deleteBankAccountTest() {
        NewExternalBankAccountForm newExternalBankAccountForm = new NewExternalBankAccountForm("lcl", "123456789");
        assertDoesNotThrow(() -> externalBankAccountsService.addBankAccount(newExternalBankAccountForm, user));
        Collection<ExternalBankAccount> externalBankAccounts = externalBankAccountsRepository.findExternalBankAccountsByUserId(user.getId());
        ExternalBankAccount externalBankAccount = ((List<ExternalBankAccount>) externalBankAccounts).get(0);

        externalBankAccountsService.deleteBankAccount(externalBankAccount.getId());

        externalBankAccounts = externalBankAccountsRepository.findExternalBankAccountsByUserId(user.getId());

        assertTrue(externalBankAccounts.isEmpty());
    }

    @Test
    public void addToBankAccount_must_throw_BankAccountAlreadyCreatedException(){
        NewExternalBankAccountForm newExternalBankAccountForm = new NewExternalBankAccountForm("lcl", "123456789");
        assertDoesNotThrow(() -> externalBankAccountsService.addBankAccount(newExternalBankAccountForm, user));
        this.user.setExternalBankAccounts(externalBankAccountsRepository.findExternalBankAccountsByUserId(user.getId()));
        assertThrows(BankAccountAlreadyCreatedException.class, () -> externalBankAccountsService.addBankAccount(newExternalBankAccountForm, user));

    }
}
