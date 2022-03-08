package pl.first.firstjava.exceptions;

import java.io.IOException;

public class LoadSceneFailException extends IOException {
    public LoadSceneFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
