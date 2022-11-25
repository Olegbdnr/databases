package bodnar.exception;

public class PatrolBotNotExistException extends RuntimeException{
    public PatrolBotNotExistException(Integer id) {
        super("Could not find 'patrol_bot' with id=" + id);
    }
}
