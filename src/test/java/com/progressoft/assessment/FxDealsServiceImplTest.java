package com.progressoft.assessment;

import com.progressoft.assessment.data.enums.Status;
import com.progressoft.assessment.data.model.BaseResponse;
import com.progressoft.assessment.data.model.FXDeals;
import com.progressoft.assessment.data.repository.FXDealsRepository;
import com.progressoft.assessment.dto.request.FXDealsRequest;
import com.progressoft.assessment.service.FxDealsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class FxDealsServiceImplTest {
    @Mock
    private FXDealsRepository fxDealsRepository;

    @InjectMocks
    private FxDealsServiceImpl fxDealsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDeals_UniqueDeal_Success() {
        FXDealsRequest request = new FXDealsRequest("uniqueId", "USD", "EUR", new BigDecimal("100.00"));
        when(fxDealsRepository.existsByUniqueDealId(anyString())).thenReturn(false);
        when(fxDealsRepository.save(any(FXDeals.class))).thenAnswer(invocation -> {
            FXDeals savedDeal = invocation.getArgument(0);
            savedDeal.setId(1L);
            return savedDeal;
        });

        BaseResponse<?> response = fxDealsService.saveDeals(request);

        assertEquals(Status.CREATED, response.getStatus());
        assertEquals("Deal saved successfully", response.getMessage());
        assertEquals("00", response.getResponseCode());
        assertNotNull(response.getData());
        FXDeals savedDeal = (FXDeals) response.getData();
        assertEquals(request.getDealUniqueId(), savedDeal.getUniqueDealId());
        assertEquals(request.getOrderingCurrencyISOCode(), savedDeal.getOrderingCurrencyISOCode());
        assertEquals(request.getToCurrencyISOCode(), savedDeal.getToCurrencyISOCode());
        assertEquals(request.getAmount(), savedDeal.getAmount());
        assertNotNull(savedDeal.getTimeStamp());
    }

    @Test
    public void testSaveDeals_DuplicateDeal_Failure() {
        FXDealsRequest request = new FXDealsRequest("existingId", "USD", "EUR", new BigDecimal("100.00"));
        when(fxDealsRepository.existsByUniqueDealId(anyString())).thenReturn(true);

        BaseResponse<?> response = fxDealsService.saveDeals(request);

        assertEquals(Status.FAILED, response.getStatus());
        assertEquals("Duplicate deal found", response.getMessage());
        assertEquals("07", response.getResponseCode());
        assertNull(response.getData());
    }

    @Test
    public void testSaveDeals_InternalError_Failure() {

        FXDealsRequest request = new FXDealsRequest("validId", "USD", "EUR", new BigDecimal("100.00"));
        when(fxDealsRepository.existsByUniqueDealId(anyString())).thenThrow(new RuntimeException("DB connection error"));

        BaseResponse<?> response = fxDealsService.saveDeals(request);

        assertEquals(Status.INTERNAL_ERROR, response.getStatus());
        assertEquals("An error occurred while saving FX Deal", response.getMessage());
        assertEquals("99", response.getResponseCode());
        assertNull(response.getData());
    }
}
