package com.coffee.pos.dto;

import com.coffee.pos.enums.CommonStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class CommonListResponse<T> extends CommonResponse {
    private List<T> data;
    private Meta meta;

    public CommonListResponse(String message, CommonStatus status, Page<T> data) {
        super(message, status);
        this.data = data.getContent();
        this.meta =
                new Meta(
                        data.getNumber() + 1,
                        data.getSize(),
                        data.getTotalPages(),
                        data.getTotalElements());
    }

    @Data
    @AllArgsConstructor
    public static class Meta {
        private int page;
        private int size;
        private int totalPages;
        private long totalElements;
    }
}
