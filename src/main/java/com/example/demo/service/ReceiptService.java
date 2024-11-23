package com.example.demo.service;

import com.example.demo.dto.ProcessReceiptRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptPoints = new HashMap<>();

    public String processReceipt(ProcessReceiptRequest request) {
        int points = calculatePoints(request);
        String id = UUID.randomUUID().toString();
        receiptPoints.put(id, points);
        return id;
    }

    public int getPoints(String id) {
        return receiptPoints.getOrDefault(id, 0);
    }

    private int calculatePoints(ProcessReceiptRequest request) {
        int points = 0;

        // Rule 1: One point for every alphanumeric character in the retailer name.
        points += request.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // Rule 2: 50 points if the total is a round dollar amount with no cents.
        if (request.getTotal().matches("\\d+\\.00")) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25.
        double total = Double.parseDouble(request.getTotal());
        if (total % 0.25 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items.
        points += (request.getItems().size() / 2) * 5;

        // Rule 5: Multiply price * 0.2 for item descriptions with a length multiple of 3.
        for (var item : request.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        // Rule 6: 6 points if the purchase date's day is odd.
        if (Integer.parseInt(request.getPurchaseDate().split("-")[2]) % 2 != 0) {
            points += 6;
        }

        // Rule 7: 10 points if the purchase time is between 2:00 PM and 4:00 PM.
        String[] time = request.getPurchaseTime().split(":");
        int hour = Integer.parseInt(time[0]);
        if (hour == 14 || hour == 15) {
            points += 10;
        }

        return points;
    }
}
