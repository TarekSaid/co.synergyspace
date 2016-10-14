package co.synergyspace.projects.controllers.impl;

import co.synergyspace.projects.controllers.IProjectController;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.projects.services.IBusinessService;
import co.synergyspace.projects.services.IProjectService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by tarek on 14/10/16.
 */
@RestController
public class ProjectController implements IProjectController<ProjectEntity> {

    @Inject
    private IBusinessService<BusinessEntity> businessService;

    @Inject
    private IProjectService<ProjectEntity> projectService;

    @Override
    @RequestMapping(value = "{name}/projects", method = RequestMethod.GET)
    public Iterable<ProjectEntity> listProjectsFrom(@PathVariable String name) {
        Business business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
        return projectService.findProjectsFrom(business);
    }
}
