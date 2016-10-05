package co.synergyspace.posts.repositories;

import co.synergyspace.posts.entities.Business;

/**
 * Created by tarek on 27/09/16.
 */
public interface IBusinessRepository<T extends Business> {

    <S extends T> S save(S business);
    <S extends T> Iterable<S> save(Iterable<S> business);
    <T extends Business> T findByName(String name);
}
