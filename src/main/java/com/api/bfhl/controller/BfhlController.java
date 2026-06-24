package com.api.bfhl.controller;

import com.api.bfhl.dto.RequestDTO;
import com.api.bfhl.dto.ResponseDTO;
import com.api.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDTO> processBfhl(@RequestBody RequestDTO request) {
        ResponseDTO processed = bfhlService.process(request);

        ResponseDTO response = ResponseDTO.builder()
                .isSuccess(true)
                .userId("tejasv_mahant_11082005")
                .email("tejasv2547.be23@chitkara.edu.in")
                .rollNumber("2310992547")
                .oddNumbers(processed.getOddNumbers())
                .evenNumbers(processed.getEvenNumbers())
                .alphabets(processed.getAlphabets())
                .specialCharacters(processed.getSpecialCharacters())
                .sum(processed.getSum())
                .concatString(processed.getConcatString())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("API is up and running");
    }
}
