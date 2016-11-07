package co.synergyspace.accounts.entities;

import java.util.List;

/**
 * Created by tarek on 07/11/16.
 */
public abstract class Business {

    protected Long id;

    protected String name;

    protected List<User> accounts;

    public Business() {
    }

    public Business(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<User> accounts) {
        this.accounts = accounts;
    }
}
