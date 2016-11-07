package co.synergyspace.accounts.services;

import co.synergyspace.accounts.entities.Business;

/**
 * Created by tarek on 07/11/16.
 */
public interface IBusinessService<T extends Business> {

    T findByName(String name);
}
