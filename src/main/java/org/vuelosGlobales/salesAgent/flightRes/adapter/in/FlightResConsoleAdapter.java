package org.vuelosGlobales.salesAgent.flightRes.adapter.in;

import org.vuelosGlobales.generals.passenger.domain.Passenger;
import org.vuelosGlobales.generals.trip.domain.Trip;
import org.vuelosGlobales.generals.trip.domain.TripAirportDTO;
import org.vuelosGlobales.salesAgent.customer.domain.Customer;
import org.vuelosGlobales.salesAgent.customer.domain.CustomerDocuDTO;
import org.vuelosGlobales.salesAgent.flightRes.application.FlightResService;
import org.vuelosGlobales.salesAgent.flightRes.domain.FlightRes;
import org.vuelosGlobales.shared.Console;
import org.vuelosGlobales.shared.CuadroDeTexto;
import org.vuelosGlobales.shared.Helpers;
import org.vuelosGlobales.systemAdministrator.fare.domain.Fare;

import java.util.List;

public class FlightResConsoleAdapter {
    private final FlightResService flightResService;
    Console console = new Console();

    public FlightResConsoleAdapter(FlightResService flightResService) {
        this.flightResService = flightResService;
    }


    public void crudFlightRes(){
        makeReservation: while (true){
            System.out.println("====================================");
            System.out.println("          GESTIÓN DE VUELOS         ");
            System.out.println("====================================");
            System.out.println("\t1. Ver los vuelos disponibles");
            System.out.println("\t2. Hacer reservación");
            System.out.println("\t3. Ver reservas por clientes");
            System.out.println("\t4. Cancelar reservación");
            System.out.println("\t5. Volver al menú anterior");
            System.out.println("====================================");
            int choise = console.readInt("Seleccione una opción: ");

            switch (choise){
                case 1:
                    mostrarViajes();
                    break;
                case 2:
                    mostrarClientes();
                    Customer cliente = seleccionarCliente();
                    mostrarViajes();
                    TripAirportDTO vuelo = seleccionarVuelo();
                    int idReserva = generarReserva(vuelo.getId());
                    FlightRes reservation = Helpers.transformAndValidateObj(
                            () -> flightResService.showOneFlightBooking(idReserva)
                    );
                    List<Fare> fares = flightResService.showAllFares();

                    String masPasajero = console.yesOrNo("Viajas con alguien más? (y/n): ");
                    if (masPasajero.equals("n")){
                        Passenger passenger = new Passenger();
                        String equipaje = console.yesOrNo("Llevas equipaje? (y/n): ");
                        int idTarifa;
                        if (equipaje.equals("y")){
                            mostrarTarifas(fares);
                            Fare fare = seleccionarTarifa();
                            idTarifa = fare.getId();
                        }else{
                            idTarifa = 1;
                        }
                        


                    }else{
                        int cantidadPasajeros = console.readInt("Cuantas personas van a viajar con usted? ");
                        for (int i = 1; i <= cantidadPasajeros; i++){

                        }
                    }
                    break;
                case 3:
                    System.out.println("\t3. Ver reservas por clientes");
                    break;
                case 4:
                    System.out.println("\t4. Cancelar reservación");
                    break;
                case 5:
                    break makeReservation;
            }
        }
    }

    public void mostrarViajes(){
        List<TripAirportDTO> airports = flightResService.showAllTrips();
        System.out.println("Listado de viajes:");
        CuadroDeTexto.drawHorizontal(130, "-");
        System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
        airports.forEach(airport -> {
            CuadroDeTexto.drawHorizontal(130, "-");
            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", airport.getId(), airport.getTripDate(), airport.getPriceTrip(), airport.getOrigin(), airport.getDestination());
        });
        CuadroDeTexto.drawHorizontal(130, "-");
        System.out.println();
    }

    public void mostrarClientes(){
        List<Customer> customerList = flightResService.showAllCustomers();
        CuadroDeTexto.drawHorizontal(120, "-");
        System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD");
        customerList.forEach(customer -> {
            CuadroDeTexto.drawHorizontal(120, "-");
            System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s |%n", customer.getId(), customer.getName(), customer.getLastName(), customer.getNroId(), customer.getAge());
        });
        CuadroDeTexto.drawHorizontal(120, "-");
        System.out.println();
    }

    public Customer seleccionarCliente(){
        return Helpers.transformAndValidateObj(
                () -> flightResService.showOneCustomer(console.readInt("Seleccione el cliente por su ID: "))
        );
    }

    public TripAirportDTO seleccionarVuelo(){
        return Helpers.transformAndValidateObj(
                () -> flightResService.showOneTrip(console.readInt("Seleccione el ID del vuelo que va a reservar: "))
        );
    }

    public int generarReserva(int idViaje){
        FlightRes flightRes = new FlightRes();
        flightRes.setIdTrip(idViaje);
        int idReservacion = flightResService.createFlightBooking(flightRes);
        return idReservacion;
    }

    public void mostrarTarifas(List<Fare> fares){
        CuadroDeTexto.drawHorizontal(50, "-");
        System.out.printf("\n| %-4s | %-16s | %-25s | %-25s |%n", "ID", "Descripción", "Detalle", "Valor");
        fares.forEach(fare -> {
            CuadroDeTexto.drawHorizontal(50, "-");
            System.out.printf("\n| %-4s | %-16s | %-25s | %-25s |%n", fare.getId(), fare.getDescription(), fare.getDetails(), fare.getValue());
        });
        CuadroDeTexto.drawHorizontal(50, "-");
    }

    public Fare seleccionarTarifa(){
        return Helpers.transformAndValidateObj(
                () -> flightResService.showOneFare(console.readInt("Seleccione el ID de la tarifa: "))
        );
    }

}
