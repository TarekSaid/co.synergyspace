package co.synergyspace.businesses.controllers;

import co.synergyspace.businesses.entities.Business;

/**
 * Entry point of the Business service.
 * Created by tarek on 12/09/16.
 */
public interface IBusinessController<T extends Business> {

    /**
     * Returns a list of all the businesses.
     *
     * @return list of businesses
     */
    Iterable<T> listBusinesses();

    /**
     * Returns the corresponding business.
     *
     * @param name the business name
     * @return the corresponding business
     */
    T getBusiness(String name);

    /**
     * registers a new business.
     *
     * @param business the business the register.
     * @return the saved business, with the ID.
     */
    T addBusiness(T business);
}
