package co.synergyspace.businesses.entities.impl;

import co.synergyspace.businesses.entities.Business;

import javax.persistence.*;

/**
 * Concrete Business entity.
 * Created by tarek on 12/09/16.
 */
@Entity
public class BusinessEntity extends Business {

    /**
     * the business id
     **/

    /**
     * the business name
     **/

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
        this.name = name;
    }

    /**
     * Creates a Business with its id and name.
     *
     * @param id   the business id
     * @param name the business name
     */
    public BusinessEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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
     * Sets the business id
     *
     * @param id the business id
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * Sets the business name
     *
     * @param name the business name
     */
    public void setName(String name) {
        this.name = name;
    }
}
