package bodnar.controller;


import bodnar.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CameraNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cameraNotExistHandler(CameraNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(CamerasExistForCompleteSetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String camerasExistForCompleteSetHandler(CamerasExistForCompleteSetException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(CompleteSetNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String completeSetNotExistHandler(CompleteSetNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(CompleteSetsForManufacturerExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String completeSetsForManufacturerExistHandler(CompleteSetsForManufacturerExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(EmployeeNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotExistHandler(EmployeeNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(EmployiesExistForTeritoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String employiesExistForTeritoryHandler(EmployiesExistForTeritoryException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String locationNotFoundHandler(LocationNotFoundException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(ManufacturerNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String manufacturerNotExistHandler(ManufacturerNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(PatrolBotNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String patrolBotNotExistHandler(PatrolBotNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(PatrolBotsExistForCompleteSetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String patrolBotsExistForCompleteSetHandler(PatrolBotsExistForCompleteSetException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(PatrolBotsExistForTeritoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String patrolBotsExistForTeritoryHandler(PatrolBotsExistForTeritoryException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(RouteNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String routeNotExistHandler(RouteNotExistException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(RoutesExistForTeritoryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String routesExistForTeritoryHandler(RoutesExistForTeritoryException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(TeritoriesExistForLocationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String teritoriesExistForLocationHandler(TeritoriesExistForLocationException e) {return e.getMessage();}

    @ResponseBody
    @ExceptionHandler(TeritoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teritoryNotFoundHandler(TeritoryNotFoundException e) {return e.getMessage();}
}
