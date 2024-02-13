package com.progressoft.assessment.controller;

import com.progressoft.assessment.data.model.BaseResponse;
import com.progressoft.assessment.dto.request.FXDealsRequest;
import com.progressoft.assessment.service.FXDealsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/deals")
@RequiredArgsConstructor
@Slf4j
public class FXDealController {
    private final FXDealsService fxDealsService;

    @GetMapping("")
    public String health(){
        return "Assessment is up and running";
    }
    @PostMapping("")
    public ResponseEntity<BaseResponse<?>> saveFXDeal(@RequestBody FXDealsRequest request) {
        log.info("This is the request body {}", request.getDealUniqueId().getClass());
        BaseResponse<?> response = fxDealsService.saveDeals(request);
        return ResponseEntity.ok(response);
    }


}
