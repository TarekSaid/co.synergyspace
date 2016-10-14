package co.synergyspace.projects.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarek on 14/10/16.
 */
public abstract class Business {

    /**
     * Business' id
     **/
    protected Long id;

    /**
     * Business' name
     **/
    protected String name;

    /**
     * Projects owned
     **/
    protected List<Project> ownedProjects;

    /**
     * Projects involved
     **/
    protected List<Project> involvedIn;

    public Business() {
    }

    public Business(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getOwnedProjects() {
        return ownedProjects;
    }

    public void setOwnedProjects(List<Project> ownedProjects) {
        this.ownedProjects = ownedProjects;
    }

    public List<Project> getInvolvedIn() {
        return involvedIn;
    }

    public void setInvolvedIn(List<Project> involvedIn) {
        this.involvedIn = involvedIn;
    }

    public void create(Project p) {
        if (ownedProjects == null) {
            ownedProjects = new ArrayList<>();
        }

        ownedProjects.add(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Business business = (Business) o;

        return name != null ? name.equals(business.name) : business.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
