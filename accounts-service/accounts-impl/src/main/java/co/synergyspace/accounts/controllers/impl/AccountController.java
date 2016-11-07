package co.synergyspace.accounts.controllers.impl;

import co.synergyspace.accounts.controllers.IAccountController;
import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.entities.impl.UserEntity;
import co.synergyspace.accounts.exceptions.impl.AccountNotOwnedException;
import co.synergyspace.accounts.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.accounts.services.IAccountService;
import co.synergyspace.accounts.services.IBusinessService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by tarek on 04/11/16.
 */
@RestController
public class AccountController implements IAccountController<UserEntity> {

    @Inject
    private IAccountService<UserEntity> accountService;

    @Inject
    private IBusinessService<BusinessEntity> businessService;

    @Override
    @RequestMapping("{name}/accounts/")
    public Iterable<UserEntity> getAccountsFromBusiness(@PathVariable String name) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name))
                .orElseThrow(() -> new BusinessNotFoundException(name));

        return accountService.findAccountsFrom(business);
    }

    @Override
    @RequestMapping("{name}/accounts/add")
    public UserEntity addAccount(@PathVariable String name, @RequestBody UserEntity user) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name))
                .orElseThrow(() -> new BusinessNotFoundException(name));

        return accountService.add(business, user);
    }

    @Override
    @RequestMapping("{name}/accounts/{id}/delete")
    public UserEntity deactivateAccount(@PathVariable String name, @PathVariable Long id) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name))
                .orElseThrow(() -> new BusinessNotFoundException(name));

        UserEntity user = (UserEntity) business.getAccounts().stream().filter(u -> id == u.getId()).findAny()
                .orElseThrow(() -> new AccountNotOwnedException(id));

        return accountService.deactivate(user);
    }
}
