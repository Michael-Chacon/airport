package org.vuelosGlobales.generals.employee.adapter.in;

import org.vuelosGlobales.generals.employee.application.EmployeeService;
import org.vuelosGlobales.generals.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.generals.role.domain.Role;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.airline.domain.Airline;
import org.vuelosGlobales.generals.employee.domain.Employee;
import org.vuelosGlobales.systemAdministrator.airport.domain.AirportCityDTO;

import java.util.List;

public class EmployeeConsoleAdap {
    private final EmployeeService employeeService;
    Console console = new Console();

    public EmployeeConsoleAdap(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public  void crudEmployee(){
        menuEmployee: while (true){
            System.out.println("======================================");
            System.out.println("          MENÚ DE GESTIÓN DE EMPLEADOS ");
            System.out.println("======================================");
            System.out.println("\t1. Crear empleado");
            System.out.println("\t2. Actualizar empleado");
            System.out.println("\t3. Buscar empleado por ID");
            System.out.println("\t4. Eliminar empleado");
            System.out.println("\t5. Listar todos los empleados");
            System.out.println("\t6. Salir");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar un employee", "*");
                    String idEmployee = Helpers.validateExist(
                            "Ingrese el id del empleado, debe tener máximo 5 caracteres: ",
                            id -> employeeService.getEmployeeById(id)
                    );
                    String employeeName = console.stringNotNull("Nombre del empleado: ");
                    String ingressDate = console.stringNotNull("En que fecha ingresó el empleado a la empresa: ");
                    showRoles();
                    Role roleSelect = Helpers.transformAndValidateObj(
                            () -> employeeService.getRoleById(console.readInt("Seleccione el rol del employee: "))
                    );
                    int idRol = roleSelect.getId();

                    showAirlines();
                    Airline airlineSelect = Helpers.transformAndValidateObj(
                            () -> employeeService.getAirlineById(console.readInt("Seleccione la aerolínea por su id: "))
                    );
                    int idAirline = airlineSelect.getId();
                    showAirportes();
                    AirportCityDTO airportSelect = Helpers.transformAndValidateObj(
                            () -> employeeService.getAirportById(console.stringNotNull("Ingrese el id del aeropuerto al que pertenece el empleado: ").toUpperCase())
                    );
                    String idAirport = airportSelect.getId();

                    Employee objEmployee = new Employee();
                    objEmployee.setId(idEmployee);
                    objEmployee.setNombre(employeeName);
                    objEmployee.setIngressDate(ingressDate);
                    objEmployee.setIdRol(idRol);
                    objEmployee.setIdAirline(idAirline);
                    objEmployee.setIdAirport(idAirport);
                    employeeService.createEmployee(objEmployee);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una employee", "*");
                    showEmployees();
                    Employee employeeSelect = Helpers.transformAndValidateObj(
                            () -> employeeService.getEmployeeById(console.stringNotNull("Seleccione el employee por el id: ").toUpperCase())
                    );

                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + employeeSelect.getNombre(), "*");
                    String nameUp = console.stringNotNull("Nueva nombre: ");
                    String ingressUp = console.stringNotNull("Nueva fecha de ingreso del empleado: ");

                    int idRolUp;
                    String validate1 = console.stringNotNull("Quiere cambiar el rol del employee? (y/n): ");
                    if (validate1.equals("y")){
                        showRoles();
                        Role roleSelectUp = Helpers.transformAndValidateObj(
                                () -> employeeService.getRoleById(console.readInt("Seleccione el rol del employee"))
                        );
                        idRolUp = roleSelectUp.getId();
                    }else {
                        idRolUp = employeeSelect.getIdRol();
                    }

                    int idAirlineUp;
                    String validate2 = console.stringNotNull("Quiere cambiar la aerolínea en la que trabaja el employee? (y/n): ");
                    if (validate2.equals("y")){
                        showAirlines();
                        Airline airlineSelectUp = Helpers.transformAndValidateObj(
                                () -> employeeService.getAirlineById(console.readInt("Seleccione la aerolínea por su id: "))
                        );
                        idAirlineUp = airlineSelectUp.getId();
                    }else {
                        idAirlineUp = employeeSelect.getIdAirline();
                    }

                    String idAirportUp;
                    String validate = console.stringNotNull("Quiere cambiar el aeropuerto base del empleado? (y/n): ");
                    if (validate.equals("y")){
                        showAirportes();
                        AirportCityDTO airportSelectUp = Helpers.transformAndValidateObj(
                                () -> employeeService.getAirportById(console.stringNotNull("Ingrese el id del aeropuerto al que pertenece el empleado").toUpperCase())
                        );
                        idAirportUp = airportSelectUp.getId();
                    }else {
                        idAirportUp = employeeSelect.getIdAirport();
                    }

                    employeeSelect.setNombre(nameUp);
                    employeeSelect.setIngressDate(ingressUp);
                    employeeSelect.setIdRol(idRolUp);
                    employeeSelect.setIdAirline(idAirlineUp);
                    employeeSelect.setIdAirport(idAirportUp);
                    employeeService.updateEmployee(employeeSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de un employee", "*");
                    System.out.println();
                    showEmployees();
                    EmployeeRelationshipDTO getEmployee = Helpers.transformAndValidateObj(
                            () -> employeeService.getEmployeeInfoById(console.stringNotNull("Seleccione el employee por el id: ").toUpperCase())
                    );
                    CuadroDeTexto.drawHorizontal(100, "-");
                    System.out.printf("\n| %-4s | %-18s | %-18s | %-18s | %-25s | %-30s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLÍNEA", "AIRPORT");
                    CuadroDeTexto.drawHorizontal(100, "-");
                    System.out.printf("\n| %-4s | %-18s | %-18s | %-18s | %-25s | %-30s |%n", getEmployee.getId(), getEmployee.getName(), getEmployee.getIngressDate(), getEmployee.getRolName(), getEmployee.getAirlineName(), getEmployee.getAirportName());
                    CuadroDeTexto.drawHorizontal(100, "-");
                    System.out.println();
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un employee", "*");
                    showEmployees();
                    Employee showEmployeeD = Helpers.transformAndValidateObj(
                            () -> employeeService.getEmployeeById(console.stringNotNull("Seleccione el employee por el id: ").toUpperCase())
                    );
                    String employeeDelete = showEmployeeD.getId();
                    employeeService.deleteEmployee(employeeDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Employee eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Empleados registradas", "*");
                    showEmployees();
                    System.out.println();
                    break;
                case 6:
                    break menuEmployee;
            }
        }
    }

    public void showEmployees(){
        List<EmployeeRelationshipDTO> employeeList = employeeService.getAllEmployeesInfo(false, 0);
        System.out.println("Listado de empleados:");
        CuadroDeTexto.drawHorizontal(151, "-");
        System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLINEA", "AIRPORT");
        employeeList.forEach(employee -> {
            CuadroDeTexto.drawHorizontal(151, "-");
            System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", employee.getId(), employee.getName(), employee.getIngressDate(), employee.getRolName(), employee.getAirlineName(), employee.getAirportName());
        });
        CuadroDeTexto.drawHorizontal(151, "-");
        System.out.println();
    }

    public void showAirlines(){
        List<Airline> airlineList = employeeService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        airlineList.forEach(airline -> {
            CuadroDeTexto.drawHorizontal(27, "-");
            System.out.printf("\n| %-4s | %-16s |%n", airline.getId(), airline.getName());
        });
        CuadroDeTexto.drawHorizontal(27, "-");
        System.out.println();
    }

    public void showAirportes(){
        List<AirportCityDTO> airports = employeeService.getAllAirport();
        System.out.println("Listado de estados:");
        CuadroDeTexto.drawHorizontal(57, "-");
        System.out.printf("\n| %-5s | %-22s | %-20s |%n", "ID", "NOMBRE", "CIUDAD");
        airports.forEach(airport -> {
            CuadroDeTexto.drawHorizontal(57, "-");
            System.out.printf("\n| %-5s | %-22s | %-20s |%n", airport.getId(), airport.getNameAirport(), airport.getNameCity());
        });
        CuadroDeTexto.drawHorizontal(57, "-");
        System.out.println();
    }

    public void showRoles(){
        List<Role> roleList = employeeService.getAllRoles();
        System.out.println("Listado de roles:");
        CuadroDeTexto.drawHorizontal(33, "-");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        roleList.forEach(role -> {
            CuadroDeTexto.drawHorizontal(33, "-");
            System.out.printf("\n| %-4s | %-16s |%n", role.getId(), role.getName());
        });
        CuadroDeTexto.drawHorizontal(33, "-");
        System.out.println();
    }
}
