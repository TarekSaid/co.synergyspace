package co.synergyspace.projects.repositories;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;

/**
 * Created by tarek on 14/10/16.
 */
public interface IProjectRepository<T extends Project> {

    Iterable<T> findByBusiness(Business business);
}
