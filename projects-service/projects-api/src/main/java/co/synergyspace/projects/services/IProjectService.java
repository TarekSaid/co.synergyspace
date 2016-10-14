package co.synergyspace.projects.services;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;

/**
 * Created by tarek on 14/10/16.
 */
public interface IProjectService<T extends Project> {

    Iterable<T> findProjectsFrom(Business business);
}
