package com.hrms.dtos.employerDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployerDto {

    @Email(message = "Lütfen email formatında giriniz.")
    @NotBlank(message = "Email alanı zorunludur.")
    @NotNull(message = "Email alanı zorunludur.")
    private String emailAddress;

    @Pattern(regexp = "(?=.*[a-z]).{8,20}", message = "Şifre en az bir küçük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[A-Z]).{8,20}", message = "Şifre en az bir büyük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[0-9]).{8,20}", message = "Şifre en az bir rakam içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    private String password;

    @Size(min = 3, message = "Şirket adı minimum üç karakter uzunluğunda olmalı")
    @NotBlank(message = "Şirket adı alanı zorunludur.")
    @NotNull(message = "Şirket adı alanı zorunludur.")
    private String companyName;
}
