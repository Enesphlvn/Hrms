package com.hrms.dtos.candidateDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCandidateDto {

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int id;

    @Size(min = 3, message = "Aday adı minimum üç karakter uzunluğunda olmalı")
    @NotBlank(message = "İsim alanı zorunludur.")
    @NotNull(message = "İsim alanı zorunludur.")
    private String name;

    @Size(min = 2, message = "Aday soyadı minimum iki karakter uzunluğunda olmalı")
    @NotBlank(message = "Soyisim alanı zorunludur.")
    @NotNull(message = "Soyisim alanı zorunludur.")
    private String surname;

    @Min(value = 18, message = "Aday yaşının en az 18 olması gerekmektedir")
    private int age;

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int cityId;

    @Size(min = 4, message = "Meslek adı minimum dört karakter uzunluğunda olmalı")
    @NotBlank(message = "Meslek alanı zorunludur.")
    @NotNull(message = "Meslek alanı zorunludur.")
    private String profession;

    @Email(message = "Lütfen email formatında giriniz.")
    @NotBlank(message = "Email alanı zorunludur.")
    @NotNull(message = "Email alanı zorunludur.")
    private String emailAddress;

    @Pattern(regexp = "(?=.*[a-z]).{8,20}", message = "Şifre en az bir küçük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[A-Z]).{8,20}", message = "Şifre en az bir büyük harf içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    @Pattern(regexp = "(?=.*[0-9]).{8,20}", message = "Şifre en az bir rakam içermeli ve 8 ile 20 karakter uzunluğunda olmalıdır")
    private String password;
}
