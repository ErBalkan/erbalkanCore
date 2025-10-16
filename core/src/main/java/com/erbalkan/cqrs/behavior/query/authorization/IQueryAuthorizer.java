package com.erbalkan.cqrs.behavior.query.authorization;

import com.erbalkan.cqrs.query.IQuery;

public interface IQueryAuthorizer {
    boolean isAuthorized(IQuery query);

}
