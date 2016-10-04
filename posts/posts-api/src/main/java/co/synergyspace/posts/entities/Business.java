package co.synergyspace.posts.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarek on 27/09/16.
 */
public abstract class Business {

    protected Long id;

    protected String name;

    protected List<Post> posts;

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void write(Post p) {
        if (posts == null) {
            posts = new ArrayList<>();
        }

        posts.add(p);
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
