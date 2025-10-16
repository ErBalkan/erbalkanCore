package com.erbalkan.result;

public enum ResultCode {
    SUCCESS,
    VALIDATION_ERROR,
    NOT_FOUND,
    UNAUTHORIZED,
    FORBIDDEN,
    INTERNAL_ERROR,
    CONFLICT,
    BAD_REQUEST
}

// Bu enum, tüm sistemde kullanılacak standart hata ve başarı kodlarını temsil eder. Genişletilebilir.