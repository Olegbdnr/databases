package bodnar.exception;

public class CamerasExistForCompleteSetException extends RuntimeException{
    public CamerasExistForCompleteSetException(Integer id) {
        super("There are available cameras for 'complete_set' with id=" + id);
    }
}
