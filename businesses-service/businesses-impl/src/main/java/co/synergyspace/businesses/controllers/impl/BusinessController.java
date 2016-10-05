package co.synergyspace.businesses.controllers.impl;

import co.synergyspace.businesses.controllers.IBusinessController;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.exceptions.impl.BusinessExistsException;
import co.synergyspace.businesses.exceptions.impl.BusinessNotFoundException;
import co.synergyspace.businesses.services.IBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by tarek on 12/09/16.
 */
@RestController
public class BusinessController implements IBusinessController<BusinessEntity> {

    @Inject
    private IBusinessService<BusinessEntity> businessService;

    @Override
    @RequestMapping(value = "businesses", method = RequestMethod.GET)
    public Iterable<BusinessEntity> listBusinesses() {
        return businessService.findAll();
    }

    @Override
    @RequestMapping(value = "business/{name}", method = RequestMethod.GET)
    public BusinessEntity getBusiness(@PathVariable String name) {
        return Optional.ofNullable(businessService.findByName(name)).orElseThrow(
                () -> new BusinessNotFoundException(name));
    }

    @Override
    @RequestMapping(value = "business/new", method = RequestMethod.POST)
    public BusinessEntity addBusiness(@RequestBody BusinessEntity business) {
        return Optional.ofNullable(businessService.addBusiness(business)).orElseThrow(
                () -> new BusinessExistsException(business.getName()));
    }
}
