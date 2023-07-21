package stock.Servicios;

import java.util.Date;
import java.util.Scanner;

public class DateServ {

public Date crearfecha(){
    boolean seguir = false;
    Date fecha = new Date();
    Scanner ingreso = new Scanner(System.in);
    System.out.println("FACTURA DE COMPRA");
    ProdServ service = new ProdServ();
    do{
    System.out.println("INGRESE DÍA");
    String dia= ingreso.next();
    System.out.println("INGRESE MES");
    String mes= ingreso.next();
    System.out.println("INGRESE AÑO");
    String anio= ingreso.next();
        if (service.validar(dia)&&service.validar(mes)&&service.validar(anio)) {
            fecha = new Date(Integer.parseInt(anio)-1900, Integer.parseInt(mes), Integer.parseInt(dia));
            seguir = true;
        }else{
            System.out.println("LAS FECHAS INGRESADAS NO CORRESPONDEN");
        }
        }while(!seguir);
return fecha;
    
    }
public Date fechaSalida(){
    boolean seguir = false;
    Date fecha = new Date();
    Scanner ingreso = new Scanner(System.in);
    System.out.println("FECHA DE SALIDA");
    ProdServ service = new ProdServ();
    do{
    System.out.println("INGRESE DÍA");
    String dia= ingreso.next();
    System.out.println("INGRESE MES");
    String mes= ingreso.next();
    System.out.println("INGRESE AÑO");
    String anio= ingreso.next();
        if (service.validar(dia)&&service.validar(mes)&&service.validar(anio)) {
            fecha = new Date(Integer.parseInt(anio)-1900, Integer.parseInt(mes), Integer.parseInt(dia));
            seguir = true;
        }else{
            System.out.println("LAS FECHAS INGRESADAS NO CORRESPONDEN");
        }
        }while(!seguir);
return fecha;
    
}
}
