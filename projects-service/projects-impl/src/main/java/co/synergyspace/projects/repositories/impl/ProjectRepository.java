package co.synergyspace.projects.repositories.impl;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.impl.ProjectEntity;
import co.synergyspace.projects.repositories.IProjectRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 14/10/16.
 */
@Repository
public interface ProjectRepository extends IProjectRepository<ProjectEntity>, GraphRepository<ProjectEntity> {

    @Query("MATCH (p:Project)-[:CREATED]-(b:Business) WHERE id(b) = {business} RETURN p ORDER BY p.name DESC, p.id")
    Iterable<ProjectEntity> findByBusiness(@Param("business") Business business);
}
