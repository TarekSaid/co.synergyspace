package co.synergyspace.accounts.repositories;

import co.synergyspace.accounts.entities.Business;
import co.synergyspace.accounts.entities.User;

/**
 * Created by tarek on 04/11/16.
 */
public interface IAccountRepository<T extends User> {

    Iterable<T> findAll();

    Iterable<T> findByBusiness(Business business);

    <S extends T> S save(S user);
}
