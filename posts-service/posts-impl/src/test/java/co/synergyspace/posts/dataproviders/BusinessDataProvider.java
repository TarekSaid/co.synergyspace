package co.synergyspace.posts.dataproviders;

import co.synergyspace.posts.entities.impl.BusinessEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 03/10/16.
 */
public class BusinessDataProvider {

    @DataProvider(name = "businesses")
    public static Iterator<Object[]> createBusinesses() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"test", new BusinessEntity("test")});
        params.add(new Object[]{"hello", new BusinessEntity("hello")});

        return params.iterator();
    }

    @DataProvider(name = "savedBusinesses")
    public static Iterator<Object[]> createSavedBusinesses() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{new BusinessEntity(""), new BusinessEntity()});
        params.add(new Object[]{new BusinessEntity("hello"), new BusinessEntity("test1")});

        return params.iterator();
    }
}
