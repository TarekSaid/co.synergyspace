package co.synergyspace.projects.entities.impl;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
@NodeEntity(label = "Project")
public class ProjectEntity extends Project {

    public ProjectEntity() {
        super();
    }

    public ProjectEntity(Long id) {
        super(id);
    }

    @Override
    @GraphId
    public Long getId() {
        return super.getId();
    }

    @Override
    @Relationship(type = "INVOLVED_IN", direction = Relationship.INCOMING)
    public Set<Business> getInvolved() {
        return super.getInvolved();
    }
}
