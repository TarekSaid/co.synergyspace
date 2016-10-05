package co.synergyspace.posts.controllers.impl;

import co.synergyspace.posts.controllers.IPostController;
import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import co.synergyspace.posts.services.IBusinessService;
import co.synergyspace.posts.services.IPostService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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
    @RequestMapping(value = "business/{name}/posts", method = RequestMethod.GET)
    public Iterable<PostEntity> listPostsFrom(@PathVariable String name) {
        Business business = businessService.findByName(name);
        return postService.findPostsFrom(business);
    }

    @Override
    @RequestMapping(value = "business/{name}/post/new", method = RequestMethod.POST)
    public Business writePost(@PathVariable String name, @RequestBody PostEntity post) {
        BusinessEntity business = businessService.findByName(name);
        return businessService.addPost(business, post);
    }
}
