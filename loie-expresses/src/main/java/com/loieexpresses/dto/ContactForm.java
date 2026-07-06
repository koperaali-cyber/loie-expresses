package com.loieexpresses.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/** Example DTO with validation, usable for the public contact form. */
@Data
public class ContactForm {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    private String phone;
    private String subject;
    @NotBlank private String message;
}
