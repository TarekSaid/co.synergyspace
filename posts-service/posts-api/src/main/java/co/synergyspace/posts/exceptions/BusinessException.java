package co.synergyspace.posts.exceptions;

/**
 * Created by tarek on 14/10/16.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
