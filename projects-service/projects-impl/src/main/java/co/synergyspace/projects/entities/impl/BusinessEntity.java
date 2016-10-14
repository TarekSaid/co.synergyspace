package co.synergyspace.projects.entities.impl;

import co.synergyspace.projects.entities.Business;
import co.synergyspace.projects.entities.Project;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by tarek on 14/10/16.
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
    @NotNull
    public String getName() {
        return super.getName();
    }

    @Override
    @Relationship(type = "CREATED")
    public List<Project> getOwnedProjects() {
        return super.getOwnedProjects();
    }

    @Override
    @Relationship(type = "INVOLVED_IN")
    public List<Project> getInvolvedIn() {
        return super.getInvolvedIn();
    }
}
