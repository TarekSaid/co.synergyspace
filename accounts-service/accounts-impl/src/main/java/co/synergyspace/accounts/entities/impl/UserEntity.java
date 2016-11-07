package co.synergyspace.accounts.entities.impl;

import co.synergyspace.accounts.entities.Business;
import co.synergyspace.accounts.entities.Role;
import co.synergyspace.accounts.entities.User;

import javax.persistence.*;

/**
 * Created by tarek on 04/11/16.
 */
@Entity
@Table(name = "users")
public class UserEntity extends User {

    public UserEntity() {
    }

    public UserEntity(String username) {
        super(username);
    }

    public UserEntity(Long id, String username) {
        super(id, username);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID")
    public Business getBusiness() {
        return business;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public Role getRole() {
        return role;
    }
}
