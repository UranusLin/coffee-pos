package com.coffee.pos.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    public Pageable getPageable(int page, int size, String sortDirection, String sortBy) {
        Sort.Direction direction =
                sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(page - 1, size, Sort.by(direction, sortBy));
    }
}
