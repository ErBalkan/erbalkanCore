package com.erbalkan.result;

import java.util.List;

import lombok.Getter;


@Getter
public class Result<T> {
    private final boolean _success;
    private final ResultCode _code;
    private final String _message;
    private final T _data;
    private final List<ErrorDetail> _errors;

    public Result(
        boolean success,
        ResultCode code,
        String message,
        T data,
        List<ErrorDetail> errors
    ){
        _success = success;
        _code = code;
        _message = message;
        _data = data;
        _errors = errors;
    }

    public static <T> Result<T> success(String message, T data){
        return new Result<T>(true, ResultCode.SUCCESS, message, data, null);
    }

    public static <T> Result<T> failure(ResultCode code, String message){
        return new Result<T>(false, code, message, null, null);
    }

    public static <T> Result<T> failure(ResultCode code, String message, List<ErrorDetail> errors){
        return new Result<T>(false, code, message, null, errors);
    }

}

/*
success, failure factory metotları

new ile değil, Result.success(...) veya Result.failure(...) ile oluşturulur.
Bu, dışarıdan Result yaratımını kontrol altında tutar (Factory Pattern).

T data
Generic yapı sayesinde her tür veri taşınabilir: User, List<Product>, null, vs.

message
Başarılı veya hatalı işlemlerde kullanıcıya/servise bilgi verir.

isSuccess()
Servis veya handler bu alanı kontrol ederek işlem sonucunu anlayabilir.

# validasyon hatası
List<ErrorDetail> errors = List.of(
    new ErrorDetail("email", "Geçerli bir e-posta giriniz"),
    new ErrorDetail("password", "Şifre en az 8 karakter olmalı")
);
return Result.failure(ResultCode.VALIDATION_ERROR, "Geçersiz alanlar var", errors);
*/