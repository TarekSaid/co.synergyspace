package co.synergyspace.posts.dataproviders;

import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
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

    @DataProvider(name = "writtenPosts")
    public static Iterator<Object[]> createWrittenPosts() {
        Collection<Object[]> params = new ArrayList<>();

        // posts
        PostEntity p1 = new PostEntity(1L);
        PostEntity p2 = new PostEntity(2L);
        PostEntity p3 = new PostEntity(3L);

        // businesses
        BusinessEntity b = new BusinessEntity("test");
        b.write(p1);
        b.write(p2);
        b.write(p3);

        params.add(new Object[]{"test", 1L, b, p1});
        params.add(new Object[]{"test", 2L, b, p2});
        params.add(new Object[]{"test", 3L, b, p3});

        return params.iterator();
    }
}
