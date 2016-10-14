package co.synergyspace.projects.services;

import co.synergyspace.projects.entities.Business;

/**
 * Created by tarek on 14/10/16.
 */
public interface IBusinessService<T extends Business> {

    T findByName(String name);
}
