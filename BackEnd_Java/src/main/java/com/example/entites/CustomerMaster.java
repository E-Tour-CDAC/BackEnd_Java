package com.example.entites;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer_master")
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
    @Column(name = "customer_role", nullable = false)
    private String customerRole;

    @ColumnDefault("'LOCAL'")
    @Lob
    @Column(name = "auth_provider", nullable = false)
    private String authProvider;

    @ColumnDefault("0")
    @Column(name = "profile_completed", nullable = false)
    private Boolean profileCompleted;

    @OneToMany(mappedBy = "customer")
    private Set<BookingHeader> bookingHeaders = new LinkedHashSet<>();

    public Integer getId() {
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

    public String getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(String customerRole) {
        this.customerRole = customerRole;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
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

}