package co.synergyspace.accounts.services.impl;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.repositories.IBusinessRepository;
import co.synergyspace.accounts.services.IBusinessService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 07/11/16.
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
