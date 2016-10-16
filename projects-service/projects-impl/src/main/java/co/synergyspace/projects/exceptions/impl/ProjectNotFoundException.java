package co.synergyspace.projects.exceptions.impl;

import co.synergyspace.projects.exceptions.ProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 15/10/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends ProjectException {

    public ProjectNotFoundException(Long id) {
        super("The project with id " + id + " was not found.");
    }
}
