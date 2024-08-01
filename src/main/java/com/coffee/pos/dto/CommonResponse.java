package com.coffee.pos.dto;

import com.coffee.pos.enums.CommonStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommonResponse {
    private String code;
    private String message;

    @Schema(description = "Response 狀態")
    private CommonStatus status;
}
