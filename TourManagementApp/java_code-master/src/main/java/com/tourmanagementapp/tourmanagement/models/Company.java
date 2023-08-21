package com.tourmanagementapp.tourmanagement.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@Document("Company")
public class Company {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
   // public Long Id;

   // @Column(name = "branch_id", unique = true, nullable = false)
    private String branchId;

    public String branchName;
    public String place;
    public double Tariff;
  //  @Column(nullable = false)
    @Pattern(regexp = ".*www.*", message = "Website should contain 'www'")
    public String website;

   // @Column(nullable = false)
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Pattern(regexp = "\\d+", message = "Mobile number must contain only digits")
    private String contact;

   // @Column(nullable = false)
    @Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", message = "Invalid email format")
    public String email;

   // @JsonManagedReference
   // @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true)
  //  public List<TrafficDetails> trafficDetails;

  //  @Column(name = "added_date", nullable = false)
    private LocalDate addedDate;

   // @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
        }
    }

}
