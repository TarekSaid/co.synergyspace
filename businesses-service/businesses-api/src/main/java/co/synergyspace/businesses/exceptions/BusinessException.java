package co.synergyspace.businesses.exceptions;

/**
 * Created by tarek on 22/09/16.
 */
public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
