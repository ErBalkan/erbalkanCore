package com.erbalkan.cqrs.command;

import com.erbalkan.result.Result;

public interface ICommandHandler<TCommand extends ICommand, TResult> {
    Result<TResult> handle(TCommand command);
}


/*
TCommand: işlenecek komut tipi.

TResult: işlem sonucu dönecek veri tipi.

Result<TResult>: daha önce tanımladığımız result mimarisiyle uyumlu. 
*/

