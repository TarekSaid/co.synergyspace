package co.synergyspace.accounts.controllers;

import co.synergyspace.accounts.entities.User;
import co.synergyspace.accounts.exceptions.AccountException;
import co.synergyspace.accounts.exceptions.BusinessException;

/**
 * Created by tarek on 04/11/16.
 */
public interface IAccountController<T extends User> {

    Iterable<T> getAccountsFromBusiness(String name) throws BusinessException;

    T addAccount(String name, T user) throws BusinessException;

    T deactivateAccount(String name, Long id) throws BusinessException, AccountException;
}
