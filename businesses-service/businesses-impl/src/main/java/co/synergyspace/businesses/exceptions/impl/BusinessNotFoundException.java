package co.synergyspace.businesses.exceptions.impl;

import co.synergyspace.businesses.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 05/10/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessNotFoundException extends BusinessException {

    public BusinessNotFoundException(String name) {
        super("Business " + name + " does not exist.");
    }
}
