package com.example.demo.controller;

import com.example.demo.dto.ProcessReceiptRequest;
import com.example.demo.dto.ProcessReceiptResponse;
import com.example.demo.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    // POST /receipts/process
    @PostMapping("/process")
    public ResponseEntity<ProcessReceiptResponse> processReceipt(@RequestBody ProcessReceiptRequest request) {
        String id = receiptService.processReceipt(request);
        return ResponseEntity.ok(new ProcessReceiptResponse(id));
    }

    // GET /receipts/{id}/points
    @GetMapping("/{id}/points")
    public ResponseEntity<Integer> getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);
        return ResponseEntity.ok(points);
    }
}
