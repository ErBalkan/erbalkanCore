package com.erbalkan.cqrs.behavior.query.authorization;

import com.erbalkan.cqrs.behavior.query.IQueryBehavior;
import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.Result;
import com.erbalkan.result.ResultCode;

public class AuthorizationQueryBehavior<TResult>
implements IQueryBehavior<TResult>
{

    private final IQueryAuthorizer _authorizer;
    
    public AuthorizationQueryBehavior(IQueryAuthorizer authorizer) {
        _authorizer = authorizer;
    }

    @Override
    public Result<TResult> handle(IQuery query, INextQueryHandler<TResult> next) {
        if(!_authorizer.isAuthorized(query)){
            return Result.failure(ResultCode.UNAUTHORIZED, "Yetkisiz işlem.");
        }
        return next.handle(query);
    }

}

/*
🧩 Behavior Zinciri Nasıl Çalışır?
Örneğin Mediator.send(query) çağrıldığında zincir şöyle işler:
LoggingQueryBehavior → ValidationQueryBehavior → AuthorizationQueryBehavior → QueryHandler

Her behavior:

next.handle(query) diyerek zinciri bir sonrakine devreder.

Zinciri durdurabilir (örneğin yetkisiz sorgu varsa).
*/