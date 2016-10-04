package co.synergyspace.posts.services.impl;

import co.synergyspace.posts.dataproviders.PostDataProvider;
import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import co.synergyspace.posts.repositories.IPostRepository;
import co.synergyspace.posts.repositories.impl.PostRepository;
import mockit.*;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tarek on 26/09/16.
 */
@Test
public class PostServiceTest {

    @Tested
    private PostService postService;

    @Injectable
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Injectable
    private IPostRepository<PostEntity> postRepository;

    public void findPostsFromShouldCallPostRepository(@Mocked Business business) {
        postService.findPostsFrom(business);

        new Verifications() {{
            postRepository.findByBusiness(business);
        }};
    }

    @Test(dataProvider = "businessPosts", dataProviderClass = PostDataProvider.class)
    public void findPostsFromShouldReturnFoundPosts(Business business, List<Post> posts) {
        new Expectations() {{
            postRepository.findByBusiness(business);
            result = posts;
        }};

        assertThat(postService.findPostsFrom(business)).isEqualTo(posts);
    }
}
