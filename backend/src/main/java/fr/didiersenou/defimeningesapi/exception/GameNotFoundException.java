package fr.didiersenou.defimeningesapi.exception;

public class GameNotFoundException extends ResourceNotFoundException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
