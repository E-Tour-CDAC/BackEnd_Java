package com.example.controller;

<<<<<<< HEAD
import com.example.entities.PaymentMaster;
import com.example.services.PaymentService;
=======
import com.example.dto.PaymentDTO;
import com.example.entities.PaymentMaster;
import com.example.services.PaymentService;
import com.example.services.PaymentServiceImpl;
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
<<<<<<< HEAD

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
=======
    private final PaymentServiceImpl paymentServiceImpl;

    public PaymentController(PaymentService paymentService,
                             PaymentServiceImpl paymentServiceImpl) {
        this.paymentService = paymentService;
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @PostMapping("/pay")
    public PaymentDTO pay(@RequestParam Integer bookingId,
                          @RequestParam String paymentMode,
                          @RequestParam String transactionRef,
                          @RequestParam String paymentStatus,
                          @RequestParam String paymentAmount) {

        PaymentMaster payment = paymentService.makePayment(
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
                bookingId,
                paymentMode,
                transactionRef,
                paymentStatus,
                paymentAmount
        );
<<<<<<< HEAD
    }

    @GetMapping("/{paymentId}")
    public PaymentMaster getPayment(@PathVariable Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/receipt/{bookingId}")
    public PaymentMaster getReceipt(@PathVariable Integer bookingId) {
        return paymentService.getSuccessfulPayment(bookingId);
=======

        return paymentServiceImpl.mapToDTO(payment);
    }

    @GetMapping("/{paymentId}")
    public PaymentDTO getPayment(@PathVariable Integer paymentId) {

        PaymentMaster payment = paymentService.getPaymentById(paymentId);
        return paymentServiceImpl.mapToDTO(payment);
    }

    @GetMapping("/receipt/{bookingId}")
    public PaymentDTO getReceipt(@PathVariable Integer bookingId) {

        PaymentMaster payment = paymentService.getSuccessfulPayment(bookingId);
        return paymentServiceImpl.mapToDTO(payment);
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
    }
}
