package bodnar.exception;

public class ManufacturerNotExistException extends RuntimeException{
    public ManufacturerNotExistException(Integer id) {
        super("Could not find 'manufacturer' with id=" + id);
    }
}
