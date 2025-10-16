package com.erbalkan.cqrs.behavior.query.validation;

import java.util.List;

import com.erbalkan.cqrs.behavior.query.IQueryBehavior;
import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.ErrorDetail;
import com.erbalkan.result.Result;
import com.erbalkan.result.ResultCode;

public class ValidationQueryBehavior<TResult>
implements IQueryBehavior<TResult>
{

    private final IQueryValidator _validator;

    public ValidationQueryBehavior(IQueryValidator validator) {
        _validator = validator;
    }

    @Override
    public Result<TResult> handle(IQuery query, INextQueryHandler<TResult> next) {
        List<ErrorDetail> errors = _validator.validate(query);
        if(!errors.isEmpty()){
            return Result.failure(ResultCode.VALIDATION_ERROR, "Validation failed.", errors);
        }
        return next.handle(query);
    }

}
