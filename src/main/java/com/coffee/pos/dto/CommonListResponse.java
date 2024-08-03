package com.coffee.pos.dto;

import com.coffee.pos.enums.CommonStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonListResponse<T> extends CommonResponse {
    private List<T> data;

    public CommonListResponse(String message, CommonStatus status, List<T> data) {
        super(message, status);
        this.data = data;
    }
}
