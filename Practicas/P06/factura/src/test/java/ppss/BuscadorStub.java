package ppss;

public class BuscadorStub extends Buscador {
    private int resultado;

    public BuscadorStub(int resultado){
        this.resultado = resultado;
    }

    @Override
    public int elemPendientes(Cliente cliente){
        return  resultado;
    }
}
