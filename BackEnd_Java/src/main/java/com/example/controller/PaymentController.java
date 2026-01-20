package com.example.controller;

import com.example.entities.PaymentMaster;
import com.example.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public PaymentMaster pay(@RequestParam Integer bookingId,
                             @RequestParam String paymentMode,
                             @RequestParam String transactionRef,
                             @RequestParam String paymentStatus,
                             @RequestParam String paymentAmount) {

        return paymentService.makePayment(
                bookingId,
                paymentMode,
                transactionRef,
                paymentStatus,
                paymentAmount
        );
    }

    @GetMapping("/{paymentId}")
    public PaymentMaster getPayment(@PathVariable Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/receipt/{bookingId}")
    public PaymentMaster getReceipt(@PathVariable Integer bookingId) {
        return paymentService.getSuccessfulPayment(bookingId);
    }
}
