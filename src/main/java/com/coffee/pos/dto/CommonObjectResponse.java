package com.coffee.pos.dto;

import com.coffee.pos.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonObjectResponse<T> extends CommonResponse {
    private Object data;

    public CommonObjectResponse(String message, CommonStatus status, Object data) {
        super(message, status);
        this.data = data;
    }
}
