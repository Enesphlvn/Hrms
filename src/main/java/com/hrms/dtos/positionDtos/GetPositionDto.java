package com.hrms.dtos.positionDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPositionDto {

    private int positionId;
    private String positionName;
}
