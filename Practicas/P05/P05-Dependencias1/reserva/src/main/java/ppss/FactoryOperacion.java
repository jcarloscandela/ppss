package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

public class FactoryOperacion {
    private static IOperacionBO operacion;

    public static IOperacionBO getOperacion(){
        if (operacion == null){
            return new Operacion();
        }else{
            return operacion;
        }
    }
    public static void setOperacion(IOperacionBO o){
        operacion = o;
    }
}
