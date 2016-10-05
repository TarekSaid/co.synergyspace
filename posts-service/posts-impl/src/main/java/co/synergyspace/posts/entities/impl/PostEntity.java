package co.synergyspace.posts.entities.impl;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.Serializable;

/**
 * Created by tarek on 23/09/16.
 */
@NodeEntity(label = "Post")
public class PostEntity extends Post implements Serializable {

    public PostEntity() {
    }

    public PostEntity(Long id) {
        super(id);
    }

    public PostEntity(String content) {
        super(content);
    }

    @Override
    @GraphId
    public Long getId() {
        return id;
    }
}
