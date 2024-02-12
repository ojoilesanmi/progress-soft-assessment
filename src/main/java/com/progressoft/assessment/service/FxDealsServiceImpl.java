package com.progressoft.assessment.service;

import com.progressoft.assessment.data.enums.ErrorCode;
import com.progressoft.assessment.data.enums.Status;
import com.progressoft.assessment.data.model.BaseResponse;
import com.progressoft.assessment.data.model.FXDeals;
import com.progressoft.assessment.data.repository.FXDealsRepository;
import com.progressoft.assessment.dto.request.FXDealsRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.util.Date;


@RequiredArgsConstructor
public class FxDealsServiceImpl implements FXDealsService{

    private final FXDealsRepository fxDealsRepository;
    private static final Logger log = LoggerFactory.getLogger(FXDealsService.class);
    @Override
    public BaseResponse<?> saveDeals(FXDealsRequest fxDealsRequest) {
        try{
            if(isDealUniqueIdUnique(fxDealsRequest.getDealUniqueId())){
                FXDeals fxDeals = buildFXDeal(fxDealsRequest);
                fxDealsRepository.save(fxDeals);
                log.info("Deal with ID {} saved successfully.", fxDealsRequest.getDealUniqueId());
                return BaseResponse.builder().message("Deal saved successfully").data(new FXDeals(fxDeals.getUniqueDealId(),
                        fxDeals.getOrderingCurrencyISOCode(), fxDeals.getToCurrencyISOCode(), fxDeals.getAmount(),
                        fxDeals.getTimeStamp())).responseCode("00").status(Status.CREATED).build();
            } else {
                return new BaseResponse<>(Status.FAILED, "Duplicate deal found");
            }
        }catch (Exception e){
            return new BaseResponse<>(Status.INTERNAL_ERROR, "An error occurred while saving FX Deal");
        }

    }

    private boolean isDealUniqueIdUnique(String dealUniqueId) {
        return !fxDealsRepository.existsByUniqueDealId(dealUniqueId);
    }

    private FXDeals buildFXDeal(FXDealsRequest fxDealsRequest) {
        FXDeals fxDeal = new FXDeals();
        fxDeal.setUniqueDealId(fxDealsRequest.getDealUniqueId());
        fxDeal.setOrderingCurrencyISOCode(fxDealsRequest.getOrderingCurrencyISOCode());
        fxDeal.setToCurrencyISOCode(fxDealsRequest.getToCurrencyISOCode());
        fxDeal.setAmount(fxDealsRequest.getAmount());
        fxDeal.setTimeStamp(new Date());
        return fxDeal;
    }
}
