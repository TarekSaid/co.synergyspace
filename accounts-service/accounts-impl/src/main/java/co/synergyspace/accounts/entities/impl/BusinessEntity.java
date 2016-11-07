package co.synergyspace.accounts.entities.impl;

import co.synergyspace.accounts.entities.Business;
import co.synergyspace.accounts.entities.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tarek on 04/11/16.
 */
@Entity
@Table(name = "businesses")
public class BusinessEntity extends Business {

    public BusinessEntity() {
    }

    public BusinessEntity(String name) {
        super(name);
    }

    /**
     * Returns the business id
     *
     * @return business id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "business")
    public List<User> getAccounts() {
        return accounts;
    }
}
