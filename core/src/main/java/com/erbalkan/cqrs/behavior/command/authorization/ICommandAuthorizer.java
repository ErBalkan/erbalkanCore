package com.erbalkan.cqrs.behavior.command.authorization;

import com.erbalkan.cqrs.command.ICommand;

public interface ICommandAuthorizer {
    boolean isAuthorized(ICommand command);
}
