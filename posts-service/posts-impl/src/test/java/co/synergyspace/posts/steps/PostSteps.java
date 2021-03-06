package co.synergyspace.posts.steps;

import co.synergyspace.posts.PostsApplication;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import co.synergyspace.posts.repositories.IPostRepository;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by tarek on 26/09/16.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "spring.cloud.config.enabled:false")
@ContextConfiguration(classes = {PostsApplication.class})
@ActiveProfiles("test")
public class PostSteps extends AbstractTestNGSpringContextTests implements En {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Inject
    private IPostRepository<PostEntity> postRepository;

    @Inject
    private TestRestTemplate restTemplate;

    private BusinessEntity business;

    private PostEntity post;

    private String result;

    public PostSteps() {
        Given("^that I have the Business \"([^\"]*)\"$", (String name) -> {
            businessRepository.save(new BusinessEntity(name));
            business = businessRepository.findByName(name);
            assertThat(business).isNotNull();
        });

        Given("^that I have the following post(s)?$", (String plural, DataTable dataTable) -> {
            List<PostEntity> posts = dataTable.asList(PostEntity.class);

            posts.forEach(p -> business.write(p));
            businessRepository.save(business);

            List<PostEntity> postList = (List<PostEntity>) postRepository.findByBusiness(business);
            assertThat(postList).isNotEmpty();

            if (plural == null) {
                post = postList.stream().findFirst().get();
            }
        });

        When("^I list my posts$", () -> {
            this.result = restTemplate.getForObject("/{name}/posts", String.class, business.getName());
        });

        When("^I add the Post \"([^\"]*)\"$", (String content) -> {
            Post post = new PostEntity(content);
            this.result = restTemplate.postForObject("/{name}/posts/new", post, String.class, business.getName());
        });

        When("^I search for the post$", () -> {
            this.result = restTemplate.getForObject("/{name}/posts/{id}", String.class, business.getName(), post.getId());
        });

        When("^I reply \"([^\"]*)\"$", (String reply) -> {
            Post replyPost = new PostEntity(reply);
            this.result = restTemplate.postForObject("/{name}/posts/{id}/reply", replyPost, String.class, business.getName(), post.getId());
        });

        Then("^I should see$", (String json) -> {
            assertThat(result).matches(json);
        });
    }
}