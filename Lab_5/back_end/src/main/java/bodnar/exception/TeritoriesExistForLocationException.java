package bodnar.exception;

public class TeritoriesExistForLocationException extends RuntimeException{
    public TeritoriesExistForLocationException(Integer id) {
        super("There are available teritories for 'location' with id=" + id);
    }
}
