package com.api.bfhl.service;

import com.api.bfhl.dto.RequestDTO;
import com.api.bfhl.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    @Test
    void process_withSampleInput_separatesAndComputesCorrectly() {
        RequestDTO request = new RequestDTO(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void process_withEmptyData_returnsZeroSumAndEmptyLists() {
        RequestDTO request = new RequestDTO(Collections.emptyList());

        ResponseDTO response = bfhlService.process(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void process_withNullData_returnsZeroSumAndEmptyLists() {
        RequestDTO request = new RequestDTO(null);

        ResponseDTO response = bfhlService.process(request);

        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void process_withOnlyAlphabets_buildsAlternatingConcatString() {
        RequestDTO request = new RequestDTO(Arrays.asList("hello", "world"));

        ResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("HELLO", "WORLD"), response.getAlphabets());
        assertEquals("DlRoWoLlEh", response.getConcatString());
    }

    @Test
    void process_withExampleC_buildsConcatStringFromReversedCharacters() {
        RequestDTO request = new RequestDTO(Arrays.asList("A", "ABCD", "DOE"));

        ResponseDTO response = bfhlService.process(request);

        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals("EoDdCbAa", response.getConcatString());
    }
}
