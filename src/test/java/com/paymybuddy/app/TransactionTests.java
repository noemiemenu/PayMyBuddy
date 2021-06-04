package com.paymybuddy.app;

import com.paymybuddy.app.enums.TransactionType;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.models.ExternalBankAccount;
import com.paymybuddy.app.models.InternalBankAccount;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.ExternalBankAccountsRepository;
import com.paymybuddy.app.repositories.InternalBankAccountRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class TransactionTests {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExternalBankAccountsRepository externalBankAccountsRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private InternalBankAccountRepository internalBankAccountRepository;

    private User user;


    @BeforeEach
    public void setupTest() throws UserAlreadyCreatedException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail("nm@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2019", formatter));


        authenticationService.registerUser(user);
        this.user = usersRepository.findUserByEmail(user.getEmail());

    }



    @Test
    public void testAddToInternalAccount() {
        //given
        InternalBankAccount internalBankAccount = new InternalBankAccount();
        internalBankAccount.setBalance(12);
        internalBankAccount.setUser(user);

        ExternalBankAccount externalBankAccount = new ExternalBankAccount();
        externalBankAccount.setUser(user);
        externalBankAccount.setName("lcl");
        externalBankAccount.setRib("12345");

        Transaction transaction = new Transaction();
        transaction.setWording("aze");
        transaction.setDate(LocalDateTime.now());
        transaction.setInternalBankAccount(internalBankAccount);
        transaction.setExternalBankAccount(externalBankAccount);
        transaction.setReceiver(user);
        transaction.setType(TransactionType.ADD_TO_INTERNAL_ACCOUNT);
        transaction.setAmount(23);

        internalBankAccount.setBalance(internalBankAccount.getBalance() + transaction.getAmount());

        //then
        assertThat(internalBankAccount.getBalance()).isEqualTo(35);
        assertNotNull(transaction);
        assertNotNull(internalBankAccount);
        assertNotNull(externalBankAccount);

    }
}
