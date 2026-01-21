package com.example.repositories;

import com.example.entities.PaymentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD

=======
@Repository
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
public interface PaymentRepository extends JpaRepository<PaymentMaster, Integer> {

    boolean existsByBooking_IdAndPaymentStatus(Integer bookingId, String paymentStatus);

    Optional<PaymentMaster> findByTransactionRef(String transactionRef);

    List<PaymentMaster> findByBooking_Id(Integer bookingId);
}
