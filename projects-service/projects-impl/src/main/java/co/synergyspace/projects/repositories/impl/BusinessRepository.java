package co.synergyspace.projects.repositories.impl;

import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.repositories.IBusinessRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 14/10/16.
 */
@Repository
public interface BusinessRepository extends IBusinessRepository<BusinessEntity>, GraphRepository<BusinessEntity> {
}
