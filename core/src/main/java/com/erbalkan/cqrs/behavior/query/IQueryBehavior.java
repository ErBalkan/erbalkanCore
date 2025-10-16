package com.erbalkan.cqrs.behavior.query;

import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.Result;

public interface IQueryBehavior<TResult> {

    Result<TResult> handle(IQuery query, INextQueryHandler<TResult> next);
    
    interface INextQueryHandler<TResult> {
        Result<TResult> handle(IQuery query);
    }
}
