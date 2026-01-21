package com.example.entities;

import com.example.enums.AuthProvider;
import com.example.enums.CustomerRole;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer_master")
<<<<<<< HEAD
@Getter
@Setter
=======

>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @ColumnDefault("'CUSTOMER'")
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_role", nullable = false)
    private CustomerRole customerRole;

<<<<<<< HEAD
    public Integer getId() {
=======
    @ColumnDefault("'LOCAL'")
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;

    @ColumnDefault("0")
    @Column(name = "profile_completed", nullable = false)
    private Boolean profileCompleted;

    @OneToMany(mappedBy = "customer")
    private Set<BookingHeader> bookingHeaders = new LinkedHashSet<>();

	public Integer getId() {
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CustomerRole getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(CustomerRole customerRole) {
		this.customerRole = customerRole;
	}

	public AuthProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}

	public Boolean getProfileCompleted() {
		return profileCompleted;
	}

	public void setProfileCompleted(Boolean profileCompleted) {
		this.profileCompleted = profileCompleted;
	}

	public Set<BookingHeader> getBookingHeaders() {
		return bookingHeaders;
	}

	public void setBookingHeaders(Set<BookingHeader> bookingHeaders) {
		this.bookingHeaders = bookingHeaders;
	}
<<<<<<< HEAD

	@ColumnDefault("'LOCAL'")
    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_provider", nullable = false)
    private AuthProvider authProvider;

    @ColumnDefault("0")
    @Column(name = "profile_completed", nullable = false)
    private Boolean profileCompleted;

    @OneToMany(mappedBy = "customer")
    private Set<BookingHeader> bookingHeaders = new LinkedHashSet<>();
=======
    
    
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0
}