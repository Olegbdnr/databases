package bodnar.exception;

public class TeritoryNotFoundException extends RuntimeException{
    public TeritoryNotFoundException(Integer id) {
        super("Could not find 'teritory' with id=" + id);
    }
}
