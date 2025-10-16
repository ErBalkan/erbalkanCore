package com.erbalkan.cqrs.behavior.query.logging;

import com.erbalkan.cqrs.behavior.query.IQueryBehavior;
import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.Result;

public class LoggingQueryBehavior<TResult> 
implements IQueryBehavior<TResult>
{

    @Override
    public Result<TResult> handle(IQuery query, INextQueryHandler<TResult> next) {
        System.out.println("[LOG] Query started: " + query.getClass().getSimpleName());
        Result<TResult> result = next.handle(query);
        System.out.println("[LOG] Query finished: " + query.getClass().getSimpleName());
        return result;      
    }

}
