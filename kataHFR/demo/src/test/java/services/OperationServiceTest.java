package services;

import com.example.kataHFR.bank_account.dao.OperationRepository;
import com.example.kataHFR.bank_account.dto.OperationDto;
import com.example.kataHFR.bank_account.mapper.OperationMapper;
import com.example.kataHFR.bank_account.models.Operation;
import com.example.kataHFR.bank_account.services.impl.OperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;


import java.util.List;
import java.util.Optional;

public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private OperationMapper operationMapper;

    @InjectMocks
    private OperationServiceImpl operationService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void getOperations_shouldReturnOperations() {

        Long accountId = 1L;
        Operation operation = new Operation();
        List<Operation> operations = List.of(operation);
        when(operationRepository.findByAccountId(accountId)).thenReturn(Optional.of(operations));

        OperationDto operationDto = new OperationDto();
        when(operationMapper.operationToOperationDto(operation)).thenReturn(operationDto);

        List<OperationDto> result = operationService.getOperations(accountId);

        assertEquals(1, result.size());
        assertEquals(operationDto, result.get(0));
    }
}
