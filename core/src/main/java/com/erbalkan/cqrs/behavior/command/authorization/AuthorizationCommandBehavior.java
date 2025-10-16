package com.erbalkan.cqrs.behavior.command.authorization;

import com.erbalkan.cqrs.behavior.command.ICommandBehavior;
import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.result.Result;
import com.erbalkan.result.ResultCode;

public class AuthorizationCommandBehavior<TResult>
implements ICommandBehavior<ICommand, TResult>
{
    private final ICommandAuthorizer _authorizer;
    
    public AuthorizationCommandBehavior(ICommandAuthorizer authorizer) {
        _authorizer = authorizer;
    }

    @Override
    public Result<TResult> handle(ICommand command, INextCommandHandler<ICommand, TResult> next) {
        if(!_authorizer.isAuthorized(command)){
            return Result.failure(ResultCode.UNAUTHORIZED, "Yetkisiz işlem.");
        }
        return next.handle(command);
    }
}

/*
🧩 Behavior Zinciri Nasıl Çalışır?
Örneğin Mediator.send(command) çağrıldığında zincir şöyle işler:
LoggingBehavior → ValidationBehavior → AuthorizationBehavior → CommandHandler
Her behavior:

next.handle(command) diyerek zinciri bir sonrakine devreder.

Zinciri durdurabilir (örneğin validasyon hatası varsa).


*/
