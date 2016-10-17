package co.synergyspace.projects.exceptions.impl;

import co.synergyspace.projects.exceptions.ProjectException;

/**
 * Created by tarek on 17/10/16.
 */
public class ProjectNotOwnedException extends ProjectException {

    public ProjectNotOwnedException(Long id) {
        super("The project with id " + id + " does not belong to you");
    }
}
