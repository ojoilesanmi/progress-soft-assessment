package com.progressoft.assessment.controller;

import com.progressoft.assessment.dto.request.FXDealsRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/")
public class FXDealController {

    @PostMapping("/test")
    public ResponseEntity<?> getVirtualAccount(@Valid @RequestBody FXDealsRequest request) throws Exception {

        return ResponseEntity.ok().build();
    }

}
