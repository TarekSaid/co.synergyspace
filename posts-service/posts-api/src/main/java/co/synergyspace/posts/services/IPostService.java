package co.synergyspace.posts.services;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;

/**
 * Created by tarek on 23/09/16.
 */
public interface IPostService<T extends Post> {

    Iterable<T> findPostsFrom(Business business);
}
