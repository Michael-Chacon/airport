package org.vuelosGlobales.salesAgent.customer.adapter.in;

import org.vuelosGlobales.salesAgent.customer.domain.Customer;
import org.vuelosGlobales.generals.country.domain.Country;
import org.vuelosGlobales.salesAgent.customer.application.CustomerService;
import org.vuelosGlobales.salesAgent.customer.domain.CustomerDocuDTO;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.document.domain.Document;

import java.util.List;

public class CustomerConsoleAdapter {
    private final CustomerService customerService;
    Console console = new Console();

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public  void crudCustomer(){
        menuCustomer: while (true){
            System.out.println("======================================");
            System.out.println("            MENÚ DE CLIENTES          ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Cliente");
            System.out.println("\t2. Actualizar Cliente");
            System.out.println("\t3. Buscar Cliente por ID");
            System.out.println("\t4. Eliminar Cliente");
            System.out.println("\t5. Listar todas las Clientes");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = console.readInt("");

            switch (choice){
                case 1:
                    CuadroDeTexto.dibujarCuadroDeTexto("Registrar cliente", "*");
                    String name = console.stringNotNull("Nombre del cliente: ");
                    String lastName = console.stringNotNull("Apellidos del cliente: ");
                    int age = console.readInt("Ingrese la edad del cliente: ");
                    int docu = console.readInt("Ingrese el numero de identificacion del cliente: ");
                    showDocuments();
                    Document getDocument = Helpers.transformAndValidateObj(
                            () -> customerService.getDocumentById(console.readInt("Seleccione el documento por el ID: "))
                    );
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setLastName(lastName);
                    customer.setAge(age);
                    customer.setNroId(docu);
                    customer.setIdDocument(getDocument.getId());
                    customerService.createCustomer(customer);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, "-");
                    break;

                case 2:
                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar información de una cliente", "*");
                    showCustomers();
                    Customer customerSelect = Helpers.transformAndValidateObj(
                            () -> customerService.getCustomerById(console.readInt("Seleccione la cliente por el id: "))
                    );

                    CuadroDeTexto.dibujarCuadroDeTexto("Actualizar datos de " + customerSelect.getName(), "*");

                    String newName;
                    String v1 = console.stringNotNull("Quiere cambiar el nombre del cliente? (y/n): ");
                    if (v1.equals("s")){
                        newName = console.stringNotNull("Ingrese el nuevo nombre: ");
                    }else {
                        newName = customerSelect.getName();
                    }

                    String newLastname;
                    String v2 = console.stringNotNull("Quiere cambiar el apellido del cliente? (y/n): ");
                    if (v2.equals("s")){
                        newLastname = console.stringNotNull("Ingrese el nuevo apellido: ");
                    }else {
                        newLastname = customerSelect.getLastName();
                    }

                    int newNro;
                    String v3 = console.stringNotNull("Quiere cambiar el numero de identificacion del cliente? (y/n): ");
                    if (v3.equals("s")){
                        newNro = console.readInt("Ingrese el nuevo numero de identificacion: ");
                    }else {
                        newNro = customerSelect.getNroId();
                    }

                    int newAge;
                    String v4 = console.stringNotNull("Quiere cambiar la edad del cliente? (y/n): ");
                    if (v4.equals("s")){
                        newAge = console.readInt("Ingrese el nueva edad del cliente: ");
                    }else {
                        newAge = customerSelect.getAge();
                    }

                    int idDocument;
                    String validate = console.stringNotNull("Quiere cambiar el tipo de documento del cliente? (y/n): ");
                    if (validate.equals("y")){
                        showDocuments();
                        Document getObj = Helpers.transformAndValidateObj(
                                () -> customerService.getDocumentById(console.readInt("A que país pertenece la cliente, seleccione por el por el id: "))
                        );
                        idDocument = getObj.getId();
                    }else {
                        idDocument = customerSelect.getIdDocument();
                    }
                    customerSelect.setName(newName);
                    customerSelect.setLastName(newLastname);
                    customerSelect.setNroId(newNro);
                    customerSelect.setAge(newAge);
                    customerSelect.setIdDocument(idDocument);
                    customerService.updateCustomer(customerSelect);
                    CuadroDeTexto.dibujarCuadroDeTexto(null, null);
                    break;

                case 3:
                    CuadroDeTexto.dibujarCuadroDeTexto("Mostrar info de una cliente", "*");
                    showCustomers();
                    CustomerDocuDTO showCustomer = Helpers.transformAndValidateObj(
                            () -> customerService.getCusDocById(console.readInt("Seleccione la cliente por el id: "))
                    );

                    System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD", "DOCU");
                    CuadroDeTexto.drawHorizontal(120, "-");
                    System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", showCustomer.getId(), showCustomer.getNameCustomer(), showCustomer.getLastName(), showCustomer.getNroId(), showCustomer.getAge(), showCustomer.getNameDocument());
                    CuadroDeTexto.drawHorizontal(120, "-");
                    System.out.println();
                    break;

                case 4:
                    CuadroDeTexto.dibujarCuadroDeTexto("Eliminar un cliente", "*");
                    showCustomers();
                    Customer showCustomerD = Helpers.transformAndValidateObj(
                            () -> customerService.getCustomerById(console.readInt("Seleccione la cliente por el id: "))
                    );
                    int customerDelete = showCustomerD.getId();
                    customerService.deleteCustomer(customerDelete);
                    CuadroDeTexto.dibujarCuadroDeTexto("Cliente eliminado con éxito", null);
                    break;
                case 5:
                    CuadroDeTexto.dibujarCuadroDeTexto("Clientes registrados", "*");
                    showCustomers();
                    System.out.println();
                    break;
                case 6:
                    break menuCustomer;
            }
        }
    }

    public void showCustomers(){
        List<CustomerDocuDTO> customerList = customerService.getAllCustDoc();
        CuadroDeTexto.drawHorizontal(120, "-");
        System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD", "DOCU");
        customerList.forEach(customer -> {
            CuadroDeTexto.drawHorizontal(120, "-");
            System.out.println(String.format("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |", customer.getId(), customer.getNameCustomer(), customer.getLastName(), customer.getNroId(), customer.getAge(), customer.getNameDocument()));
        });
        CuadroDeTexto.drawHorizontal(120, "-");
        System.out.println();
    }

    public void showDocuments(){
        List<Document> documents = customerService.getAllDocuments();
        System.out.println("Tipos de documentos:");
        CuadroDeTexto.drawHorizontal(35, "-");
        System.out.printf("\n| %-4s | %-18s |%n", "ID", "NOMBRE");
        documents.forEach(document -> {
            CuadroDeTexto.drawHorizontal(35, "-");
            System.out.printf("\n| %-4s | %-18s |%n", document.getId(), document.getName());
        });
        CuadroDeTexto.drawHorizontal(35, "-");
        System.out.println();
    }
}
