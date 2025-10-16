package com.erbalkan.cqrs.mediator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.erbalkan.cqrs.behavior.command.ICommandBehavior;
import com.erbalkan.cqrs.behavior.query.IQueryBehavior;
import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.cqrs.command.ICommandHandler;
import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.cqrs.query.IQueryHandler;
import com.erbalkan.result.Result;

public class SimpleMediator implements IMediator {

    private final Map<Class<?>, Object> _handlerRegistry = new HashMap<>();
    private final List<ICommandBehavior<?,?>> _commandBehaviors = new ArrayList<>();
    private final List<IQueryBehavior<?>> _queryBehaviors = new ArrayList<>();

    // Handler kayıt metotları
    public <TCommand extends ICommand, TResult> void registerCommandHandler(
        Class<TCommand> commandType,
        ICommandHandler<TCommand,TResult> handler){
            _handlerRegistry.put(commandType, handler);
        }

    public <TQuery extends IQuery,TResult> void registerQueryHandler(
        Class<TQuery> queryType,
        IQueryHandler<TQuery,TResult> handler){
            _handlerRegistry.put(queryType, handler);
    }
        
    // Behavior kayıt metotları
    public void addCommandBehavior(ICommandBehavior<?,?> behavior){
        _commandBehaviors.add(behavior);
    }
    public void addQueryBehavior(IQueryBehavior<?> hehavior){
        _queryBehaviors.add(hehavior);
    }

    // Command gönderimi
    @SuppressWarnings("unchecked")
    @Override
    public <TResult> Result<TResult> send(ICommand command){
        ICommandHandler<ICommand,TResult> handler = (ICommandHandler<ICommand,TResult>) _handlerRegistry.get(command.getClass());
        if (handler == null) {
            throw new IllegalStateException("No CommandHandler registered for " + command.getClass().getSimpleName());
        }
        ICommandBehavior.INextCommandHandler<ICommand, TResult> finalHandler = cmd -> handler.handle(cmd);
        for (ICommandBehavior<?, ?> behavior : reverse(_commandBehaviors)) {
            ICommandBehavior<ICommand, TResult> typed = (ICommandBehavior<ICommand, TResult>) behavior;
            ICommandBehavior.INextCommandHandler<ICommand, TResult> next = finalHandler;
            finalHandler = cmd -> typed.handle(cmd, next);
        }

        return finalHandler.handle(command);
    }

    // Query gönderimi
    @SuppressWarnings("unchecked")
    @Override
    public <TResult> Result<TResult> send(IQuery query) {
        IQueryHandler<IQuery, TResult> handler = (IQueryHandler<IQuery, TResult>) _handlerRegistry.get(query.getClass());
        if (handler == null) {
            throw new IllegalStateException("No QueryHandler registered for " + query.getClass().getSimpleName());
        }

        IQueryBehavior.INextQueryHandler<TResult> finalHandler = qry -> handler.handle(qry);

        for (IQueryBehavior<?> behavior : reverse(_queryBehaviors)) {
            IQueryBehavior<TResult> typed = (IQueryBehavior<TResult>) behavior;
            IQueryBehavior.INextQueryHandler<TResult> next = finalHandler;
            finalHandler = qry -> typed.handle(qry, next);
        }

        return finalHandler.handle(query);
    }

    // Yardımcı: Listeyi ters çevir
    private <T> List<T> reverse(List<T> list) {
        List<T> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }
    
}



/*
🧠 Mimari Özellikler
Modülerlik: Handler’lar ve behavior’lar bağımsız olarak eklenebilir.

Zincirleme davranış: Behavior’lar sırayla çalışır, next.handle(...) ile zincir ilerler.

Genişletilebilirlik: Yeni behavior türleri (metrics, caching, tracing) kolayca eklenebilir.

Test edilebilirlik: Her handler ve behavior ayrı ayrı test edilebilir. 
*/

