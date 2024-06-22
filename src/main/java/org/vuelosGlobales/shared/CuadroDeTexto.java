package org.vuelosGlobales.shared;

public class CuadroDeTexto {
    public static void dibujarCuadroDeTexto(String message, String tipe){
        if (message == null){
            message = "Información registrada con éxito";
        }

        if (tipe == null){
            tipe = "═";
        }

        int widthMessage = message.length();
        int widthHorizontal = widthMessage + 4;

        drawHorizontal(widthHorizontal, tipe);
        System.out.println("\n║ " + message + " ║");
        drawHorizontal(widthHorizontal, tipe);
        System.out.println();
    }

    public static void drawHorizontal(int width, String tipe){
        for (int i = 0; i < width; i++){
            System.out.print(tipe);
        }
    }
}
