package org.vuelosGlobales.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public  class Console {
    private Scanner scanner;

    public Console(){
        this.scanner = new Scanner(System.in);
    }

    public int readInt(String mensaje){
        int valor;
        while (true){
            System.out.println(mensaje);
            try{
                valor =  Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
    }

    public String stringNotNull(String message){
        System.out.print(message);
        String value ;
        while (true){
            value = scanner.nextLine();
            if (value.isBlank()){
                System.out.print("Este es un campo obligatorio, " + message.toLowerCase());
            }else{
                break;
            }
        }
        return value;
    }

    public String stringWithLeght(String message, int lenght){
        String value;
        while (true){
            value = this.stringNotNull(message);
            if (value.length() > lenght){
                System.out.print("Error, ");
            }else {
                break;
            }
        }
        return value;
    }

    public String stringNull(String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    public String yesOrNo(String message){
        String option;
        while (true){
            option = this.stringNotNull(message);
            if (!option.equals("y") && !option.equals("n")){
                System.out.print("Ingresa una opción valida, ");
            }else {
                break;
            }
        }
        return option;
    }

    public Date validarFecha(String mensaje) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date fecha = null;

        while (fecha == null) {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            try {
                java.util.Date utilDate = dateFormat.parse(input);
                fecha = new Date(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Formato correcto: YYYY-MM-DD.");
            }
        }
        return fecha;
    }

}
