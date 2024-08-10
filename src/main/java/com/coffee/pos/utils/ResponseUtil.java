package com.coffee.pos.utils;

import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.enums.CommonStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public ResponseEntity<CommonObjectResponse> getObjectResponse(
            String message, CommonStatus status, Object data) {
        CommonObjectResponse commonObjectResponse = new CommonObjectResponse(message, status, data);
        return new ResponseEntity<>(commonObjectResponse, HttpStatus.OK);
    }
}
