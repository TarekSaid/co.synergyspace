package co.synergyspace.projects.services;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;

import java.util.List;
import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
public interface IProjectService<T extends Project, S extends Business> {

    Iterable<T> findProjectsFrom(Business business);

    T findProjectById(Long id);

    T involveBusinesses(T project, Set<S> businesses);
}
