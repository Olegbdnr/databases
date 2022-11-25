package bodnar.exception;

public class RouteNotExistException extends RuntimeException{
    public RouteNotExistException(Integer id) {
        super("Could not find 'route' with id=" + id);
    }
}
