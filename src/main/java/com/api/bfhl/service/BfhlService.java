package com.api.bfhl.service;

import com.api.bfhl.dto.RequestDTO;
import com.api.bfhl.dto.ResponseDTO;

public interface BfhlService {

    ResponseDTO process(RequestDTO request);
}
