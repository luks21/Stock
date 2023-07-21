package stock.Entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-11T21:10:17")
@StaticMetamodel(Movimientos.class)
public class Movimientos_ { 

    public static volatile SingularAttribute<Movimientos, Date> fecha;
    public static volatile SingularAttribute<Movimientos, Integer> id;
    public static volatile SingularAttribute<Movimientos, Integer> cantidad;
    public static volatile SingularAttribute<Movimientos, String> nombre;

}