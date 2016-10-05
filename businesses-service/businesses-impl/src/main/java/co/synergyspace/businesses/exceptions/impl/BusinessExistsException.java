package co.synergyspace.businesses.exceptions.impl;

import co.synergyspace.businesses.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 22/09/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessExistsException extends BusinessException {

    public BusinessExistsException(String name) {
        super("business " + name + " already exists.");
    }
}
