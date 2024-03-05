package com.hrms.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {

    @Email(message = "Lütfen email formatında giriniz.")
    @NotBlank(message = "Email alanı zorunludur.")
    @NotNull(message = "Email alanı zorunludur.")
    private String emailAddress;

    @Pattern(regexp = "(?=.*[a-z]).{8,20}", message = "Şifre en az bir küçük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[A-Z]).{8,20}", message = "Şifre en az bir büyük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[0-9]).{8,20}", message = "Şifre en az bir rakam içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    private String password;
}
