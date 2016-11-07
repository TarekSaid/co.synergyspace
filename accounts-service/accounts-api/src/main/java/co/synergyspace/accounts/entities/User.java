package co.synergyspace.accounts.entities;

/**
 * Created by tarek on 04/11/16.
 */
public abstract class User {

    protected Long id;

    protected Business business;

    protected String username;

    protected String password;

    protected UserStatus status;

    protected Role role;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (business != null ? !business.equals(user.business) : user.business != null) return false;
        return username != null ? username.equals(user.username) : user.username == null;

    }

    @Override
    public int hashCode() {
        int result = business != null ? business.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
