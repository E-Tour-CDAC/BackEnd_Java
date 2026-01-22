package com.example.controller;

import com.example.dto.PaymentDTO;
import com.example.entities.PaymentMaster;
import com.example.mapper.InvoiceMapper;
import com.example.services.InvoicePdfService;
import com.example.services.PaymentService;
import com.example.services.impl.PaymentServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	private final PaymentService paymentService;
	private final PaymentServiceImpl paymentServiceImpl;
	private final InvoicePdfService invoicePdfService;
	private final InvoiceMapper invoiceMapper;

	public PaymentController(PaymentService paymentService, PaymentServiceImpl paymentServiceImpl,
			InvoicePdfService invoicePdfService, InvoiceMapper invoiceMapper) {
		this.paymentService = paymentService;
		this.paymentServiceImpl = paymentServiceImpl;
		this.invoicePdfService = invoicePdfService;
		this.invoiceMapper = invoiceMapper;
	}

	// ✅ MAKE PAYMENT + GENERATE PDF
	@PostMapping("/pay")
	public PaymentDTO pay(@RequestParam Integer bookingId, @RequestParam String paymentMode,
			@RequestParam String transactionRef, @RequestParam String paymentStatus, @RequestParam String paymentAmount)
			throws Exception {

		PaymentMaster payment = paymentService.makePayment(bookingId, paymentMode, transactionRef, paymentStatus,
				paymentAmount);

		// 🔥 Generate PDF only for successful payment
		if ("SUCCESS".equalsIgnoreCase(paymentStatus)) {
			invoicePdfService.generateInvoice(bookingId);
		}

		return paymentServiceImpl.mapToDTO(payment);
	}
}
