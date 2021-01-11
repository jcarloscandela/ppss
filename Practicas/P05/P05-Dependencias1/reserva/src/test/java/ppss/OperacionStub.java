package ppss;

import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.SocioInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class OperacionStub extends Operacion{
    private boolean accesoDB;
    private List<String> listaSocios;
    private List<String> listaIsbns;

    public OperacionStub(){
        listaSocios = new ArrayList<String>();
        listaIsbns = new ArrayList<String>();
        listaSocios.add("Luis");
        listaIsbns.add("11111");
        listaIsbns.add("22222");
        listaIsbns.add("33333");
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws JDBCException, SocioInvalidoException, IsbnInvalidoException {
        if(!accesoDB)
            throw new JDBCException();
        if(!listaSocios.contains(socio))
            throw new SocioInvalidoException();
        if(!listaIsbns.contains(isbn))
            throw new IsbnInvalidoException();
    }

    public void setAccesoDB(boolean accesoDB){
        this.accesoDB = accesoDB;
    }

    public void setListaSocios(List<String> listaSocios){
        this.listaSocios = listaSocios;
    }

    public void setListaIsbns(List<String> listaIsbns){
        this.listaIsbns = listaIsbns;
    }

}
