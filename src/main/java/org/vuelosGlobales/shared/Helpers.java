package org.vuelosGlobales.shared;

import java.util.Optional;
import java.util.function.Supplier;

public class Helpers {

    /*Método para convertir un Optional a la clase que yo necesito por que
    todos los métodos de los adaptadores de salida  findById(id) devuelven un Optional
    ejm: si quiero buscar un pais por id, findById(id) devuelve un Optional<Pais>, pero el método transformAndValidateObj
    convierte ese Optional<Pais> a un Pais directamente. ESTE MÉTODO también valida que un id que estoy buscando exista en la base de datos, si no existe vuelve y lo pide
    **/

    public static <T> T transformAndValidateObj(Supplier<Optional<T>> supplier){
        T objetoSeleccionado = null;
        while (true) {
            Optional<T> objetoOpcional = supplier.get();
            if (objetoOpcional.isPresent()) {
                return objetoSeleccionado = objetoOpcional.get();
            } else {
                System.out.println("El id no existe");
            }
        }
    }

    /*
    Este método valida que el id que estamos ingresando sea único, no no existe en la db, si existe va seguir pidiendo hasta que ingrese un id único.
    * */
   public static <T> String validateExist(String message, Validator<T> validator){
    Console console = new Console();
    while (true){
        String input = console.stringWithLeght(message, 5);
        Optional<T> validationResult = validator.validate(input.toUpperCase());
        if (validationResult.isPresent()){
            System.out.println("El id ingresado ya existe");
        }else{
            return input;
        }
    }
   }
}
