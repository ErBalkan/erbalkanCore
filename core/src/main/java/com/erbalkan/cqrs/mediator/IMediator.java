package com.erbalkan.cqrs.mediator;

import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.Result;

public interface IMediator {
    <TResult> Result<TResult> send(ICommand command);
    <TResult> Result<TResult> send(IQuery query);
}

/*
send(Command) → ilgili CommandHandler’ı bulur ve çalıştırır.
send(Query) → ilgili QueryHandler’ı bulur ve çalıştırır.
Dönüş tipi her zaman Result<TResult> → mimari tutarlılık. 
*/