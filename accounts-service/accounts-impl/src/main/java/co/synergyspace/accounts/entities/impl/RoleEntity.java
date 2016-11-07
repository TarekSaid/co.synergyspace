package co.synergyspace.accounts.entities.impl;

import co.synergyspace.accounts.entities.Role;

import javax.persistence.*;

/**
 * Created by tarek on 04/11/16.
 */
@Entity
@Table(name = "roles")
public class RoleEntity extends Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }
}
