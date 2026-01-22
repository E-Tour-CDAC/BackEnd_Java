package com.example.repositories;

import com.example.entities.BookingHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<BookingHeader, Integer> {
	@Modifying
	@Query("update BookingHeader b set b.status.id = :statusId where b.id = :bookingId")
	void updateBookingStatus(@Param("bookingId") Integer bookingId,
	                         @Param("statusId") Integer statusId);

}
