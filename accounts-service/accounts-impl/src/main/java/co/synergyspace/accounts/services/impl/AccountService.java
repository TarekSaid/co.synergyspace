package co.synergyspace.accounts.services.impl;

import co.synergyspace.accounts.entities.Business;
import co.synergyspace.accounts.entities.UserStatus;
import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.entities.impl.UserEntity;
import co.synergyspace.accounts.repositories.IAccountRepository;
import co.synergyspace.accounts.services.IAccountService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 04/11/16.
 */
@Service
public class AccountService implements IAccountService<UserEntity> {

    @Inject
    private IAccountRepository<UserEntity> accountRepository;

    @Override
    public Iterable<UserEntity> findAccountsFrom(Business business) {
        return accountRepository.findByBusiness(business);
    }

    @Override
    public UserEntity add(Business business, UserEntity user) {
        user.setBusiness(business);
        return accountRepository.save(user);
    }

    @Override
    public UserEntity deactivate(UserEntity user) {
        user.setStatus(UserStatus.DEACTIVATED);
        return accountRepository.save(user);
    }
}
