package com.example.SpringMVCweek2.SpringMVC.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 10)
    private String name;

    @Email(message = "Email should be valid email")
    private String email;

    @Max(value = 80)
    @Min(value = 18)
    private Integer age;

    @PastOrPresent(message = "DateOfJoining field in employee cannot be in the future")
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
}
