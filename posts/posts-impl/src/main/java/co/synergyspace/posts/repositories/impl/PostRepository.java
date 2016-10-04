package co.synergyspace.posts.repositories.impl;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IPostRepository;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tarek on 26/09/16.
 */
@Repository
public interface PostRepository extends IPostRepository<PostEntity>, GraphRepository<PostEntity> {

    @Query("MATCH (p:Post)-[:WROTE]-(b:Business) WHERE id(b) = {business} RETURN p ORDER BY p.date DESC, p.id")
    Iterable<PostEntity> findByBusiness(@Param("business") Business business);
}
