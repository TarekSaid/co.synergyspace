package co.synergyspace.projects.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tarek on 14/10/16.
 */
public abstract class Project {

    /**
     * The id
     **/
    protected Long id;

    /**
     * Project's name
     **/
    protected String name;

    /**
     * Project's description
     **/
    protected String description;

    /**
     * Project Status
     **/
    protected ProjectStatus status;

    /**
     * Businesses involved
     **/
    protected Set<Business> involved;

    public Project() {
    }

    public Project(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Set<Business> getInvolved() {
        return involved;
    }

    public void setInvolved(Set<Business> involved) {
        this.involved = involved;
    }

    public void involve(Set<? extends Business> businesses) {
        if (involved == null) {
            involved = new HashSet<>();
        }

        involved.addAll(businesses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id != null ? id.equals(project.id) : project.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
