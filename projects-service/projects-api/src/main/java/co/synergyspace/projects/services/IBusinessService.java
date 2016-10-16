package co.synergyspace.projects.services;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;

/**
 * Created by tarek on 14/10/16.
 */
public interface IBusinessService<T extends Business> {

    T findByName(String name);

    T addProject(T business, Project project);
}
