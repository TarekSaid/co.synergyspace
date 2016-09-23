package co.synergyspace.businesses.services.impl;

import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.exceptions.BusinessException;
import co.synergyspace.businesses.exceptions.impl.BusinessExistsException;
import co.synergyspace.businesses.repositories.IBusinessRepository;
import co.synergyspace.businesses.repositories.impl.BusinessRepository;
import co.synergyspace.businesses.services.IBusinessService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 12/09/16.
 */
@Service
public class BusinessService implements IBusinessService<BusinessEntity> {

    @Inject
    private BusinessRepository businessRepository;

    @Override
    public Iterable<BusinessEntity> findAll() {
        return businessRepository.findAll();
    }

    @Override
    public BusinessEntity findByName(String name) {
        try {
            return businessRepository.findByName(name);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public BusinessEntity addBusiness(BusinessEntity business) {
        if (businessRepository.findByName(business.getName()) == null) {
            return businessRepository.save(business);
        }

        throw new BusinessExistsException(business.getName());
    }
}
