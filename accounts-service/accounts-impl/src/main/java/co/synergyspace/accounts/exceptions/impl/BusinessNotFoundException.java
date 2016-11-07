package co.synergyspace.accounts.exceptions.impl;

import co.synergyspace.accounts.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 07/11/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessNotFoundException extends BusinessException {

    public BusinessNotFoundException(String name) {
        super("Business " + name + " does not exist.");
    }
}
