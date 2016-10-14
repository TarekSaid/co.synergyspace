package co.synergyspace.projects.dataproviders;

import co.synergyspace.projects.entities.impl.BusinessEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 14/10/16.
 */
public class BusinessDataProvider {

    @DataProvider(name = "businesses")
    public static Iterator<Object[]> createBusinesses() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"", new BusinessEntity("")});
        params.add(new Object[]{"hello", new BusinessEntity("hello")});
        params.add(new Object[]{"Test", new BusinessEntity("Another")});
        params.add(new Object[]{"Test", new BusinessEntity()});

        return params.iterator();
    }
}
