package co.synergyspace.businesses.services;

import co.synergyspace.businesses.entities.Business;

/**
 * Service with the business logic for the BusinessController
 * Created by tarek on 12/09/16.
 */
public interface IBusinessService<T extends Business> {

    /**
     * Finds all the businesses in the repository.
     *
     * @return List of Businesses
     */
    Iterable<T> findAll();

    /**
     * Finds a business by name in the repository.
     *
     * @param name the business name
     * @return the associated Business
     */
    T findByName(String name);

    /**
     * Checks if the business exists then saves it.
     *
     * @param business the business to save
     * @return saved business
     */
    T addBusiness(T business);
}
