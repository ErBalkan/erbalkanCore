package com.erbalkan.cqrs.behavior.query.validation;

import java.util.List;

import com.erbalkan.cqrs.query.IQuery;
import com.erbalkan.result.ErrorDetail;

public interface IQueryValidator {
    List<ErrorDetail> validate(IQuery query);
}
