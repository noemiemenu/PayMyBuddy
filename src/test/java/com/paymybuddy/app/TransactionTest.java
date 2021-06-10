package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.InsufficientBalanceException;
import com.paymybuddy.app.exceptions.NegativeTransactionAmountException;
import com.paymybuddy.app.exceptions.UserAlreadyCreatedException;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.forms.SendMoneyToFriendForm;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repositories.FriendsRepository;
import com.paymybuddy.app.repositories.UsersRepository;
import com.paymybuddy.app.services.interfaces.AuthenticationService;
import com.paymybuddy.app.services.interfaces.FriendService;
import com.paymybuddy.app.services.interfaces.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class TransactionTest {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private TransactionService transactionService;


    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendsRepository friendsRepository;

    private User user;


   /* @BeforeEach
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
        this.user.setInternalBankAccount(internalBankAccountRepository.findByUserId(this.user.getId()));
    }


    @Test
    public void testAddToInternalAccount() {
        //given
        ExternalBankAccount externalBankAccount = new ExternalBankAccount();
        externalBankAccount.setName("lcl");
        externalBankAccount.setRib("123456");
        externalBankAccount.setUser(user);
        externalBankAccount = externalBankAccountsRepository.save(externalBankAccount);
        this.user.setExternalBankAccounts(Arrays.asList(externalBankAccount));
        TransactionForm transactionForm = new TransactionForm(externalBankAccount.getId());
        transactionForm.setAmount(10);
        transactionForm.setWording("azerty");

        //when
        assertDoesNotThrow(() -> transactionService.addToInternalAccount(transactionForm, this.user));

        //then
        this.user.setInternalBankAccount(internalBankAccountRepository.findByUserId(this.user.getId()));
        assertThat(this.user.getInternalBankAccount().getBalance()).isGreaterThan(0);

    }

    @Test
    public void computeTransactionAmountWithFeeTest() {
        //given
        TransactionForm transactionForm = new TransactionForm(0);
        transactionForm.setAmount(10);
        transactionForm.setWording("azerty");
        user.getInternalBankAccount().setBalance(20);

        //when
        double amount = transactionService.computeTransactionAmountWithFee(user, transactionForm);
        BigDecimal bd = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
        amount = bd.doubleValue();

        //Then
        assertThat(amount).isEqualTo(9.95);

    }

    @Test
    public void addToInternalAccount_must_throw_NegativeTransactionAmountException() {
        //given
        TransactionForm transactionForm = new TransactionForm(0);
        transactionForm.setAmount(0);
        transactionForm.setWording("azerty");

        //when & then
        assertThrows(NegativeTransactionAmountException.class, () -> transactionService.addToInternalAccount(transactionForm, user));
    }

    @Test
    public void sendToExternalBankAccountTest() {
        //given
        ExternalBankAccount externalBankAccount = new ExternalBankAccount();
        externalBankAccount.setName("lcl");
        externalBankAccount.setRib("123456");
        externalBankAccount.setUser(user);
        externalBankAccount = externalBankAccountsRepository.save(externalBankAccount);
        this.user.setExternalBankAccounts(Arrays.asList(externalBankAccount));
        TransactionForm transactionForm = new TransactionForm(externalBankAccount.getId());
        transactionForm.setAmount(10);
        transactionForm.setWording("azerty");
        user.getInternalBankAccount().setBalance(20);
        //when
        assertDoesNotThrow(() -> transactionService.sendToExternalBankAccount(transactionForm, user));

        //then
        this.user.setInternalBankAccount(internalBankAccountRepository.findByUserId(this.user.getId()));
        BigDecimal bd = BigDecimal.valueOf(user.getInternalBankAccount().getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        user.getInternalBankAccount().setBalance(bd.doubleValue());
        assertThat(user.getInternalBankAccount().getBalance()).isEqualTo(9.95);
    }

    @Test
    public void sendToExternalBankAccount_must_throw_NegativeTransactionAmountException() {
        //given
        TransactionForm transactionForm = new TransactionForm(0);
        transactionForm.setAmount(0);
        transactionForm.setWording("azerty");

        //when & then
        assertThrows(NegativeTransactionAmountException.class, () -> transactionService.sendToExternalBankAccount(transactionForm, user));

    }

    @Test
    public void sendToExternalBankAccount_must_throw_InsufficientBalanceException() {
        //given
        TransactionForm transactionForm = new TransactionForm(0);
        transactionForm.setAmount(10);
        transactionForm.setWording("azerty");
        user.getInternalBankAccount().setBalance(0);

        //when & then
        assertThrows(InsufficientBalanceException.class, () -> transactionService.sendToExternalBankAccount(transactionForm, user));


    }

    @Test
    public void sendToFriendTest() {
        //given
        user.getInternalBankAccount().setBalance(20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var friendRef = new Object() {
            User friend = new User();
        };
        friendRef.friend.setFirstName("noemie");
        friendRef.friend.setLastName("menu");
        friendRef.friend.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        friendRef.friend.setPassword("nm2021");
        friendRef.friend.setBirthdate(LocalDate.parse("29-04-2019", formatter));
        assertDoesNotThrow(() -> authenticationService.registerUser(friendRef.friend));
        friendRef.friend = usersRepository.findUserByEmail(friendRef.friend.getEmail());
        friendRef.friend.setInternalBankAccount(internalBankAccountRepository.findByUserId(friendRef.friend.getId()));
        AddFriendForm addFriendForm = new AddFriendForm(friendRef.friend.getEmail());
        assertDoesNotThrow(() -> friendService.addFriend(addFriendForm, user));
        user.setFriends(friendsRepository.findFriendsByUserId(user.getId()));

        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm(friendRef.friend.getEmail());
        sendMoneyToFriendForm.setAmount(10);
        sendMoneyToFriendForm.setWording("azerty");

        //when
        assertDoesNotThrow(() -> transactionService.sendToFriend(sendMoneyToFriendForm, user));
        //then
        user.setInternalBankAccount(internalBankAccountRepository.findByUserId(user.getId()));
        BigDecimal bd = BigDecimal.valueOf(user.getInternalBankAccount().getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        user.getInternalBankAccount().setBalance(bd.doubleValue());
        assertThat(user.getInternalBankAccount().getBalance()).isEqualTo(9.95);

        friendRef.friend.setInternalBankAccount(internalBankAccountRepository.findByUserId(friendRef.friend.getId()));
        bd = BigDecimal.valueOf(friendRef.friend.getInternalBankAccount().getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        friendRef.friend.getInternalBankAccount().setBalance(bd.doubleValue());
        assertThat(friendRef.friend.getInternalBankAccount().getBalance()).isEqualTo(10.00);

    }

    @Test
    public void sendToFriend_must_throw_NegativeTransactionAmountException() {
        //given
        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm("");
        sendMoneyToFriendForm.setAmount(0);
        sendMoneyToFriendForm.setWording("azerty");

        //when & then
        assertThrows(NegativeTransactionAmountException.class, () -> transactionService.sendToFriend(sendMoneyToFriendForm, user));

   // }

    //@Test
    public void sendToFriend_must_throw_InsufficientBalanceException() {
        //given
        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm(" ");
        sendMoneyToFriendForm.setAmount(10);
        sendMoneyToFriendForm.setWording("azerty");

        user.getInternalBankAccount().setBalance(0);

        //when & then
        assertThrows(InsufficientBalanceException.class, () -> transactionService.sendToFriend(sendMoneyToFriendForm, user));


     }
 }
*/