package com.erbalkan.cqrs.query;

import com.erbalkan.result.Result;

public interface IQueryHandler<TQuery extends IQuery, TResult> {
    Result<TResult> handle(TQuery query);
}

/*
TQuery: işlenecek sorgu tipi.
TResult: dönecek veri tipi.
Result<TResult>: daha önce tanımladığımız result mimarisiyle uyumlu. 
*/