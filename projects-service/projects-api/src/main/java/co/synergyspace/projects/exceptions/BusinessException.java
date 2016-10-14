package co.synergyspace.projects.exceptions;

/**
 * Created by tarek on 14/10/16.
 */
public abstract class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
