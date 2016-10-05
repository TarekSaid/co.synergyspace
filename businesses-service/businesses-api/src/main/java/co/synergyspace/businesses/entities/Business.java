package co.synergyspace.businesses.entities;

/**
 * Abstract Business Entity.
 * Created by tarek on 12/09/16.
 */
public abstract class Business {

    protected Long id;
    protected String name;

    public Business() {

    }

    public Business(Long id) {
        this.id = id;
    }

    public Business(String name) {
        this.name = name;
    }

    public Business(Long id, String name) {
        this.id = id;
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
