package co.synergyspace.projects.exceptions.impl;

import co.synergyspace.projects.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 14/10/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BusinessNotFoundException extends BusinessException {

    public BusinessNotFoundException(String name) {
        super("Business " + name + " does not exist.");
    }
}
