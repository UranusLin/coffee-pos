package com.coffee.pos.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response 狀態", example = "SUCCESS")
public enum CommonStatus {
    @Schema(description = "成功狀態")
    SUCCESS,
    @Schema(description = "失敗狀態")
    FAILED
}
