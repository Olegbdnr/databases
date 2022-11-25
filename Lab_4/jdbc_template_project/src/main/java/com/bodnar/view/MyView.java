package com.bodnar.view;

import com.bodnar.controller.*;
import com.bodnar.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Component
public class MyView {

    @Autowired
    private CameraController cameraController;
    @Autowired
    private CompleteSetController completeSetController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private LocationController locationController;
    @Autowired
    private ManufacturerController manufacturerController;
    @Autowired
    private PatrolBotController patrolBotController;
    @Autowired
    private RouteController routeController;
    @Autowired
    private TeritoryController teritoryController;


    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Camera nullCamera = new Camera(null, null, null, null, null, null, null);
    private final CompleteSet nullCompleteSet = new CompleteSet(null, null, null, null, null, null, null, null);
    private final Employee nullEmployee = new Employee(null, null, null, null, null, null);
    private final Location nullLocation = new Location(null, null, null, null, null);
    private final Manufacturer nullManufacturer = new Manufacturer(null, null);
    private final PatrolBot nullPatrolBot = new PatrolBot(null, null);
    private final Route nullRoute = new Route(null, null, null, null, null);
    private final Teritory nullTeritory= new Teritory(null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Location");
        menu.put("11", "  11 - Create Location");
        menu.put("12", "  12 - Update Location");
        menu.put("13", "  13 - Delete from Location");
        menu.put("14", "  14 - Find all Locations");
        menu.put("15", "  15 - Find Location by ID");

        menu.put("2", "   2 - Table: Teritory");
        menu.put("21", "  21 - Create Teritory");
        menu.put("22", "  22 - Update Teritory");
        menu.put("23", "  23 - Delete from Teritory");
        menu.put("24", "  24 - Find all Teritorys");
        menu.put("25", "  25 - Find Teritory by ID");

        menu.put("3", "   3 - Table: Employee");
        menu.put("31", "  31 - Create Employee");
        menu.put("32", "  32 - Update Employee");
        menu.put("33", "  33 - Delete from Employee");
        menu.put("34", "  34 - Find all Employees");
        menu.put("35", "  35 - Find Employee by ID");

        menu.put("4", "   4 - Table: Manufacturer");
        menu.put("41", "  41 - Create Manufacturer");
        menu.put("42", "  42 - Update Manufacturer");
        menu.put("43", "  43 - Delete from Manufacturer");
        menu.put("44", "  44 - Find all Manufacturers");
        menu.put("45", "  45 - Find Manufacturer by ID");

        menu.put("5", "   5 - Table: CompleteSet");
        menu.put("51", "  51 - Create CompleteSet");
        menu.put("52", "  52 - Update CompleteSet");
        menu.put("53", "  53 - Delete from CompleteSet");
        menu.put("54", "  54 - Find all CompleteSets");
        menu.put("55", "  55 - Find CompleteSet by ID");

        menu.put("6", "   6 - Table: PatrolBot");
        menu.put("61", "  61 - Create PatrolBot");
        menu.put("62", "  62 - Update PatrolBot");
        menu.put("63", "  63 - Delete from PatrolBot");
        menu.put("64", "  64 - Find all PatrolBots");
        menu.put("65", "  65 - Find PatrolBot by ID");

        menu.put("7", "   7 - Table: Camera");
        menu.put("71", "  71 - Create Camera");
        menu.put("72", "  72 - Update Camera");
        menu.put("73", "  73 - Delete from Camera");
        menu.put("74", "  74 - Find all Cameras");
        menu.put("75", "  75 - Find Camera by ID");

        menu.put("8", "   8 - Table: Route");
        menu.put("81", "  81 - Create Route");
        menu.put("82", "  82 - Update Route");
        menu.put("83", "  83 - Delete from Route");
        menu.put("84", "  84 - Find all Routes");
        menu.put("85", "  85 - Find Route by ID");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);
        methodsMenu.put("11", this::createLocation);
        methodsMenu.put("12", this::updateLocation);
        methodsMenu.put("13", this::deleteFromLocation);
        methodsMenu.put("14", this::findAllLocation);
        methodsMenu.put("15", this::findLocationById);

