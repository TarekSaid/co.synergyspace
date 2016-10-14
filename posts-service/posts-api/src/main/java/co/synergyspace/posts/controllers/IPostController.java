package co.synergyspace.posts.controllers;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.exceptions.BusinessException;

/**
 * Created by tarek on 23/09/16.
 */
public interface IPostController<T extends Post> {

    Iterable<T> listPostsFrom(String businessName) throws BusinessException;

    Business writePost(String name, T post) throws BusinessException;
}
