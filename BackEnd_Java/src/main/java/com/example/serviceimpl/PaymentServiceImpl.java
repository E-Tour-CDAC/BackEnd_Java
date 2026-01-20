package com.example.serviceimpl;

import com.example.entities.BookingHeader;
import com.example.entities.PaymentMaster;
import com.example.repositories.BookingRepository;
import com.example.repositories.PaymentRepository;
import com.example.services.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public PaymentMaster makePayment(Integer bookingId,
                                     String paymentMode,
                                     String transactionRef,
                                     String paymentStatus,
                                     String paymentAmount) {

        // 1️⃣ Fetch booking (READ ONLY dependency)
        BookingHeader booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2️⃣ Prevent duplicate SUCCESS payment
        if (paymentRepository.existsByBooking_IdAndPaymentStatus(bookingId, "SUCCESS")) {
            throw new RuntimeException("Payment already completed for this booking");
        }

        // 3️⃣ Validate unique transaction reference
        paymentRepository.findByTransactionRef(transactionRef)
                .ifPresent(p -> {
                    throw new RuntimeException("Duplicate transaction reference");
                });

        // 4️⃣ Validate payment amount
        BigDecimal payAmount = new BigDecimal(paymentAmount);
        if (booking.getTotalAmount() == null ||
                payAmount.compareTo(booking.getTotalAmount()) != 0) {
            throw new RuntimeException("Payment amount mismatch");
        }

        // 5️⃣ Create payment entity
        PaymentMaster payment = new PaymentMaster();
        payment.setBooking(booking);
        payment.setPaymentMode(paymentMode);
        payment.setTransactionRef(transactionRef);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentAmount(payAmount);
        payment.setPaymentDate(Instant.now());

        // 6️⃣ Save payment
        PaymentMaster savedPayment = paymentRepository.save(payment);

        // 🔕 TEMP DISABLED
        // BookingStatus module not ready yet
        // Will be enabled after BookingStatusController + Service implementation
        /*
        if ("SUCCESS".equalsIgnoreCase(paymentStatus)) {
            booking.setStatusId(2); // PAID
            bookingRepository.save(booking);
        }
        */

        return savedPayment;
    }

    @Override
    public PaymentMaster getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public PaymentMaster getSuccessfulPayment(Integer bookingId) {
        return paymentRepository.findByBooking_Id(bookingId).stream()
                .filter(p -> "SUCCESS".equalsIgnoreCase(p.getPaymentStatus()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No successful payment found"));
    }
}