        methodsMenu.put("21", this::createTeritory);
        methodsMenu.put("22", this::updateTeritory);
        methodsMenu.put("23", this::deleteFromTeritory);
        methodsMenu.put("24", this::findAllTeritorys);
        methodsMenu.put("25", this::findTeritoryById);

        methodsMenu.put("31", this::createEmployee);
        methodsMenu.put("32", this::updateEmployee);
        methodsMenu.put("33", this::deleteFromEmployee);
        methodsMenu.put("34", this::findAllEmployees);
        methodsMenu.put("35", this::findEmployeeById);

        methodsMenu.put("41", this::createManufacturer);
        methodsMenu.put("42", this::updateManufacturer);
        methodsMenu.put("43", this::deleteFromManufacturer);
        methodsMenu.put("44", this::findAllManufacturers);
        methodsMenu.put("45", this::findManufacturerById);

        methodsMenu.put("51", this::createCompleteSet);
        methodsMenu.put("52", this::updateCompleteSet);
        methodsMenu.put("53", this::deleteFromCompleteSet);
        methodsMenu.put("54", this::findAllCompleteSets);
        methodsMenu.put("55", this::findCompleteSetById);

        methodsMenu.put("61", this::createPatrolBot);
        methodsMenu.put("62", this::updatePatrolBot);
        methodsMenu.put("63", this::deleteFromPatrolBot);
        methodsMenu.put("64", this::findAllPatrolBots);
        methodsMenu.put("65", this::findPatrolBotById);

        methodsMenu.put("71", this::createCamera);
        methodsMenu.put("72", this::updateCamera);
        methodsMenu.put("73", this::deleteFromCamera);
        methodsMenu.put("74", this::findAllCameras);
        methodsMenu.put("75", this::findCameraById);

