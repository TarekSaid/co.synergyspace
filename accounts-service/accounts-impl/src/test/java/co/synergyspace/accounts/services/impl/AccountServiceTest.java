package co.synergyspace.accounts.services.impl;

import co.synergyspace.accounts.entities.UserStatus;
import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.entities.impl.UserEntity;
import co.synergyspace.accounts.providers.AccountProvider;
import co.synergyspace.accounts.repositories.IAccountRepository;
import mockit.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 07/11/16.
 */
@Test
public class AccountServiceTest {

    @Tested
    private AccountService accountService;

    @Injectable
    private IAccountRepository<UserEntity> accountRepository;

    public void findAccountsFromShouldCallAccountRepository() {
        accountService.findAccountsFrom(new BusinessEntity());

        new Verifications() {{
            accountRepository.findByBusiness((BusinessEntity) any);
        }};
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "accounts")
    public void findAccountsFromShouldReturnFoundAccounts(BusinessEntity business, List<UserEntity> users) {
        new Expectations() {{
            accountRepository.findByBusiness(business);
            result = users;
        }};

        assertThat(accountService.findAccountsFrom(business)).isEqualTo(users);
    }

    public void addAccountShouldCallSaveFromRepository(@Mocked BusinessEntity business, @Mocked UserEntity user) {
        accountService.add(business, user);

        new Verifications() {{
            user.setBusiness(business);
            accountRepository.save(user);
        }};
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "account")
    public void addAccountShouldReturnSavedUser(@Mocked UserEntity user) {
        new Expectations() {{
            accountRepository.save((UserEntity) any);
            result = user;
        }};

        assertThat(accountService.add(new BusinessEntity(), new UserEntity())).isEqualTo(user);
    }

    public void deactivateShouldSaveDeactivatedUser(@Mocked UserEntity user) {
        accountService.deactivate(user);

        new Verifications() {{
            user.setStatus(UserStatus.DEACTIVATED);
            accountRepository.save(user);
        }};
    }

    @Test(dataProviderClass = AccountProvider.class, dataProvider = "account")
    public void deactivateShouldReturnSavedUsers(UserEntity user) {
        new Expectations() {{
            accountRepository.save((UserEntity) any);
            result = user;
        }};

        assertThat(accountService.deactivate(new UserEntity())).isEqualTo(user);
    }
}
