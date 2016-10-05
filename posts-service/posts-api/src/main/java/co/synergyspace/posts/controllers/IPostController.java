package co.synergyspace.posts.controllers;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;

/**
 * Created by tarek on 23/09/16.
 */
public interface IPostController<T extends Post> {

    Iterable<T> listPostsFrom(String businessName);

    Business writePost(String name, T post);
}
