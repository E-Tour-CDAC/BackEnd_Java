package com.example.services;
<<<<<<< HEAD
=======

import com.example.dto.PaymentDTO;
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
import com.example.entities.BookingHeader;
import com.example.entities.PaymentMaster;
import com.example.repositories.BookingRepository;
import com.example.repositories.PaymentRepository;
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

<<<<<<< HEAD
        // 1️⃣ Fetch booking (READ ONLY dependency)
        BookingHeader booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 2️⃣ Prevent duplicate SUCCESS payment
=======
        BookingHeader booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
        if (paymentRepository.existsByBooking_IdAndPaymentStatus(bookingId, "SUCCESS")) {
            throw new RuntimeException("Payment already completed for this booking");
        }

<<<<<<< HEAD
        // 3️⃣ Validate unique transaction reference
=======
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
        paymentRepository.findByTransactionRef(transactionRef)
                .ifPresent(p -> {
                    throw new RuntimeException("Duplicate transaction reference");
                });

<<<<<<< HEAD
        // 4️⃣ Validate payment amount
=======
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
        BigDecimal payAmount = new BigDecimal(paymentAmount);
        if (booking.getTotalAmount() == null ||
                payAmount.compareTo(booking.getTotalAmount()) != 0) {
            throw new RuntimeException("Payment amount mismatch");
        }

<<<<<<< HEAD
        // 5️⃣ Create payment entity
=======
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
        PaymentMaster payment = new PaymentMaster();
        payment.setBooking(booking);
        payment.setPaymentMode(paymentMode);
        payment.setTransactionRef(transactionRef);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentAmount(payAmount);
        payment.setPaymentDate(Instant.now());

<<<<<<< HEAD
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
=======
        return paymentRepository.save(payment);
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
    }

    @Override
    public PaymentMaster getPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public PaymentMaster getSuccessfulPayment(Integer bookingId) {
        return paymentRepository.findByBooking_Id(bookingId)
                .stream()
                .filter(p -> "SUCCESS".equalsIgnoreCase(p.getPaymentStatus()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No successful payment found"));
    }

<<<<<<< HEAD
}
=======
    // ✅ DTO MAPPER (PUBLIC)
    public PaymentDTO mapToDTO(PaymentMaster payment) {

        PaymentDTO dto = new PaymentDTO();

        dto.setPaymentId(payment.getId());
        dto.setPaymentAmount(payment.getPaymentAmount());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setTransactionRef(payment.getTransactionRef());
        dto.setPaymentDate(payment.getPaymentDate());

        if (payment.getBooking() != null) {
            dto.setBookingId(payment.getBooking().getId()); // ✅ FIX HERE
        }

        return dto;
    }
}
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
