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
}