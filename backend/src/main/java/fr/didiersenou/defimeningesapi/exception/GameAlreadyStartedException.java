package fr.didiersenou.defimeningesapi.exception;

public class GameAlreadyStartedException extends RuntimeException {
    public GameAlreadyStartedException(String message) {
        super(message);
    }
}