        methodsMenu.put("81", this::createRoute);
        methodsMenu.put("82", this::updateRoute);
        methodsMenu.put("83", this::deleteFromRoute);
        methodsMenu.put("84", this::findAllRoutes);
        methodsMenu.put("85", this::findRouteById);
    }

    private void selectAllTable() {
        findAllLocation();
        findAllTeritorys();
        findAllEmployees();
        findAllManufacturers();
        findAllCompleteSets();
        findAllPatrolBots();
        findAllCameras();
        findAllRoutes();
    }

    // region Location ---------------------------------------------------
    private void createLocation() {
        System.out.println("Input 'country': ");
        String country = input.nextLine();
        System.out.println("Input 'city': ");
        String city = input.nextLine();
        System.out.println("Input 'street': ");
        String street = input.nextLine();
        System.out.println("Input 'building_number': ");
        String buildingNumber = input.nextLine();
        Location location = new Location(null, country, city, street, buildingNumber);

        int count = locationController.create(location);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateLocation() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'country': ");
        String country = input.nextLine();
        System.out.println("Input new 'city': ");
        String city = input.nextLine();
        System.out.println("Input new 'street': ");
        String street = input.nextLine();
        System.out.println("Input new 'building_number': ");
        String buildingNumber = input.nextLine();
        Location location = new Location(null, country, city, street, buildingNumber);

        int count = locationController.update(id, location);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromLocation() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = locationController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllLocation() {
        System.out.println("\nTable: Location");
        List<Location> locations = locationController.findAll();
        for (Location location : locations) {
            System.out.println(location);
        }
    }

    private void findLocationById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Location> book = locationController.findById(id);
        System.out.println(book.orElse(nullLocation));
    }

    //endregion
    // region Teritory ---------------------------------------------------
    private void createTeritory() {
        System.out.println("Input 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input 'location_id': ");
        Integer locationId = Integer.valueOf(input.nextLine());

        Teritory teritory = new Teritory(null, phone, locationId);

        int count = teritoryController.create(teritory);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateTeritory() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'phone': ");
        String phone = input.nextLine();
        System.out.println("Input new 'location_id': ");
        Integer locationId = Integer.valueOf(input.nextLine());

        Teritory teritory = new Teritory(null, phone, locationId);

        int count = teritoryController.update(id, teritory);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromTeritory() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = teritoryController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllTeritorys() {
        System.out.println("\nTable: Teritory");
        List<Teritory> teritorys = teritoryController.findAll();
        for (Teritory teritory : teritorys) {
            System.out.println(teritory);
        }
    }

    private void findTeritoryById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Teritory> teritory = teritoryController.findById(id);
        System.out.println(teritory.orElse(nullTeritory));
    }

    // endregion
    // region Employee -------------------------------------------------
    private void createEmployee() {
        System.out.println("Input 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'gender': ");
        String gender = input.nextLine();
        System.out.println("Input 'birthday': ");
        String birthday = input.nextLine();
        System.out.println("Input 'teritory_id': ");
        Integer teritoryId = Integer.valueOf((input.nextLine()));

        Employee employee = new Employee(null, name, surname, gender, Date.valueOf(birthday), teritoryId);

        int count = employeeController.create(employee);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateEmployee() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'surname': ");
        String surname = input.nextLine();
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'gender': ");
        String gender = input.nextLine();
        System.out.println("Input new 'birthday': ");
        String birthday = input.nextLine();
        System.out.println("Input new 'teritory_id': ");
        Integer teritoryId = Integer.valueOf((input.nextLine()));

        Employee employee = new Employee(null, name, surname, gender, Date.valueOf(birthday), teritoryId);

        int count = employeeController.update(id, employee);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromEmployee() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = employeeController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllEmployees() {
        System.out.println("\nTable: Employee");
        List<Employee> employees = employeeController.findAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void findEmployeeById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Employee> person = employeeController.findById(id);
        System.out.println(person.orElse(nullEmployee));
    }
    //endregion
    // region Manufacturer -------------------------------------------------
    private void createManufacturer() {
        System.out.println("Input 'name': ");
        String name = input.nextLine();

        Manufacturer manufacturer = new Manufacturer(null, name);

        int count = manufacturerController.create(manufacturer);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateManufacturer() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'name': ");
        String name = input.nextLine();

        Manufacturer manufacturer = new Manufacturer(null, name);

        int count = manufacturerController.update(id, manufacturer);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromManufacturer() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = manufacturerController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllManufacturers() {
        System.out.println("\nTable: Manufacturer");
        List<Manufacturer> manufacturers = manufacturerController.findAll();
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println(manufacturer);
        }
    }

    private void findManufacturerById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Manufacturer> person = manufacturerController.findById(id);
        System.out.println(person.orElse(nullManufacturer));
    }
    // endregion
    // region CompleteSet -------------------------------------------------
    private void createCompleteSet() {
        System.out.println("Input 'manufacturer_id': ");
        Integer manufacturerId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'battery_reserve': ");
        BigDecimal batteryReserve = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input 'average_speed': ");
        Integer averageSpeed = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'width': ");
        BigDecimal width = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input 'length': ");
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input 'height': ");
        BigDecimal height = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));

        CompleteSet completeSet = new CompleteSet(null, manufacturerId, name, batteryReserve, averageSpeed,
                width, length, height);

        int count = completeSetController.create(completeSet);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCompleteSet() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'manufacturer_id': ");
        Integer manufacturerId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'battery_reserve': ");
        BigDecimal batteryReserve = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input new 'average_speed': ");
        Integer averageSpeed = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'width': ");
        BigDecimal width = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input new 'length': ");
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));
        System.out.println("Input new 'height': ");
        BigDecimal height = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));

        CompleteSet completeSet = new CompleteSet(null, manufacturerId, name, batteryReserve, averageSpeed,
                width, length, height);

        int count = completeSetController.update(id, completeSet);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCompleteSet() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = completeSetController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCompleteSets() {
        System.out.println("\nTable: CompleteSet");
        List<CompleteSet> completeSets =  completeSetController.findAll();
        for (CompleteSet completeSet : completeSets) {
            System.out.println(completeSet);
        }
    }

    private void findCompleteSetById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<CompleteSet> completeSet = completeSetController.findById(id);
        System.out.println(completeSet.orElse(nullCompleteSet));
    }

    // endregion
    // region PatrolBot -------------------------------------------------
    private void createPatrolBot() {
        System.out.println("Input 'complete_set_id': ");
        Integer completeSetId = Integer.valueOf((input.nextLine()));

        PatrolBot patrolBot = new PatrolBot(null, completeSetId);

        int count = patrolBotController.create(patrolBot);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePatrolBot() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'complete_set_id': ");
        Integer completeSetId = Integer.valueOf((input.nextLine()));

        PatrolBot patrolBot = new PatrolBot(null, completeSetId);

        int count = patrolBotController.update(id, patrolBot);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPatrolBot() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = patrolBotController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPatrolBots() {
        System.out.println("\nTable: PatrolBot");
        List<PatrolBot> patrolBots =  patrolBotController.findAll();
        for (PatrolBot patrolBot : patrolBots) {
            System.out.println(patrolBot);
        }
    }

    private void findPatrolBotById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<PatrolBot> patrolBot = patrolBotController.findById(id);
        System.out.println(patrolBot.orElse(nullPatrolBot));
    }

    // endregion
    // region Camera -------------------------------------------------
    private void createCamera() {
        System.out.println("Input 'complete_set_id': ");
        Integer completeSetId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'rotation_angle': ");
        Integer rotationAngle = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'night_mode': ");
        Byte nightMode = Byte.parseByte(input.nextLine());
        System.out.println("Input 'face_recognition': ");
        Byte faceRecognition = Byte.parseByte(input.nextLine());
        System.out.println("Input 'voice_recognition': ");
        Byte voiceRecognition = Byte.parseByte(input.nextLine());

        Camera camera = new Camera(null, completeSetId, name,rotationAngle, nightMode,
                faceRecognition, voiceRecognition);

        int count = cameraController.create(camera);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCamera() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'complete_set_id': ");
        Integer completeSetId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'rotation_angle': ");
        Integer rotationAngle = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'night_mode': ");
        Byte nightMode = Byte.parseByte(input.nextLine());
        System.out.println("Input new 'face_recognition': ");
        Byte faceRecognition = Byte.parseByte(input.nextLine());
        System.out.println("Input new 'voice_recognition': ");
        Byte voiceRecognition = Byte.parseByte(input.nextLine());

        Camera camera = new Camera(null, completeSetId, name,rotationAngle,
                nightMode, faceRecognition, voiceRecognition);

        int count = cameraController.update(id, camera);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCamera() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = cameraController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCameras() {
        System.out.println("\nTable: Camera");
        List<Camera> cameras =  cameraController.findAll();
        for (Camera camera : cameras) {
            System.out.println(camera);
        }
    }

    private void findCameraById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Camera> camera = cameraController.findById(id);
        System.out.println(camera.orElse(nullCamera));
    }

    // endregion
    // region Route -------------------------------------------------
    private void createRoute() {
        System.out.println("Input 'teritory_id': ");
        Integer teritoryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'patrol_bot_id': ");
        Integer patrolBotId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'name': ");
        String name = input.nextLine();
        System.out.println("Input 'length': ");
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));

        Route route = new Route(null, teritoryId, patrolBotId, name, length);

        int count = routeController.create(route);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateRoute() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input new 'teritory_id': ");
        Integer teritoryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'patrol_bot_id': ");
        Integer patrolBotId = Integer.valueOf((input.nextLine()));
        System.out.println("Input new 'name': ");
        String name = input.nextLine();
        System.out.println("Input new 'length': ");
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(input.nextLine()));

        Route route = new Route(null, teritoryId, patrolBotId, name, length);

        int count = routeController.update(id, route);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromRoute() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = routeController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllRoutes() {
        System.out.println("\nTable: Route");
        List<Route> routes =  routeController.findAll();
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    private void findRouteById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Route> route = routeController.findById(id);
        System.out.println(route.orElse(nullRoute));
    }

    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //endregion
}

