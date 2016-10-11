package co.synergyspace.posts.consumers;

import co.synergyspace.posts.entities.Business;

/**
 * Created by tarek on 10/10/16.
 */
public interface IBusinessConsumer<T extends Business> {

    void businessReceived(T business);
}
