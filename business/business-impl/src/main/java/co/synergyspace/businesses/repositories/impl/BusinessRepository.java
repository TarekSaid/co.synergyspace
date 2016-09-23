package co.synergyspace.businesses.repositories.impl;

import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.repositories.IBusinessRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the BusinessRepository.
 * Created by tarek on 13/09/16.
 */
@Repository
public interface BusinessRepository extends IBusinessRepository<BusinessEntity>, JpaRepository<BusinessEntity, Long> {
}
