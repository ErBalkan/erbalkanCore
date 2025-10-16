package com.erbalkan.cqrs.behavior.command.validation;

import java.util.List;

import com.erbalkan.cqrs.command.ICommand;
import com.erbalkan.result.ErrorDetail;

public interface ICommandValidator {
    List<ErrorDetail> validate(ICommand command);
}
