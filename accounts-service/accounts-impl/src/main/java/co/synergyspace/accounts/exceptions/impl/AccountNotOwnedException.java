package co.synergyspace.accounts.exceptions.impl;

import co.synergyspace.accounts.exceptions.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 07/11/16.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountNotOwnedException extends AccountException {

    public AccountNotOwnedException(Long id) {
        super("The account " + id + " is not owned by you.");
    }
}
