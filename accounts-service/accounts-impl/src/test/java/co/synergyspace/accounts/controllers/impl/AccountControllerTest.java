package co.synergyspace.accounts.controllers.impl;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.entities.impl.UserEntity;
import co.synergyspace.accounts.exceptions.impl.AccountNotOwnedException;
import co.synergyspace.accounts.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.accounts.providers.AccountProvider;
import co.synergyspace.accounts.services.IAccountService;
import co.synergyspace.accounts.services.IBusinessService;
import mockit.*;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 07/11/16.
 */
@Test
public class AccountControllerTest {

    @Tested
    private AccountController accountController;

    @Injectable
    private IAccountService<UserEntity> accountService;

    @Injectable
    private IBusinessService<BusinessEntity> businessService;

    public void getAccountsFromBusinessShouldCallBusinessService() {
        accountController.getAccountsFromBusiness("");

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void getAccountsFromBusinessShouldThrowBusinessNotFoundExceptionWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        accountController.getAccountsFromBusiness("");
    }

    public void getAccountsFromBusinessShouldCallAccountService() {
        accountController.getAccountsFromBusiness("");

        new Verifications() {{
            accountService.findAccountsFrom((BusinessEntity) any);
        }};
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "accountsByBusiness")
    public void getAccountsShouldReturnAccountsFound(String name, BusinessEntity business, Iterable<UserEntity> users) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
            accountService.findAccountsFrom(business);
            result = users;
        }};

        assertThat(accountController.getAccountsFromBusiness(name)).isEqualTo(users);
    }

    public void addAccountShouldCallBusinessService() {
        accountController.addAccount("", new UserEntity());

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void addAccountShouldThrowBusinessNotFoundExceptionWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        accountController.addAccount("", new UserEntity());
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "account")
    public void addAccountShouldCallAccountService(UserEntity user) {
        accountController.addAccount("", user);

        new Verifications() {{
            accountService.add((BusinessEntity) any, user);
        }};
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "saved-accounts")
    public void addAccountShouldReturnSavedAccount(String name, BusinessEntity business, UserEntity user, UserEntity savedUser) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
            accountService.add(business, user);
            result = savedUser;
        }};

        assertThat(accountController.addAccount(name, user)).isEqualTo(savedUser);
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void deactivateAccountShouldThrowBusinessNotFoundWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        accountController.deactivateAccount("", 1L);
    }

    @Test(expectedExceptions = AccountNotOwnedException.class)
    public void deactivateAccountShouldThrowAccountNotOwnedWhenNotFound(@Mocked BusinessEntity business) {
        new Expectations() {{
            businessService.findByName(anyString);
            result = business;
        }};

        accountController.deactivateAccount("", 1L);
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "deactivateAccounts")
    public void deactivateAccountShouldDeactivateUser(String name, Long id, BusinessEntity business, UserEntity user) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
            accountService.deactivate((UserEntity) any);
            result = user;
        }};

        assertThat(accountController.deactivateAccount(name, id)).isEqualTo(user);
    }
}
