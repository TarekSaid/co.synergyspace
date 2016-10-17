package co.synergyspace.projects.consumers;

import co.synergyspace.projects.entities.Business;

/**
 * Created by tarek on 17/10/16.
 */
public interface IBusinessConsumer {

    void businessReceived(String name);
}
