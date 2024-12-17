package controllers;

import com.example.kataHFR.bank_account.controllers.AccountController;
import com.example.kataHFR.bank_account.exception.DepositException;
import com.example.kataHFR.bank_account.exception.WithdrawalException;
import com.example.kataHFR.bank_account.models.Account;
import com.example.kataHFR.bank_account.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;

public class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountServiceImpl accountService;

    @Autowired
    private MockMvc mockMvc;

    private static final Long ACCOUNT_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    @DisplayName("Should return the account and statut OK when the id exists")
    public void testGetAccountById_OK() throws Exception {
        Account mockAccount = new Account(1L,  new BigDecimal("1000.00"));
        when(accountService.getAccountById(ACCOUNT_ID)).thenReturn(mockAccount);

        MockHttpServletResponse response = mockMvc.perform(
                get("/account/1")).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("{\"id\":1,\"balance\":1000.00}", response.getContentAsString());
    }

    @Test
    @DisplayName("Should return Not Found when the id does not exist")
    public void testGetAccountById_KO() throws Exception {
        Long accountId = 2L;
        when(accountService.getAccountById(accountId)).thenThrow(new RuntimeException("Account not found"));

        MockHttpServletResponse response = mockMvc.perform(
                get("/account/2")).andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should return 200 OK when deposit OK")
    public void testDepositAmount_OK() throws Exception {
        BigDecimal amount = new BigDecimal("100.00");
        doNothing().when(accountService).makeDeposit(eq(ACCOUNT_ID), eq(amount));

        MockHttpServletResponse response = mockMvc
                .perform(post("/account/{id}/deposit", ACCOUNT_ID)
                        .param("amount", amount.toString()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Deposit successful", response.getContentAsString());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when deposit fails")
    public void testDepositAmount_KO() throws Exception {
        BigDecimal amount = new BigDecimal("-50.00");

        doThrow(new DepositException("Deposit amount must be greater than zero")).when(accountService).makeDeposit(eq(ACCOUNT_ID), eq(amount));

        MockHttpServletResponse response = mockMvc
                .perform(post("/account/{id}/deposit", ACCOUNT_ID)
                        .param("amount", amount.toString()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Deposit failed: Deposit amount must be greater than zero", response.getContentAsString());
    }

    @Test
    @DisplayName("Should return 200 OK when Withdraw OK")
    public void testWithdrawAmount_OK() throws Exception {
        BigDecimal amount = new BigDecimal("1000.00");

        doNothing().when(accountService).makeWithdraw(eq(ACCOUNT_ID), eq(amount));

        MockHttpServletResponse response = mockMvc
                .perform(post("/account/{id}/withdraw", ACCOUNT_ID)
                        .param("amount", amount.toString()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Withdrawal successful", response.getContentAsString());
    }

    @Test
    @DisplayName("Should return 400 Bad Request when Withdraw fails")
    public void testWithdraw_KO() throws Exception {
        BigDecimal amount = new BigDecimal("-50.00");

        doThrow(new WithdrawalException("Withdrawal amount must be greater than zero")).when(accountService)
                .makeWithdraw(eq(ACCOUNT_ID), eq(amount));

        MockHttpServletResponse response = mockMvc
                .perform(post("/account/{id}/withdraw", ACCOUNT_ID)
                        .param("amount", amount.toString()))
                .andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Withdrawal failed: Withdrawal amount must be greater than zero", response.getContentAsString());
    }
}
