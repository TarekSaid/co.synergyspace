package co.synergyspace.projects.services.impl;

import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import co.synergyspace.projects.services.IBusinessService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 14/10/16.
 */
@Service
public class BusinessService implements IBusinessService<BusinessEntity> {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Override
    public BusinessEntity findByName(String name) {
        return businessRepository.findByName(name);
    }
}
