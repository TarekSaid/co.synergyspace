package co.synergyspace.businesses.dataproviders;

import co.synergyspace.businesses.entities.impl.BusinessEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Data Provider
 * Created by tarek on 12/09/16.
 */
public class BusinessDataProvider {

    @DataProvider(name = "businesses")
    public static Iterator<Object[]> createBusinesses() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{null});
        list.add(new Object[]{Arrays.asList(new BusinessEntity("hello"))});
        list.add(new Object[]{Arrays.asList(new BusinessEntity("test1"), new BusinessEntity("test2"))});

        return list.iterator();
    }

    @DataProvider(name = "business")
    public static Iterator<Object[]> createBusiness() {
        List<Object[]> params = new ArrayList<>();

        params.add(new Object[]{"teste", new BusinessEntity("teste")});
        params.add(new Object[]{"teste2", new BusinessEntity("teste2")});

        return params.iterator();
    }

    @DataProvider(name = "added-businesses")
    public static Iterator<Object[]> createSavedBusinesses() {
        List<Object[]> params = new ArrayList<>();

        params.add(new Object[]{new BusinessEntity("teste"), new BusinessEntity(1L, "teste")});
        params.add(new Object[]{new BusinessEntity("hello"), new BusinessEntity(2L, "hello")});

        return params.iterator();
    }
}
