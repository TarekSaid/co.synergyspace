package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IProjectRepository;
import co.synergyspace.projects.services.IProjectService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
@Service
public class ProjectService implements IProjectService<ProjectEntity, BusinessEntity> {

    @Inject
    private IProjectRepository<ProjectEntity> projectRepository;

    @Override
    public Iterable<ProjectEntity> findProjectsFrom(Business business) {
        return projectRepository.findByBusiness(business);
    }

    @Override
    public ProjectEntity findProjectById(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public ProjectEntity involveBusinesses(ProjectEntity project, Set<BusinessEntity> businesses) {
        project.involve(businesses);
        return projectRepository.save(project);
    }
}
