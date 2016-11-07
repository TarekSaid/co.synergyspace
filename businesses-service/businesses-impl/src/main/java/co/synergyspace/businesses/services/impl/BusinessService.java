package co.synergyspace.businesses.services.impl;

import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.producers.IBusinessProducer;
import co.synergyspace.businesses.repositories.IBusinessRepository;
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
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Inject
    private IBusinessProducer businessProducer;

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
        try {
            BusinessEntity entity = businessRepository.save(business);
            businessProducer.send(entity.getName());
            return entity;
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }
}
