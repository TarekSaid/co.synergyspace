package co.synergyspace.accounts.exceptions;

/**
 * Created by tarek on 07/11/16.
 */
public abstract class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}
