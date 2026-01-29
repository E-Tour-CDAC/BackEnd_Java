package com.example.controller;

import com.example.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.services.InvoicePdfService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoicePdfService invoiceService;

    @Autowired
    private final PaymentService paymentService;

    public InvoiceController(InvoicePdfService invoiceService, PaymentService paymentService) {
        this.invoiceService = invoiceService;
        this.paymentService = paymentService;
    }

    @GetMapping("/{bookingId}/download")
    public ResponseEntity<byte[]> downloadInvoice(
            @PathVariable Integer bookingId) {

        int paymentId = paymentService.findByBookingId(bookingId);

        byte[] pdf = invoiceService.generateInvoice(paymentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=invoice_" + paymentId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}

