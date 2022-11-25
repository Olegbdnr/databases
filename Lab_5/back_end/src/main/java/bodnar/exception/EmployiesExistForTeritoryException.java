package bodnar.exception;

public class EmployiesExistForTeritoryException extends RuntimeException{
    public EmployiesExistForTeritoryException(Integer id) {
        super("There are available employees for 'teritory' with id=" + id);
    }
}
