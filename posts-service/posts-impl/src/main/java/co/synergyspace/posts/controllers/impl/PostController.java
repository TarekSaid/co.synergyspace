package co.synergyspace.posts.controllers.impl;

import co.synergyspace.posts.controllers.IPostController;
import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.posts.exceptions.impl.PostNotWrittenException;
import co.synergyspace.posts.services.IBusinessService;
import co.synergyspace.posts.services.IPostService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by tarek on 26/09/16.
 */
@RestController
public class PostController implements IPostController<PostEntity> {

    @Inject
    private IBusinessService<BusinessEntity> businessService;

    @Inject
    private IPostService<PostEntity> postService;

    @Override
    @RequestMapping(value = "{name}/posts", method = RequestMethod.GET)
    public Iterable<PostEntity> listPostsFrom(@PathVariable String name) {
        Business business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
        return postService.findPostsFrom(business);
    }

    @Override
    @RequestMapping(value = "{name}/posts/new", method = RequestMethod.POST)
    public Business writePost(@PathVariable String name, @RequestBody PostEntity post) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
        return businessService.addPost(business, post);
    }

    @Override
    @RequestMapping(value = "{name}/posts/{id}", method = RequestMethod.GET)
    public PostEntity getPost(@PathVariable String name, @PathVariable Long id) {
        BusinessEntity business = Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));

        return (PostEntity) business.getPosts().parallelStream().filter(p -> p.getId() == id).findAny()
                .orElseThrow(() ->  new PostNotWrittenException(id));
    }
}
