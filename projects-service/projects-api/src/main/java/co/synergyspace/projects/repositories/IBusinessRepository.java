package co.synergyspace.projects.repositories;

import co.synergyspace.projects.entities.Business;

/**
 * Created by tarek on 14/10/16.
 */
public interface IBusinessRepository<T extends Business> {

    <S extends T> S save(S business);

    <S extends T> Iterable<S> save(Iterable<S> businesses);

    <T extends Business> T findByName(String name);
}
