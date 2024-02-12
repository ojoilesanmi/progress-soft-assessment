package com.progressoft.assessment.service;

import com.progressoft.assessment.data.model.BaseResponse;
import com.progressoft.assessment.dto.request.FXDealsRequest;

public interface FXDealsService {

    BaseResponse<?> saveDeals(FXDealsRequest fxDealsRequest);

}
