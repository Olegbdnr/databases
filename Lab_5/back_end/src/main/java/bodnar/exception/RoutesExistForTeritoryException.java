package bodnar.exception;

public class RoutesExistForTeritoryException extends RuntimeException{
    public RoutesExistForTeritoryException(Integer id) {
        super("There are available routes for 'teritory' with id=" + id);
    }
}
