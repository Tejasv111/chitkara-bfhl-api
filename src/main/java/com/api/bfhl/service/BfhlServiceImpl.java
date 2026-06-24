package com.api.bfhl.service;

import com.api.bfhl.dto.RequestDTO;
import com.api.bfhl.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDTO process(RequestDTO request) {
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        List<String> alphabetElements = new ArrayList<>();
        long totalSum = 0;

        if (request.getData() != null) {
            for (String item : request.getData()) {
                if (isNumber(item)) {
                    long value = Long.parseLong(item);
                    totalSum += value;
                    if (value % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } else if (isAlphabet(item)) {
                    alphabets.add(item.toUpperCase());
                    alphabetElements.add(item);
                } else {
                    specialCharacters.add(item);
                }
            }
        }

        return ResponseDTO.builder()
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(totalSum))
                .concatString(buildConcatString(alphabetElements))
                .build();
    }

    private boolean isNumber(String value) {
        return value != null && value.matches("-?\\d+");
    }

    private boolean isAlphabet(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (char c : value.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private String buildConcatString(List<String> alphabetElements) {
        StringBuilder combined = new StringBuilder();
        for (String element : alphabetElements) {
            combined.append(element);
        }

        String reversed = combined.reverse().toString();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
