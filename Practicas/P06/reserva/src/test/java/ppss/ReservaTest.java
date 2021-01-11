package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.partialMockBuilder;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {


    private String[] db_isbns;
    private String[] db_socios;

    private Reserva reservaMock;
    private IOperacionBO iOperacionBOMock;
    private FactoriaBOs FactoriaBOsMock;
    private String login, password, socio;
    private String[] isbns;
    private Usuario usuario;
    private boolean accesoDB;


    private ReservaException resultadoEsperado;
    private ReservaException resultadoReal;

    @BeforeEach
    void setUp(){
        usuario = Usuario.BIBLIOTECARIO;
        db_isbns = new String[] {"22222","33333"};
        db_socios = new String[] {"Pepe"};
        reservaMock = partialMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos","getFactoriaBOs").createMock();
        iOperacionBOMock = EasyMock.createMock(IOperacionBO.class);
        FactoriaBOsMock = EasyMock.createMock(FactoriaBOs.class);

    }

    @Test
    public void c1ReservaTest () throws Exception {
        login ="xxxx";
        password = "xxxx";
        socio ="Pepe";
        isbns = new String[]{"22222"};
        accesoDB = true;

        resultadoEsperado = new ReservaException("ERROR de permisos; ");

        EasyMock.expect(reservaMock.compruebaPermisos(login,password,usuario)).andReturn(false);
        EasyMock.replay(reservaMock);

        try {
            reservaMock.realizaReserva(login, password, socio, isbns);
        }
        catch(ReservaException ex){
            resultadoReal = ex;
            Assertions.assertEquals(resultadoEsperado.getMessage(),resultadoReal.getMessage());
        }

        EasyMock.verify(reservaMock);
    }

    @Test
    public void c2ReservaTest () throws Exception {
        login ="ppss";
        password = "ppss";
        socio ="Pepe";
        isbns = new String[]{"22222", "33333"};
        accesoDB = true;

        resultadoEsperado = null;

        iOperacionBOMock.operacionReserva(socio,isbns[0]);
        iOperacionBOMock.operacionReserva(socio,isbns[1]);
        EasyMock.replay(iOperacionBOMock);

        EasyMock.expect(FactoriaBOsMock.getOperacionBO()).andReturn(iOperacionBOMock);
        EasyMock.replay(FactoriaBOsMock)
        ;
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(FactoriaBOsMock);
        EasyMock.expect(reservaMock.compruebaPermisos(login,password,usuario)).andReturn(true);
        EasyMock.replay(reservaMock);

        reservaMock.realizaReserva(login,password,socio,isbns);

        EasyMock.verify(reservaMock, FactoriaBOsMock, iOperacionBOMock);
    }


    @Test
    public void c3ReservaTest () throws Exception {
        login ="ppss";
        password = "ppss";
        socio ="Pepe";
        isbns = new String[]{"11111"};
        accesoDB = true;

        String resultadoEsperadoString = "ISBN invalido:11111; ";

        iOperacionBOMock.operacionReserva(socio,isbns[0]);
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        EasyMock.replay(iOperacionBOMock);

        EasyMock.expect(FactoriaBOsMock.getOperacionBO()).andReturn(iOperacionBOMock);
        EasyMock.replay(FactoriaBOsMock)
        ;
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(FactoriaBOsMock);
        EasyMock.expect(reservaMock.compruebaPermisos(login,password,usuario)).andReturn(true);
        EasyMock.replay(reservaMock);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(login,password,socio,isbns));

        assertEquals(resultadoEsperadoString , excepcionEsperada.getMessage());
        EasyMock.verify(reservaMock, FactoriaBOsMock, iOperacionBOMock);
    }

    @Test
    public void c4ReservaTest () throws Exception {
        login ="ppss";
        password = "ppss";
        socio ="Pepe";
        isbns = new String[]{"22222"};
        accesoDB = true;

        String resultadoEsperadoString = "SOCIO invalido; ";

        iOperacionBOMock.operacionReserva(socio,isbns[0]);
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());
        EasyMock.replay(iOperacionBOMock);

        EasyMock.expect(FactoriaBOsMock.getOperacionBO()).andReturn(iOperacionBOMock);
        EasyMock.replay(FactoriaBOsMock)
        ;
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(FactoriaBOsMock);
        EasyMock.expect(reservaMock.compruebaPermisos(login,password,usuario)).andReturn(true);
        EasyMock.replay(reservaMock);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(login,password,socio,isbns));

        assertEquals(resultadoEsperadoString , excepcionEsperada.getMessage());
        EasyMock.verify(reservaMock, FactoriaBOsMock, iOperacionBOMock);
    }

    @Test
    public void c5ReservaTest () throws Exception {
        login ="ppss";
        password = "ppss";
        socio ="Pepe";
        isbns = new String[]{"22222"};
        accesoDB = false;

        String resultadoEsperadoString = "CONEXION invalida; ";

        iOperacionBOMock.operacionReserva(socio,isbns[0]);
        EasyMock.expectLastCall().andThrow(new JDBCException());
        EasyMock.replay(iOperacionBOMock);

        EasyMock.expect(FactoriaBOsMock.getOperacionBO()).andReturn(iOperacionBOMock);
        EasyMock.replay(FactoriaBOsMock)
        ;
        EasyMock.expect(reservaMock.getFactoriaBOs()).andReturn(FactoriaBOsMock);
        EasyMock.expect(reservaMock.compruebaPermisos(login,password,usuario)).andReturn(true);
        EasyMock.replay(reservaMock);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaMock.realizaReserva(login,password,socio,isbns));

        assertEquals(resultadoEsperadoString , excepcionEsperada.getMessage());
        EasyMock.verify(reservaMock, FactoriaBOsMock, iOperacionBOMock);
    }

}