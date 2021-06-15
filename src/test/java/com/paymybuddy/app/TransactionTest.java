package com.paymybuddy.app;

import com.paymybuddy.app.exceptions.*;
import com.paymybuddy.app.forms.AddFriendForm;
import com.paymybuddy.app.forms.AddMoneyToBalanceForm;
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


    @BeforeEach
    public void setupTest() throws UserAlreadyCreatedException, LegalAgeException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User user = new User();
        user.setFirstName("noemie");
        user.setLastName("menu");
        user.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        user.setPassword("nm2021");
        user.setBirthdate(LocalDate.parse("29-04-2000", formatter));
        user.setBalance(10);

        authenticationService.registerUser(user);
        this.user = usersRepository.findUserByEmail(user.getEmail());
    }


    @Test
    public void testAddToInternalAccount() {
        //given

        AddMoneyToBalanceForm addMoneyToBalanceForm = new AddMoneyToBalanceForm("lcl", "AZ123");
        addMoneyToBalanceForm.setAmount("10");
        addMoneyToBalanceForm.setWording("azerty");
        double amount = Double.parseDouble(addMoneyToBalanceForm.getAmount());


        //when
        assertDoesNotThrow(() -> transactionService.addToInternalAccount(addMoneyToBalanceForm, this.user));

        //then
        this.user.setBalance(amount);
        assertThat(this.user.getBalance()).isGreaterThan(9);

    }

    @Test
    public void computeTransactionAmountWithFeeTest() {
        //given
        AddMoneyToBalanceForm transactionForm = new AddMoneyToBalanceForm("bank", "aze") {
        };
        transactionForm.setAmount("10");
        transactionForm.setWording("azerty");
        user.setBalance(20);
        double addAmount = Double.parseDouble(transactionForm.getAmount());

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
        AddMoneyToBalanceForm addMoneyToBalanceForm = new AddMoneyToBalanceForm("lcl", "azert");
        addMoneyToBalanceForm.setAmount("0");
        addMoneyToBalanceForm.setWording("azerty");
        double amount = Double.parseDouble(addMoneyToBalanceForm.getAmount());


        //when & then
        assertThrows(NegativeTransactionAmountException.class, () -> transactionService.addToInternalAccount(addMoneyToBalanceForm, user));
    }

    @Test
    public void addToInternalAccount_must_throw_AmountFormatExceptionTest() {
        //given
        AddMoneyToBalanceForm addMoneyToBalanceForm = new AddMoneyToBalanceForm("lcl", "azert");
        addMoneyToBalanceForm.setAmount("aze");
        addMoneyToBalanceForm.setWording("azerty");

        //when & then
        assertThrows(AmountFormatException.class, () -> transactionService.addToInternalAccount(addMoneyToBalanceForm, user));
    }


    @Test
    public void sendToFriendTest() {
        //given
        user.setBalance(20);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var friendRef = new Object() {
            User friend = new User();
        };
        friendRef.friend.setFirstName("noemie");
        friendRef.friend.setLastName("menu");
        friendRef.friend.setEmail(Long.toHexString(Double.doubleToLongBits(Math.random())) + "@gmail.com");
        friendRef.friend.setPassword("nm2021");
        friendRef.friend.setBirthdate(LocalDate.parse("29-04-2000", formatter));
        friendRef.friend.setBalance(0);
        assertDoesNotThrow(() -> authenticationService.registerUser(friendRef.friend));
        friendRef.friend = usersRepository.findUserByEmail(friendRef.friend.getEmail());
        friendRef.friend.setBalance(10);

        AddFriendForm addFriendForm = new AddFriendForm(friendRef.friend.getEmail());
        assertDoesNotThrow(() -> friendService.addFriend(addFriendForm, user));
        user.setFriends(friendsRepository.findFriendsByUserId(user.getId()));

        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm(friendRef.friend.getEmail());
        sendMoneyToFriendForm.setAmount("10");
        sendMoneyToFriendForm.setWording("azerty");
        double amount = Double.parseDouble(sendMoneyToFriendForm.getAmount());

        //when
        assertDoesNotThrow(() -> transactionService.sendToFriend(sendMoneyToFriendForm, user));

        //then
        user.setBalance(user.getBalance());
        BigDecimal bd = BigDecimal.valueOf(user.getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        user.setBalance(bd.doubleValue());
        assertThat(user.getBalance()).isEqualTo(9.95);

        friendRef.friend.setBalance(amount);
        bd = BigDecimal.valueOf(friendRef.friend.getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        friendRef.friend.setBalance(bd.doubleValue());
        assertThat(friendRef.friend.getBalance()).isEqualTo(10.00);

    }

    @Test
    public void sendToFriend_must_throw_NegativeTransactionAmountException() {
        //given
        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm("");
        sendMoneyToFriendForm.setAmount("0");
        sendMoneyToFriendForm.setWording("azerty");
        double amount = Double.parseDouble(sendMoneyToFriendForm.getAmount());
        //when & then
        assertThrows(NegativeTransactionAmountException.class, () -> transactionService.sendToFriend(sendMoneyToFriendForm, user));

    }

    @Test
    public void sendToFriend_must_throw_InsufficientBalanceException() {
        //given

        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm(" ");
        sendMoneyToFriendForm.setAmount("10");
        sendMoneyToFriendForm.setWording("azerty");
        double amount = Double.parseDouble(sendMoneyToFriendForm.getAmount());

        user.setBalance(0);

        //when & then
        assertThrows(InsufficientBalanceException.class, () -> transactionService.sendToFriend(sendMoneyToFriendForm, user));


    }

    @Test
    public void sendToFriend_must_throw_AmountFormatExceptionTest(){

        SendMoneyToFriendForm sendMoneyToFriendForm = new SendMoneyToFriendForm(" ");
        sendMoneyToFriendForm.setAmount("aze");
        sendMoneyToFriendForm.setWording("azerty");

        //when & then
        assertThrows(AmountFormatException.class, () -> transactionService.sendToFriend(sendMoneyToFriendForm, user));

    }
}
