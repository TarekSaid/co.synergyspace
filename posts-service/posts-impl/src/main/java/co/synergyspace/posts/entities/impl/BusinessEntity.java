package co.synergyspace.posts.entities.impl;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by tarek on 27/09/16.
 */
@NodeEntity(label = "Business")
public class BusinessEntity extends Business {

    public BusinessEntity() {
        super();
    }

    public BusinessEntity(String name) {
        super(name);
    }

    @Override
    @GraphId
    public Long getId() {
        return super.getId();
    }

    @Override

    public String getName() {
        return super.getName();
    }

    @Override
    @Relationship(type = "WROTE")
    public List<Post> getPosts() {
        return super.getPosts();
    }
}
