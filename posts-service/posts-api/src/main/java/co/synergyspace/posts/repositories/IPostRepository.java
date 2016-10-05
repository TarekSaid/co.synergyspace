package co.synergyspace.posts.repositories;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;

/**
 * Created by tarek on 23/09/16.
 */
public interface IPostRepository<T extends Post> {

    Iterable<T> findByBusiness(Business business);

    <S extends T> S save(S post);

    <S extends T> Iterable<S> save(Iterable<S> posts);

    Iterable<T> findAll();
}