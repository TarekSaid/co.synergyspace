package co.synergyspace.posts.controllers;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.exceptions.BusinessException;
import co.synergyspace.posts.exceptions.PostException;

/**
 * Created by tarek on 23/09/16.
 */
public interface IPostController<T extends Post> {

    Iterable<T> listPostsFrom(String name) throws BusinessException;

    Business writePost(String name, T post) throws BusinessException;

    T getPost(String name, Long id) throws BusinessException, PostException;
}
