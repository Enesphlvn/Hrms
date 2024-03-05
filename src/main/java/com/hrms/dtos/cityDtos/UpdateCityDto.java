package com.hrms.dtos.cityDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityDto {

    @Size(min = 3, message = "Şehir adı minimum üç karakter uzunluğunda olmalı")
    @NotBlank(message = "Şehir adı alanı zorunludur.")
    @NotNull(message = "Şehir adı alanı zorunludur.")
    private String cityName;
}
