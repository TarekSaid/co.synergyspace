package co.synergyspace.accounts.repositories.impl;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.repositories.IBusinessRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 07/11/16.
 */
@Repository
public interface BusinessRepository extends IBusinessRepository<BusinessEntity>, JpaRepository<BusinessEntity, Long> {
}
