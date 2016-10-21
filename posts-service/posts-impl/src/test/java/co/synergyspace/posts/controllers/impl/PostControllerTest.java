package co.synergyspace.posts.controllers.impl;

import co.synergyspace.posts.dataproviders.BusinessDataProvider;
import co.synergyspace.posts.dataproviders.PostDataProvider;
import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.posts.exceptions.impl.PostNotWrittenException;
import co.synergyspace.posts.services.IBusinessService;
import co.synergyspace.posts.services.IPostService;
import mockit.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 26/09/16.
 */
@Test
public class PostControllerTest {

    @Tested
    private PostController postController;

    @Injectable
    private IBusinessService<BusinessEntity> businessService;

    @Injectable
    private IPostService<PostEntity> postService;

    public void listPostsFromShouldCallBusinessService() {
        postController.listPostsFrom("");

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void listPostsFromShouldThrowExceptionWhenNameNotFound() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        postController.listPostsFrom("");
    }

    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void listPostsFromShouldCallPostService(String name, BusinessEntity business) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        postController.listPostsFrom(name);

        new Verifications() {{
            postService.findPostsFrom(business);
        }};
    }

    @Test(dataProvider = "businessPosts", dataProviderClass = PostDataProvider.class)
    public void listPostsFromShouldReturnFoundPosts(Business business, List<Post> posts) {
        new Expectations() {{
            businessService.findByName(business.getName());
            result = business;

            postService.findPostsFrom(business);
            result = posts;
        }};

        assertThat(postController.listPostsFrom(business.getName())).isEqualTo(posts);
    }

    public void writePostShouldCallBusinessService() {
        postController.writePost("", new PostEntity());

        new Verifications() {{
            businessService.findByName(anyString);
        }};
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void writePostShouldThrowExceptionWhenNameNotFound(@Mocked PostEntity post) {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        postController.writePost("", post);
    }

    @Test(dataProvider = "businesses", dataProviderClass = BusinessDataProvider.class)
    public void writePostShouldCallPostService(String name, BusinessEntity business) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        postController.writePost(name, new PostEntity());

        new Verifications() {{
            businessService.addPost(business, (PostEntity) any);
        }};
    }

    @Test(dataProvider = "posts", dataProviderClass = PostDataProvider.class)
    public void addPostShouldReturnSavedPost(PostEntity post, Business business) {
        new Expectations() {{
            businessService.addPost((BusinessEntity) any, post);
            result = business;
        }};

        assertThat(postController.writePost("", post)).isEqualTo(business);
    }

    @Test(expectedExceptions = BusinessNotFoundException.class)
    public void getPostShouldThrowBusinessNotFoundExceptionWhenNull() {
        new Expectations() {{
            businessService.findByName(anyString);
            result = null;
        }};

        postController.getPost("", 1L);
    }

    @Test(dataProviderClass = BusinessDataProvider.class, dataProvider = "writtenPosts")
    public void getPostShouldReturnPostFound(String name, Long id, Business business, Post post) {
        new Expectations() {{
            businessService.findByName(name);
            result = business;
        }};

        assertThat(postController.getPost(name, id)).isEqualTo(post);
    }

    @Test(expectedExceptions = PostNotWrittenException.class)
    public void getPostShouldThrowPostNotWrittenExceptionWhenNull() {
        postController.getPost("", 1L);
    }
}
