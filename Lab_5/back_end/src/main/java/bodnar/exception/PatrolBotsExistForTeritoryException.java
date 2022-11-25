package bodnar.exception;

public class PatrolBotsExistForTeritoryException extends RuntimeException{
    public PatrolBotsExistForTeritoryException(Integer id) {
        super("There are available patrol bots for 'teritory' with id=" + id);
    }
}
