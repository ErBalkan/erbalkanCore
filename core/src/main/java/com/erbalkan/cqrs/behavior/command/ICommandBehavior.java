package com.erbalkan.cqrs.behavior.command;

import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.result.Result;

public interface ICommandBehavior<TCommand extends ICommand, TResult> {

    Result<TResult> handle(TCommand command, INextCommandHandler<TCommand, TResult> next);
    
    interface INextCommandHandler<TCommand extends ICommand, TResult> {

        Result<TResult> handle(TCommand command);
    }
}
