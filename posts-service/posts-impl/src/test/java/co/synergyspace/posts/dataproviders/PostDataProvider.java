package co.synergyspace.posts.dataproviders;

import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tarek on 26/09/16.
 */
public class PostDataProvider {

    @DataProvider(name = "businessPosts")
    public static Iterator<Object[]> createPosts() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{new BusinessEntity(""), null});
        params.add(new Object[]{new BusinessEntity("test"), new ArrayList<Post>()});
        params.add(new Object[]{new BusinessEntity("hello"), Arrays.asList(new PostEntity(1L))});
        params.add(new Object[]{new BusinessEntity("name"),
                Arrays.asList(new PostEntity(1L), new PostEntity(2L), new PostEntity(3L))});

        return params.iterator();
    }

    @DataProvider(name = "posts")
    public static Iterator<Object[]> createSavedPosts() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[]{new PostEntity("test"), null});
        params.add(new Object[]{new PostEntity("hello"), new BusinessEntity("hello")});

        return params.iterator();
    }

    @DataProvider(name = "replies")
    public static Iterator<Object[]> createReplies() {
        Collection<Object[]> params = new ArrayList<>();

        params.add(new Object[] {new PostEntity(), new PostEntity("reply")});
        params.add(new Object[] {new PostEntity("post"), new PostEntity("reply")});
        params.add(new Object[] {new PostEntity("reply"), new PostEntity("help")});

        return params.iterator();
    }
}
