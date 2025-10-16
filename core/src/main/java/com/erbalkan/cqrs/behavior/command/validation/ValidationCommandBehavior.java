package com.erbalkan.cqrs.behavior.command.validation;

import java.util.List;

import com.erbalkan.cqrs.behavior.command.ICommandBehavior;
import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.result.ErrorDetail;
import com.erbalkan.result.Result;
import com.erbalkan.result.ResultCode;


public class ValidationCommandBehavior<TResult> 
implements ICommandBehavior<ICommand, TResult>
{
    private final ICommandValidator _validator;

    public ValidationCommandBehavior(ICommandValidator validator) {
        _validator = validator;
    }

    @Override
    public Result<TResult> handle(ICommand command, INextCommandHandler<ICommand, TResult> next) {
        List<ErrorDetail> errors = _validator.validate(command);
        if(!errors.isEmpty()){
            return Result.failure(ResultCode.VALIDATION_ERROR, "Validation failed.", errors);
        }
        return next.handle(command);
    }

}

/*
Validasyon mantığı behavior’a taşınır.

Handler sadece iş mantığına odaklanır.

Validasyon başarısızsa zincir durur → next çağrılmaz. 
*/