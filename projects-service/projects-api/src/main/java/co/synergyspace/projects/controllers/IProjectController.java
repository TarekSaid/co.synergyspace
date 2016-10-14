package co.synergyspace.projects.controllers;

import co.synergyspace.projects.entities.Project;
import co.synergyspace.projects.exceptions.BusinessException;

/**
 * Created by tarek on 14/10/16.
 */
public interface IProjectController<T extends Project> {

    Iterable<T> listProjectsFrom(String businessName) throws BusinessException;
}
