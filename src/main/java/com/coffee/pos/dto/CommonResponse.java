package com.coffee.pos.dto;

import com.coffee.pos.enums.CommonStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private String message;

    @Schema(description = "Response 狀態")
    private CommonStatus status;
}
