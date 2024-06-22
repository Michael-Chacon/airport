package org.vuelosGlobales.shared;

import java.util.Optional;
import java.util.function.Supplier;

public class Helpers {

    public static <T> T transformAndValidateObj(Supplier<Optional<T>> supplier){
        T objetoSeleccionado = null;
        while (true) {
            Optional<T> objetoOpcional = supplier.get();
            if (objetoOpcional.isPresent()) {
                System.out.println();
                return objetoSeleccionado = objetoOpcional.get();
            } else {
                System.out.println("El id no existe");
            }
        }
    }
}
