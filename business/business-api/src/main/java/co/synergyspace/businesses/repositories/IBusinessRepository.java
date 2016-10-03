package co.synergyspace.businesses.repositories;

import co.synergyspace.businesses.entities.Business;

import java.util.List;

/**
 * Repository that holds Businesses.
 * Created by tarek on 12/09/16.
 */
public interface IBusinessRepository<T extends Business> {
    /**
     * finds all Businesses from the repository
     *
     * @return list of businesses
     */
    Iterable<T> findAll();

    /**
     * finds a business by its name
     *
     * @param name the business name
     * @return the business or null if not found.
     */
    T findByName(String name);

    <S extends T> List<S> save(Iterable<S> businesses);
}
