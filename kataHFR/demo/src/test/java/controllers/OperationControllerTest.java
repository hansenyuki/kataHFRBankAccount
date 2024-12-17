package controllers;

import com.example.kataHFR.bank_account.controllers.OperationController;
import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.enums.OperationType;
import com.example.kataHFR.bank_account.services.service.OperationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OperationControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    private MockMvc mockMvc;

    @Autowired
    public OperationControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(operationController).build();
    }

    @Test
    void testGetOperations_OK() throws Exception {

        Long accountId = 1L;
        List<OperationDto> mockOperations = Arrays.asList(
                new OperationDto(1L, OperationType.DEPOSIT, BigDecimal.valueOf(1000)),
                new OperationDto(2L, OperationType.WITHDRAWAL, BigDecimal.valueOf(500))
        );
        when(operationService.getOperations(accountId)).thenReturn(mockOperations);

        mockMvc.perform(get("/operations/{accountId}", accountId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].amount").value(1000))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].amount").value(500));
    }


    @Test
    public void testGetOperations_NotFound() throws Exception {
        Long accountId = 2L;

        when(operationService.getOperations(accountId)).thenThrow(new RuntimeException("Account not found"));

        mockMvc.perform(get("/operations/{accountId}", accountId))
                .andExpect(status().isNotFound());
    }

}
