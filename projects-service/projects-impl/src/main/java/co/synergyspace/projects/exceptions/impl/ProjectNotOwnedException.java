package co.synergyspace.projects.exceptions.impl;

import co.synergyspace.projects.exceptions.ProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 17/10/16.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProjectNotOwnedException extends ProjectException {

    public ProjectNotOwnedException(Long id) {
        super("The project with id " + id + " does not belong to you");
    }
}
