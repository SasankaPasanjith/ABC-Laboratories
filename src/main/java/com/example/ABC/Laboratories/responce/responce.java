package com.example.ABC.Laboratories.responce;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class responce {
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object reponseObject
    )
    {
        Map<String, Object> response=new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", reponseObject);

        return new ResponseEntity<Object>(response,httpStatus);
    }
}
