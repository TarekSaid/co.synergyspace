package co.synergyspace.posts.services.impl;

import co.synergyspace.posts.entities.Post;
import co.synergyspace.posts.entities.impl.BusinessEntity;
import co.synergyspace.posts.repositories.IBusinessRepository;
import co.synergyspace.posts.services.IBusinessService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by tarek on 03/10/16.
 */
@Service
public class BusinessService implements IBusinessService<BusinessEntity> {

    @Inject
    private IBusinessRepository<BusinessEntity> businessRepository;

    @Override
    public BusinessEntity findByName(String name) {
        return businessRepository.findByName(name);
    }

    @Override
    public BusinessEntity addPost(BusinessEntity business, Post post) {
        business.write(post);
        return businessRepository.save(business);
    }
}
