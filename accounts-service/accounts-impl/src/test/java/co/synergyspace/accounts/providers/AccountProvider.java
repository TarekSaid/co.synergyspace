package co.synergyspace.accounts.providers;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import co.synergyspace.accounts.entities.impl.UserEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 07/11/16.
 */
public class AccountProvider {

    @DataProvider(name = "accountsByBusiness")
    public static Iterator<Object[]> createUsersByBusiness() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"hello", new BusinessEntity("hello"), null});
        params.add(new Object[]{"test", new BusinessEntity("test"), new ArrayList<>()});
        params.add(new Object[]{"", new BusinessEntity(), Arrays.asList(new UserEntity(1L, "test"))});
        params.add(new Object[]{"", new BusinessEntity("Another"),
                Arrays.asList(new UserEntity(), new UserEntity(1L, "hello"), new UserEntity(2L, "user"))});

        return params.iterator();
    }

    @DataProvider(name = "accounts")
    public static Iterator<Object[]> createAccounts() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{null, Arrays.asList(new UserEntity(), new UserEntity(1L, "test"))});
        params.add(new Object[]{new BusinessEntity(), Arrays.asList(new UserEntity(), new UserEntity(2L, "hello"))});
        params.add(new Object[]{new BusinessEntity("test"), Arrays.asList(new UserEntity(2L, "test"))});

        return params.iterator();
    }

    @DataProvider(name = "account")
    public static Iterator<Object[]> createAccount() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{null});
        params.add(new Object[]{new UserEntity()});
        params.add(new Object[]{new UserEntity(1L, "test")});

        return params.iterator();
    }

    @DataProvider(name = "saved-accounts")
    public static Iterator<Object[]> savedAccounts() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"test", new BusinessEntity("test"), new UserEntity(), new UserEntity(1L, "hello")});
        params.add(new Object[]{"", new BusinessEntity(), new UserEntity("test"), new UserEntity(2L, "test")});
        params.add(new Object[]{"1", new BusinessEntity("2"), new UserEntity("3"), new UserEntity(3L, "4")});

        return params.iterator();
    }

    @DataProvider(name = "deactivateAccounts")
    public static Iterator<Object[]> createAccountsToDeactivate() {
        Collection<Object[]> params = new ArrayList<>();

        String name = "test";
        BusinessEntity business = new BusinessEntity(name);
        business.setAccounts(Arrays.asList(new UserEntity(1L, "test"), new UserEntity(2L, "hello")));

        params.add(new Object[]{name, 1L, business, new UserEntity(1L, "yep")});
        params.add(new Object[]{name, 2L, business, new UserEntity(2L, "nope")});

        return params.iterator();
    }
}
