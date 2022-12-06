package bodnar.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(Integer id) {
        super("Could not find 'location' with id=" + id);
    }
}
