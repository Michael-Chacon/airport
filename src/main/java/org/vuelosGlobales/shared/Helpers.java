package org.vuelosGlobales.shared;

import java.util.Optional;
import java.util.function.Supplier;

public class Helpers {

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
   public static <T> String validateExist(String message, Validator<T> validator){
    Console console = new Console();
    while (true){
        String input = console.stringWithLeght(message, 5);
        Optional<T> validationResult = validator.validate(input.toUpperCase());
        if (validationResult.isPresent()){
            System.out.println("El id ingresado ya existe");
        }else{
            System.out.println("Id valido");
            return input;
        }
    }
   }
}
