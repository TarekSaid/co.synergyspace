package co.synergyspace.businesses.exceptions.impl;

import co.synergyspace.businesses.exceptions.BusinessException;

/**
 * Created by tarek on 05/10/16.
 */
public class BusinessNotFoundException extends BusinessException {

    public BusinessNotFoundException(String name) {
        super("Business " + name + " does not exist.");
    }
}
