package com.api.bfhl.controller;

import com.api.bfhl.dto.RequestDTO;
import com.api.bfhl.dto.ResponseDTO;
import com.api.bfhl.service.BfhlService;
import com.api.bfhl.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BfhlControllerTest {

    @Mock
    private BfhlService bfhlService;

    @InjectMocks
    private BfhlController bfhlController;

    @Test
    void processBfhl_returnsOkWithHardcodedFields() {
        ResponseDTO serviceResponse = ResponseDTO.builder()
                .oddNumbers(List.of("1"))
                .evenNumbers(List.of("334", "4"))
                .alphabets(List.of("A", "R"))
                .specialCharacters(List.of("$"))
                .sum("339")
                .concatString("Ra")
                .build();

        when(bfhlService.process(any(RequestDTO.class))).thenReturn(serviceResponse);

        RequestDTO request = new RequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseEntity<ResponseDTO> result = bfhlController.processBfhl(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDTO response = result.getBody();
        assertTrue(response.isSuccess());
        assertEquals("tejasv_mahant_11082005", response.getUserId());
        assertEquals("tejasv2547.be23@chitkara.edu.in", response.getEmail());
        assertEquals("2310992547", response.getRollNumber());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
        verify(bfhlService).process(request);
    }

    @Test
    void processBfhl_withSampleInputAndRealService_computesSumAndConcatString() {
        BfhlController controllerWithRealService = new BfhlController(new BfhlServiceImpl());
        RequestDTO request = new RequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseEntity<ResponseDTO> result = controllerWithRealService.processBfhl(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        ResponseDTO response = result.getBody();
        assertTrue(response.isSuccess());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }
}
