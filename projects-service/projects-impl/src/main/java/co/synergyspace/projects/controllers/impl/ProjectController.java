package co.synergyspace.projects.controllers.impl;

import co.synergyspace.projects.controllers.IProjectController;
import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.projects.exceptions.impl.ProjectNotFoundException;
import co.synergyspace.projects.services.IBusinessService;
import co.synergyspace.projects.services.IProjectService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
@RestController
public class ProjectController implements IProjectController<ProjectEntity, BusinessEntity> {

    @Inject
    private IBusinessService<BusinessEntity> businessService;

    @Inject
    private IProjectService<ProjectEntity, BusinessEntity> projectService;

    @Override
    @RequestMapping(value = "{name}/projects", method = RequestMethod.GET)
    public Iterable<ProjectEntity> listProjectsFrom(@PathVariable String name) {
        Business business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
        return projectService.findProjectsFrom(business);
    }

    @Override
    @RequestMapping(value = "{name}/projects/{id}", method = RequestMethod.GET)
    public ProjectEntity getProject(@PathVariable Long id) {
        return Optional.ofNullable(projectService.findProjectById(id)).orElseThrow(
                () -> new ProjectNotFoundException(id));
    }

    @Override
    @RequestMapping(value = "{name}/projects/new", method = RequestMethod.POST)
    public Business createProject(@PathVariable String name, @RequestBody ProjectEntity project) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
        return businessService.addProject(business, project);
    }

    @Override
    @RequestMapping(value = "{name}/projects/{id}/involve", method = RequestMethod.POST)
    public ProjectEntity involve(@PathVariable Long id, @RequestBody Set<BusinessEntity> businesses) {
        ProjectEntity project = Optional.ofNullable(projectService.findProjectById(id)).orElseThrow(
                () -> new ProjectNotFoundException(id));
        return projectService.involveBusinesses(project, businesses);
    }
}
