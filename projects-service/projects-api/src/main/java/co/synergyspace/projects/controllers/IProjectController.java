package co.synergyspace.projects.controllers;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.exceptions.BusinessException;
import co.synergyspace.projects.exceptions.ProjectException;

import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
public interface IProjectController<T extends Project, S extends Business> {

    Iterable<T> listProjectsFrom(String name) throws BusinessException;

    T getProject(String name, Long id);

    Business createProject(String name, T project) throws BusinessException;

    T involve(String name, Long id, Set<S> businesses) throws BusinessException, ProjectException;
}
