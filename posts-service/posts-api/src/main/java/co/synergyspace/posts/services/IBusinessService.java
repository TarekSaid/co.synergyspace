package co.synergyspace.posts.services;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;

/**
 * Created by tarek on 03/10/16.
 */
public interface IBusinessService<T extends Business> {

    T findByName(String name);

    T addPost(T business, Post post);
}
