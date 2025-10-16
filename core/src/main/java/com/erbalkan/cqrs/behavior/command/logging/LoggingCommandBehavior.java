package com.erbalkan.cqrs.behavior.command.logging;

import com.erbalkan.cqrs.behavior.command.ICommandBehavior;
import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.result.Result;

public class LoggingCommandBehavior<TResult> 
implements  ICommandBehavior<ICommand, TResult>
{

    @Override
    public Result<TResult> handle(ICommand command, INextCommandHandler<ICommand, TResult> next) {
        System.out.println("[LOG] Command started: " + command.getClass().getSimpleName());
        Result<TResult> result = next.handle(command);
        System.out.println("[LOG] Command finished: " + command.getClass().getSimpleName());
        return result;    
    }

}

/*
next.handle(command) çağrısı zincirin bir sonraki halkasına geçer.

Öncesinde ve sonrasında log basılır.

Handler’a dokunmadan loglama yapılır → SRP uyumu. 
*/