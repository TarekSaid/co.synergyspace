package co.synergyspace.accounts.repositories;

import co.synergyspace.accounts.entities.Business;

/**
 * Created by tarek on 07/11/16.
 */
public interface IBusinessRepository<T extends Business> {

    T findByName(String name);
}
