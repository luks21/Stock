package stock.Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import stock.Entidades.Producto;
import stock.Servicios.ProductoJpaController;

public class ProdServ {

public void Menu() throws Exception
{
    Scanner ingreso = new Scanner(System.in);
    String resp;
    int cant;
    String filtro;
    ProductoJpaController service = new ProductoJpaController();
    boolean salir = false;
    System.out.println("");
    System.out.println("");
    System.out.println("     BIENVENIDO!!");
    do{
        System.out.println("1)CREAR UN NUEVO PRODUCTO");
        System.out.println("2)CARGAR PRODUCTO AL STOCK");
        System.out.println("3)SACAR UN PRODUCTO DEL STOCK");
          System.out.println("4)LISTAR PRODUCTOS CON EXISTENCIA MENOR O IGUAL A:");
    System.out.println("5)LISTAR PRODUCTOS DE NOMBRE..:");
    System.out.println("6)ELIMINAR PRODUCTO");
    System.out.println("7)MODIFICAR PRECIO");
    System.out.println("8)MONTO STOCK");
    System.out.println("9)LISTAR TODOS LOS PRODUCTOS");
    System.out.println("0)SALIR");
    
    resp = ingreso.next();
    if (validar(resp)) {
    switch (resp) {
        case "1":
            crearProd();
            break;
        case "2":
            cargarProd();
            break;
        case "3":
            sacarProd();
            break;
        case "4":
            System.out.println("INGRESE LA CANTIDAD MINIMA A LISTAR");
            cant = ingreso.nextInt();
            service.listarFiltroCant(cant);
            for (Producto p : service.listarFiltroCant(ingreso.nextInt())) {
                System.out.println(p.getId()+")"+p.getNombre()+"/"+p.getCantidad()+" $"+p.getPrecio());
                System.out.println("");            }
            break;
        case "5":
            System.out.println("INGRESE EL PRODUCTO A BUSCAR");
            filtro = ingreso.next();
            System.out.println("");
            for (Producto p : service.listarFiltro("%"+filtro+"%")) {
                System.out.println(p.getId()+")"+p.getNombre()+"/"+p.getCantidad()+" $"+p.getPrecio());
                System.out.println("");
            }
            System.out.println("");
            break;
        case "6":
            System.out.println("INGRESE EL PRODUCTO A BUSCAR PARA SER ELIMINADO!!!");
            filtro = ingreso.next();
            System.out.println("");
            for (Producto p : service.listarFiltro("%"+filtro+"%")) {
                System.out.println(p.getId()+")"+p.getNombre()+"/"+p.getCantidad()+" $"+p.getPrecio());
                System.out.println("");
            }
            System.out.println("");
            System.out.println("no terminado");
            break;
        case "7":
            modifPrecio();
            break;
        case "8":
            calcStock();
            break;
        case "9":
            for (Producto p : service.EncontProductos()) {
                System.out.println(p.getId()+")"+p.getNombre()+"/"+p.getCantidad()+"/$"+p.getPrecio()+"/"+p.getProveedor());
                System.out.println("");
            }
            break;
        case "0":
            System.out.println("GRACIAS!!");
            salir=true;
            break;
        default:
            throw new AssertionError();
    }
    }
    }while(!salir);
}


public void crearProd(){
    Scanner ingreso = new Scanner(System.in);
    ProductoJpaController prodServ = new ProductoJpaController();
    DateServ dateServ = new DateServ();
    Producto producto = new Producto();
    System.out.println("INGRESE EL NOMBRE DEL PRODUCTO");
    producto.setNombre(ingreso.nextLine().toUpperCase());
    producto.setPrecio(0);
    producto.setFecCompra(null);
    producto.setFecSalida(null);
    producto.setCantidad(0);
    producto.setTamPaq(0);
    prodServ.create(producto);
}


public void cargarProd() throws Exception{
    DateServ dateServ = new DateServ();
    Scanner ingreso = new Scanner(System.in);
    //System.out.println("INGRESE EL NOMBRE DEL PRODUCTO");
    //String nombre = ingreso.nextLine();
    ProductoJpaController service = new ProductoJpaController();
    for (Producto p : service.EncontProductos()) {
    System.out.println(p.getId()+")"+p.getNombre()+" = "+p.getCantidad());
    }
    System.out.println("INGRESE EL ID DEL PRODUCTO A CARGAR");
    int id = ingreso.nextInt();
    Producto p = service.EncontProductos(id);
    p.setFecCompra(dateServ.crearfecha());
    System.out.println("INGRESE CANTIDAD");
    p.setCantidad(ingreso.nextInt()+p.getCantidad());
    double precComp = p.getPrecio();
    System.out.println("INGRESE TAMAÑO DEL EMPAQUE");
    p.setTamPaq(ingreso.nextDouble());
    System.out.println("INGRESE PRECIO X KG O UNIDAD");
    double precNuevo = ingreso.nextDouble();
    if (precNuevo>precComp) {
        p.setPrecio(precNuevo);
    }else{
        System.out.println("EL NUEVO PRECIO ES MENOR");
        System.out.println("¿DESEA CAMBIARLO?");
        System.out.println("S/N");
        String resp = ingreso.next().substring(0, 1).toUpperCase();
        switch (resp) {
            case "S":
                p.setPrecio(precNuevo);
                service.edit(p);
                break;
            case "N":
                
                break;
            default:
                throw new AssertionError();
        }
    }
    System.out.println("INGRESE EL PROVEEDOR");
    p.setProveedor(ingreso.next().toUpperCase());
    service.edit(p);
}

public void sacarProd() throws Exception{
    DateServ dateServ = new DateServ();
    Scanner ingreso = new Scanner(System.in);
    //System.out.println("INGRESE EL NOMBRE DEL PRODUCTO");
    //String nombre = ingreso.nextLine();
    ProductoJpaController service = new ProductoJpaController();
    for (Producto p : service.EncontProductos()) {
    System.out.println(p.getId()+")"+p.getNombre()+" = "+p.getCantidad());
    }
    System.out.println("INGRESE EL ID DEL PRODUCTO A SACAR");
    int id = ingreso.nextInt();
    Producto p = service.EncontProductos(id);
    if (p.getCantidad()!=0) {
    System.out.println("INGRESE CANTIDAD");
    p.setCantidad(p.getCantidad()-ingreso.nextInt());
    p.setFecSalida(dateServ.fechaSalida());
    service.edit(p);
    }else{
        System.out.println("NO ALCANZA EL STOCK");
        System.out.println("");
    }
    
    }

public void modifPrecio() throws Exception{
    DateServ dateServ = new DateServ();
    Scanner ingreso = new Scanner(System.in);
    //System.out.println("INGRESE EL NOMBRE DEL PRODUCTO");
    //String nombre = ingreso.nextLine();
    ProductoJpaController service = new ProductoJpaController();
    for (Producto p : service.EncontProductos()) {
    System.out.println(p.getId()+")"+p.getNombre()+" = "+p.getCantidad());
    }
    System.out.println("INGRESE EL ID DEL PRODUCTO A MODIFICAR SU PRECIO");
    int id = ingreso.nextInt();
    Producto p = service.EncontProductos(id);
    System.out.println("INGRESE PRECIO");
    int precio = ingreso.nextInt();
    if (precio>p.getPrecio()) {
    p.setPrecio(ingreso.nextInt());
    service.edit(p);
    }else{
        System.out.println("EL NUEVO PRECIO ES MENOR");
        System.out.println("¿DESEA CAMBIARLO?");
        System.out.println("S/N");
        String resp = ingreso.next().substring(0, 1).toUpperCase();
        switch (resp) {
            case "S":
                p.setPrecio(precio);
                service.edit(p);
                break;
            default:
                throw new AssertionError();
        }
    }
    }
    
public void listarExist(int cant){
    ProductoJpaController service = new ProductoJpaController();
    for (Producto p : service.EncontProductos()) {
     if (cant>=p.getCantidad()) {
        System.out.println(p.getId()+")"+p.getNombre()+" = "+p.getCantidad());
    }
}
}
public void calcStock(){
    ProductoJpaController service = new ProductoJpaController();
    int suma = 0;
    for (Producto p : service.EncontProductos()) {
        suma = suma +((p.getCantidad()*((int)p.getTamPaq()))*((int)p.getPrecio()));
    }
    System.out.println("$"+suma);
}
    public static void limpiarPantalla() {
        try {
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando= new ArrayList<>();
            if(sistemaOperativo.contains("Windows")){        
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");
            } else {
                comando.add("clear");
            } 
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla"+e.getMessage());
        }
}
//public void listarFiltrado(String filtro){
//    ProductoJpaController service = new ProductoJpaController();
//    //for (Producto p : (service.EncontProductos()= em.)) {
//     if (p.getNombre().) {
//        System.out.println(p.getId()+")"+p.getNombre()+" = "+p.getCantidad()+" $"+p.getPrecio());
//    }
    public boolean validar(String text) {
    int num;
    try {
      num=Integer.parseInt(text);
      return true;
    } catch (NumberFormatException ex) {
       return false;
    }
}
}


