package com.erbalkan.result;

import lombok.Getter;

@Getter
public class ErrorDetail {
    private final String _field;
    private final String _message;

    public ErrorDetail(String field, String message) {
        _field = field;
        _message = message;
    }
}

/*
Özellikle validasyon hatalarında çok işe yarar. 
Örneğin: "email" alanı için "Geçerli bir e-posta giriniz" gibi. 
*/