package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReservaTest {

    private ReservaStub sut = new ReservaStub();
    private String login;
    private String password;
    private String socio;
    private String[] isbns;
    private Boolean accesoDB;
    private Boolean permisos;
    private OperacionStub operacionStub;
    private String[] sociosDB;
    private String[] isbnsDB;

    @BeforeEach
    void ReservaTest(){
        sociosDB = new String[]{"Luis"};
        isbnsDB = new String[]{"11111","22222"};
        sut = new ReservaStub();
        operacionStub = new OperacionStub();
        operacionStub.setListaSocios(new ArrayList<>(Arrays.asList(sociosDB)));
        operacionStub.setListaIsbns(new ArrayList<>(Arrays.asList(isbnsDB)));
    }

    @Test
    public void c1ReservaTest() throws Exception {
        login = "xxxx";
        password = "xxxx";
        socio = "Luis";
        isbns = new String[]{"11111"};
        accesoDB = true;
        permisos = false;
        ReservaException resultadoEsperado = new ReservaException("ERROR de permisos; ");
        operacionStub.setAccesoDB(accesoDB);
        sut.setPermisos(permisos);
        FactoryOperacion.setOperacion(operacionStub);

        try{
            sut.realizaReserva(login,password,socio,isbns);
        } catch (ReservaException e){
            assertEquals(resultadoEsperado.getMessage(), e.getMessage());
        }catch (Exception e){
            fail();
        }

    }

    @Test
    public void c2ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[]{"11111","22222"};
        accesoDB = true;
        permisos = true;
        ReservaException resultadoEsperado = new ReservaException("");
        operacionStub.setAccesoDB(accesoDB);
        sut.setPermisos(permisos);
        FactoryOperacion.setOperacion(operacionStub);

        try{
            sut.realizaReserva(login,password,socio,isbns);
        } catch (ReservaException e){
            fail();

        }catch (Exception e){
            fail();
        }

    }

    @Test
    public void c3ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Luis";
        isbns = new String[]{"33333"};
        accesoDB = true;
        permisos = true;
        ReservaException resultadoEsperado = new ReservaException("ISBN invalido:33333; ");
        operacionStub.setAccesoDB(accesoDB);
        sut.setPermisos(permisos);
        FactoryOperacion.setOperacion(operacionStub);

        try{
            sut.realizaReserva(login,password,socio,isbns);
            fail();
        } catch (ReservaException e){
            assertEquals(resultadoEsperado.getMessage(), e.getMessage());
        }catch (Exception e){
            fail();
        }

    }

    @Test
    public void c4ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111"};
        accesoDB = true;
        permisos = true;
        ReservaException resultadoEsperado = new ReservaException("SOCIO invalido; ");
        operacionStub.setAccesoDB(accesoDB);
        sut.setPermisos(permisos);
        FactoryOperacion.setOperacion(operacionStub);

        try{
            sut.realizaReserva(login,password,socio,isbns);
            fail();
        } catch (ReservaException e){
            assertEquals(resultadoEsperado.getMessage(), e.getMessage());
        }catch (Exception e){
            fail();
        }

    }

    @Test
    public void c5ReservaTest() throws Exception {
        login = "xxxx";
        password = "xxxx";
        socio = "Luis";
        isbns = new String[]{"11111"};
        accesoDB = false;
        permisos = true;
        ReservaException resultadoEsperado = new ReservaException("CONEXION invalida; ");
        operacionStub.setAccesoDB(accesoDB);
        sut.setPermisos(permisos);
        FactoryOperacion.setOperacion(operacionStub);

        try{
            sut.realizaReserva(login,password,socio,isbns);
            fail();
        } catch (ReservaException e){
            assertEquals(resultadoEsperado.getMessage(), e.getMessage());
        }catch (Exception e){
            fail();
        }

    }

}