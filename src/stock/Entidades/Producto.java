package stock.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private double precio;
     private Date fecCompra=null;
    private Date fecSalida;
    private int cantidad;
    private String proveedor;
    private double tamPaq;
    
    public int getId() {
        return id;
    }

    public double getTamPaq() {
        return tamPaq;
    }

    public void setTamPaq(double tamPaq) {
        this.tamPaq = tamPaq;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stock.Entidades.Producto[ id=" + id + " ]";
    }

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, Date fecCompra, Date fecSalida, int cantidad, String proveedor, double tamPaq) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecCompra = fecCompra;
        this.fecSalida = fecSalida;
        this.cantidad = cantidad;
        this.proveedor = proveedor;    
        this.tamPaq = tamPaq;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecCompra() {
        return fecCompra;
    }

    public void setFecCompra(Date fecCompra) {
        this.fecCompra = fecCompra;
    }

    public Date getFecSalida() {
        return fecSalida;
    }

    public void setFecSalida(Date fecSalida) {
        this.fecSalida = fecSalida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

}
