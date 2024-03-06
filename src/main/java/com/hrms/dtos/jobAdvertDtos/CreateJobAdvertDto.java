package com.hrms.dtos.jobAdvertDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobAdvertDto {

    @Size(min = 3, message = "Şehir adı minimum üç karakter uzunluğunda olmalıdır.")
    @NotBlank(message = "Şehir alanı zorunludur.")
    @NotNull(message = "Şehir alanı zorunludur.")
    private String title;

    @Size(max = 100, message = "Açıklama alanı maximum yüz karakter uzunluğunda olmalıdır.")
    private String description;

    @Min(value = 17000, message = "Minimum maaş 17000TL olabilir.")
    private int minSalary;

    @Max(value = 150000, message = "Maximum maaş 150000TL olabilir.")
    private int maxSalary;

    @Min(value = 1, message = "Pozisyon sayısı minimum bir olmalı.")
    private int numberOfAvailablePositions;

    @Future(message = "Son başvuru tarihi gelecek bir tarih olmalıdır.")
    private LocalDate deadline;

    @AssertTrue(message = "İlan durumu, ilan oluşturulurken 'true' olmalıdır.")
    private boolean advertSituation = true;

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int employerId;

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int candidateId;

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int positionId;

    @Min(value = 1, message = "Lütfen pozitif bir Id değeri girin.")
    private int cityId;
}
