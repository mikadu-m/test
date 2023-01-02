package com.example.solva.controller;

import com.example.solva.dto.LimitRequestDto;
import com.example.solva.dto.TransactionItemDto;
import com.example.solva.dto.TransactionRequestDto;
import com.example.solva.service.impl.LimitService;
import com.example.solva.service.impl.TransactionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/solva")
public class MainController {

    private final TransactionService transactionService;
    private final LimitService limitService;

    @ApiOperation("Сохранить транзакцию")
    @PostMapping("/save")
    public void saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.saveTransaction(transactionRequestDto);
    }

    @ApiOperation("Установить новый лимит")
    @PutMapping("/limit")
    public void updateLimit(@RequestBody LimitRequestDto limitRequestDto) {
        limitService.updateLimit(limitRequestDto);
    }

    @ApiOperation("Список транзакций, превысивших лимит")
    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<TransactionItemDto>> getTransactions(@PathVariable("accountNumber") long accountNumber) {
        return ResponseEntity.ok().body(transactionService.getTransactions(accountNumber));
    }
}