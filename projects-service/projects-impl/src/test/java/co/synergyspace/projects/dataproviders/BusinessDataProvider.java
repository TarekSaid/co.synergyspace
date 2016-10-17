package co.synergyspace.projects.dataproviders;

import co.synergyspace.projects.entities.impl.BusinessEntity;
import co.synergyspace.projects.entities.impl.ProjectEntity;
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

    @DataProvider(name = "savedBusinesses")
    public static Iterator<Object[]> createSavedBusinesses() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{new BusinessEntity(), new BusinessEntity("Hello")});
        params.add(new Object[]{new BusinessEntity("Test"), new BusinessEntity("Another")});
        params.add(new Object[]{new BusinessEntity("Third"), new BusinessEntity()});

        return params.iterator();
    }

    @DataProvider(name = "ownedProjects")
    public static Iterator<Object[]> createOwnedProjects() {
        Collection<Object[]> params = new ArrayList<>();

        // projects
        ProjectEntity p = new ProjectEntity(1L);
        ProjectEntity p2 = new ProjectEntity(2L);
        ProjectEntity p3 = new ProjectEntity(3L);

        // businesses
        BusinessEntity b = new BusinessEntity("test");
        b.create(p);
        b.create(p2);
        b.create(p3);

        params.add(new Object[]{"test", 1L, b, p});
        params.add(new Object[]{"test", 2L, b, p2});
        params.add(new Object[]{"test", 3L, b, p3});

        return params.iterator();
    }
}
