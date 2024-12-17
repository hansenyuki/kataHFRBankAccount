package services;

import com.example.kataHFR.bank_account.dao.AccountRepository;
import com.example.kataHFR.bank_account.dao.OperationRepository;
import com.example.kataHFR.bank_account.enums.OperationType;
import com.example.kataHFR.bank_account.exception.DepositException;
import com.example.kataHFR.bank_account.exception.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.models.Operation;
import com.example.kataHFR.bank_account.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    OperationRepository operationRepository;
    private final static Long accountId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Optional<Account> getMockedAccount() {
        return Optional.of(Account.builder()
                .id(1L)
                .balance(new BigDecimal("1000.00"))
                .build());
    }

    private Optional<Operation> getMockedDepositOperation() {
        return Optional.of(Operation.builder()
                .date(LocalDateTime.now())
                .type(OperationType.DEPOSIT)
                .amount(new BigDecimal("1000.00"))
                .balanceUpdated(new BigDecimal("2000.00"))
                .account(new Account(1L, new BigDecimal("1000.00")))
                .build());
    }

    @Test
    @DisplayName("deposit should update balance")
    void deposit_shouldUpdateBalance() throws DepositException {

        Optional<Account> mockedAccount = getMockedAccount();
        when(accountRepository.findById(accountId)).thenReturn(mockedAccount);

        // When
        accountService.makeDeposit(mockedAccount.get().getId(), mockedAccount.get().getBalance());

        // Then
        verify(accountRepository, times(1)).save(mockedAccount.get());
    }

    @Test
    @DisplayName("deposit should throwException when amount is negative")
    void deposit_shouldThrowException_whenAmountIsNegative() {
        // Given
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal("-1.00");
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(new BigDecimal("0.00"));

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        assertThrows(DepositException.class, () -> accountService.makeDeposit(accountId, amount));
    }

    @Test
    @DisplayName("deposit should throwException when account not found")
    void deposit_shouldThrowException_whenAccountNotFound() {

        Long accountId = 1L;
        BigDecimal amount = new BigDecimal("1000.00");;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> accountService.makeDeposit(accountId, amount));
    }

    @Test
    @DisplayName("withdraw should update balance")
    void withdraw_shouldUpdateBalance() throws WithdrawalException {

        Optional<Account> mockedAccount = getMockedAccount();
        when(accountRepository.findById(accountId)).thenReturn(mockedAccount);

        accountService.makeWithdraw(mockedAccount.get().getId(), mockedAccount.get().getBalance());

        verify(accountRepository, times(1)).save(mockedAccount.get());
    }

    @Test
    @DisplayName("withdraw should throwException when amount is negative")
    void withdraw_shouldThrowException_whenAmountIsNegative() {

        Long accountId = 1L;
        BigDecimal amount = new BigDecimal("-1.00");

        Optional<Account> mockedAccount = getMockedAccount();

        when(accountRepository.findById(accountId)).thenReturn(mockedAccount);

        assertThrows(WithdrawalException.class, () -> accountService.makeWithdraw(accountId, amount));
    }

    @Test
    @DisplayName("withdraw should throwException when balance is insufficient")
    void withdraw_shouldThrowException_whenBalanceInsufficient() {
        // Given
        Long accountId = 1L;
        BigDecimal amount = new BigDecimal("5000.00");
        Optional<Account> mockedAccount = getMockedAccount();

        when(accountRepository.findById(accountId)).thenReturn(mockedAccount);

        assertThrows(WithdrawalException.class, () -> accountService.makeWithdraw(accountId, amount));
    }
}
