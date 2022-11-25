package bodnar.exception;

public class EmployeeNotExistException extends RuntimeException{
    public EmployeeNotExistException(Integer id) {
        super("Could not find 'employee' with id=" + id);
    }
}
