package co.synergyspace.posts.exceptions.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 07/10/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BusinessNotFoundException extends RuntimeException {

    public BusinessNotFoundException(String name) {
        super("Business " + name + " does not exist.");
    }
}
