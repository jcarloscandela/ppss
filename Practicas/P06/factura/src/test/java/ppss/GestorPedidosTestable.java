package ppss;

public class GestorPedidosTestable extends GestorPedidos{
    Buscador buscador;
    @Override
    public Buscador getBuscador(){
            return buscador;

    }

    public void setBuscador(Buscador buscador){
        this.buscador = buscador;
    }
}
