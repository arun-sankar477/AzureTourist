//package com.tourmanagementapp.tourmanagement.models;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Entity
//@Setter
//@Table(name = "tariff_details")
//public class TrafficDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JsonBackReference
//    @ManyToOne
//    @JoinColumn(name = "company_id", nullable = false)
//    private Company company;
//
//    @Column(nullable = false)
//    private String place;
//
//    @Column(nullable = false)
//    private double tariff;
//
//    // Constructors, getters, and setters
//
//    // Static list of places and their tariff details
//    public static final String PLACE_ANDAMAN = "ANDAMAN";
//    public static final String PLACE_THAILAND = "THAILAND";
//    public static final String PLACE_DUBAI = "DUBAI";
//    public static final String PLACE_SINGAPORE = "SINGAPORE";
//    public static final String PLACE_MALAYSIA = "MALAYSIA";
//
//    // Default tariff details for each place
//    public static final double MIN_TARIFF = 50000.00;
//    public static final double MAX_TARIFF = 100000.00;
//
//
//
//    // Constructors, getters, and setters
//
//    // Custom Exception class
//    public static class CustomException extends RuntimeException {
//        public CustomException(String message) {
//            super(message);
//        }
//    }
//}
