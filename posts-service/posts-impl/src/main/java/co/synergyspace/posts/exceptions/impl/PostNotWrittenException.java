package co.synergyspace.posts.exceptions.impl;

import co.synergyspace.posts.exceptions.PostException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by tarek on 18/10/16.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class PostNotWrittenException extends PostException {

    public PostNotWrittenException(Long id) {
        super("The post with id " + id + " was not written by you.");
    }
}
