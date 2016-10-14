package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IProjectRepository;
import co.synergyspace.projects.services.IProjectService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 14/10/16.
 */
@Service
public class ProjectService implements IProjectService<ProjectEntity> {

    @Inject
    private IProjectRepository<ProjectEntity> projectRepository;

    @Override
    public Iterable<ProjectEntity> findProjectsFrom(Business business) {
        return projectRepository.findByBusiness(business);
    }
}
