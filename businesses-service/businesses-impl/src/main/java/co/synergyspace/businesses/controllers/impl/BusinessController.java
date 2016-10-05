package co.synergyspace.businesses.controllers.impl;

import co.synergyspace.businesses.controllers.IBusinessController;
import co.synergyspace.businesses.entities.Business;
import co.synergyspace.businesses.entities.impl.BusinessEntity;
import co.synergyspace.businesses.services.IBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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
        return businessService.findByName(name);
    }

    @Override
    @RequestMapping(value = "business/new", method = RequestMethod.POST)
    public BusinessEntity addBusiness(@RequestBody BusinessEntity business) {
        return businessService.addBusiness(business);
    }
}
