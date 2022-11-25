package bodnar.exception;

public class CompleteSetNotExistException extends RuntimeException{
    public CompleteSetNotExistException(Integer id) {
        super("Could not find 'complete_set' with id=" + id);
    }
}
