package co.synergyspace.businesses.entities.impl;

import co.synergyspace.businesses.entities.Business;

import javax.persistence.*;

/**
 * Concrete Business entity.
 * Created by tarek on 12/09/16.
 */
@Entity(name = "businesses")
public class BusinessEntity extends Business {

    /**
     * Business empty contructor.
     */
    public BusinessEntity() {
    }

    /**
     * Creates a business with its name.
     *
     * @param name the business name.
     */
    public BusinessEntity(String name) {
        super(name);
    }

    /**
     * Creates a Business with its id and name.
     *
     * @param id   the business id
     * @param name the business name
     */
    public BusinessEntity(Long id, String name) {
        super(id, name);
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

    /**
     * Returns the business name
     *
     * @return business name
     */
    @Column
    public String getName() {
        return name;
    }
}
