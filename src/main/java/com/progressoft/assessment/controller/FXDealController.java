package com.progressoft.assessment.controller;

import com.progressoft.assessment.data.model.BaseResponse;
import com.progressoft.assessment.dto.request.FXDealsRequest;
import com.progressoft.assessment.service.FXDealsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/deals/")
@RequiredArgsConstructor
public class FXDealController {
    private final FXDealsService fxDealsService;
    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> saveFXDeal(@Valid @RequestBody FXDealsRequest request) {
        BaseResponse<?> response = fxDealsService.saveDeals(request);
        return ResponseEntity.ok(response);
    }

}
