package co.synergyspace.posts.repositories.impl;

import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 27/09/16.
 */
@Repository
public interface BusinessRepository extends IBusinessRepository<BusinessEntity>, GraphRepository<BusinessEntity> {
}
