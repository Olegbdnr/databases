package bodnar.exception;

public class CompleteSetsForManufacturerExistException extends RuntimeException{
    public CompleteSetsForManufacturerExistException(Integer id) {
        super("There are available complete sets for 'manufacturer' with id=" + id);
    }
}
