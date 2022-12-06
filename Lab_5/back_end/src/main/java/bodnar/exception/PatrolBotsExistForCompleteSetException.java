package bodnar.exception;

public class PatrolBotsExistForCompleteSetException extends RuntimeException{
    public PatrolBotsExistForCompleteSetException(Integer id) {
        super("There are available patrol bots for 'complete_set' with id=" + id);
    }
}
