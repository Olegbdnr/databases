package bodnar.exception;

public class CameraNotExistException extends RuntimeException{
    public CameraNotExistException(Integer id) {
        super("Could not find 'camera' with id=" + id);
    }
}
