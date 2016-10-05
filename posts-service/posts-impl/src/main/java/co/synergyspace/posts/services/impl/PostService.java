package co.synergyspace.posts.services.impl;

import co.synergyspace.posts.entities.Business;
import co.synergyspace.posts.entities.impl.PostEntity;
import co.synergyspace.posts.repositories.IPostRepository;
import co.synergyspace.posts.services.IPostService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 26/09/16.
 */
@Service
public class PostService implements IPostService<PostEntity> {

    @Inject
    private IPostRepository<PostEntity> postRepository;

    @Override
    public Iterable<PostEntity> findPostsFrom(Business business) {
        return postRepository.findByBusiness(business);
    }
}
