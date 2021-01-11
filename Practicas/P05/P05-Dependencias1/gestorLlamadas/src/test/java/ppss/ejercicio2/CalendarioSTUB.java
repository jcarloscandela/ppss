package ppss.ejercicio2;

public class CalendarioSTUB extends Calendario {
    int resultado;

    public CalendarioSTUB(int salida){
        this.resultado = salida;
    }

    @Override
    public int getHoraActual(){
        return resultado;
    }
}
