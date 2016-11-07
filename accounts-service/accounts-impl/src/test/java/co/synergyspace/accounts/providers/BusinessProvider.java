package co.synergyspace.accounts.providers;

import co.synergyspace.accounts.entities.impl.BusinessEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 07/11/16.
 */
public class BusinessProvider {

    @DataProvider(name = "businesses")
    public static Iterator<Object[]> createBusinesses() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"test", new BusinessEntity("test")});
        params.add(new Object[]{"", new BusinessEntity()});
        params.add(new Object[]{"hello", new BusinessEntity("another")});

        return params.iterator();
    }
}
