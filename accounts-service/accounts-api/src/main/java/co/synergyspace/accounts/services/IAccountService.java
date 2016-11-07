package co.synergyspace.accounts.services;

import co.synergyspace.accounts.entities.Business;
import co.synergyspace.accounts.entities.User;

/**
 * Created by tarek on 04/11/16.
 */
public interface IAccountService<T extends User> {

    Iterable<T> findAccountsFrom(Business business);

    T add(Business business, T user);

    T deactivate(T user);
}
