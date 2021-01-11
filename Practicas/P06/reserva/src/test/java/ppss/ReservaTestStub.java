package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTestStub {


    private String[] db_isbns;
    private String[] db_socios;

    private Reserva reservaStub;
    private IOperacionBO iOperacionBOStub;
    private FactoriaBOs FactoriaBOsStub;
    private String login, password, socio;
    private String[] isbns;
    private Usuario usuario;
    private boolean accesoDB;


    private ReservaException resultadoEsperado;
    private ReservaException resultadoReal;

    @BeforeEach
    void setUp() {
        usuario = Usuario.BIBLIOTECARIO;
        db_isbns = new String[]{"22222", "33333"};
        db_socios = new String[]{"Pepe"};
        reservaStub = EasyMock.createMockBuilder(Reserva.class).addMockedMethods("compruebaPermisos", "getFactoriaBOs").createNiceMock();
        iOperacionBOStub = EasyMock.createNiceMock(IOperacionBO.class);
        FactoriaBOsStub = EasyMock.createNiceMock(FactoriaBOs.class);

    }

    @Test
    public void c1ReservaTest() throws Exception {
        login = "xxxx";
        password = "xxxx";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        accesoDB = true;

        resultadoEsperado = new ReservaException("ERROR de permisos; ");

        EasyMock.expect(reservaStub.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyObject())).andStubReturn(false);
        EasyMock.replay(reservaStub);

        try {
            reservaStub.realizaReserva(EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyString(), EasyMock.anyObject());
        } catch (ReservaException ex) {
            resultadoReal = ex;
            Assertions.assertEquals(resultadoEsperado.getMessage(), resultadoReal.getMessage());
        }

    }



    @Test
    public void c2ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222", "33333"};
        accesoDB = true;

        resultadoEsperado = null;

        iOperacionBOStub.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
        EasyMock.expectLastCall().asStub();
        EasyMock.replay(iOperacionBOStub);

        EasyMock.expect(FactoriaBOsStub.getOperacionBO()).andStubReturn(iOperacionBOStub);
        EasyMock.replay(FactoriaBOsStub)
        ;
        EasyMock.expect(reservaStub.getFactoriaBOs()).andStubReturn(FactoriaBOsStub);
        EasyMock.expect(reservaStub.compruebaPermisos(EasyMock.anyString(), EasyMock.anyString(),  EasyMock.anyObject())).andStubReturn(true);
        EasyMock.replay(reservaStub);

        reservaStub.realizaReserva(login,password,socio,isbns);
    }


    @Test
    public void c3ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"11111"};
        accesoDB = true;

        String resultadoEsperadoString = "ISBN invalido:11111; ";

        iOperacionBOStub.operacionReserva(EasyMock.anyString(), EasyMock.anyString());
        EasyMock.expectLastCall().andStubThrow(new IsbnInvalidoException());
        EasyMock.replay(iOperacionBOStub);

        EasyMock.expect(FactoriaBOsStub.getOperacionBO()).andStubReturn(iOperacionBOStub);
        EasyMock.replay(FactoriaBOsStub)
        ;
        EasyMock.expect(reservaStub.getFactoriaBOs()).andReturn(FactoriaBOsStub);
        EasyMock.expect(reservaStub.compruebaPermisos(login, password, usuario)).andStubReturn(true);
        EasyMock.replay(reservaStub);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaStub.realizaReserva(login, password, socio, isbns));

        assertEquals(resultadoEsperadoString, excepcionEsperada.getMessage());
    }


    @Test
    public void c4ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        accesoDB = true;

        String resultadoEsperadoString = "SOCIO invalido; ";

        iOperacionBOStub.operacionReserva(socio, isbns[0]);
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());
        EasyMock.replay(iOperacionBOStub);

        EasyMock.expect(FactoriaBOsStub.getOperacionBO()).andReturn(iOperacionBOStub);
        EasyMock.replay(FactoriaBOsStub)
        ;
        EasyMock.expect(reservaStub.getFactoriaBOs()).andReturn(FactoriaBOsStub);
        EasyMock.expect(reservaStub.compruebaPermisos(login, password, usuario)).andReturn(true);
        EasyMock.replay(reservaStub);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaStub.realizaReserva(login, password, socio, isbns));

        assertEquals(resultadoEsperadoString, excepcionEsperada.getMessage());
    }

    @Test
    public void c5ReservaTest() throws Exception {
        login = "ppss";
        password = "ppss";
        socio = "Pepe";
        isbns = new String[]{"22222"};
        accesoDB = false;

        String resultadoEsperadoString = "CONEXION invalida; ";

        iOperacionBOStub.operacionReserva(socio, isbns[0]);
        EasyMock.expectLastCall().andThrow(new JDBCException());
        EasyMock.replay(iOperacionBOStub);

        EasyMock.expect(FactoriaBOsStub.getOperacionBO()).andReturn(iOperacionBOStub);
        EasyMock.replay(FactoriaBOsStub)
        ;
        EasyMock.expect(reservaStub.getFactoriaBOs()).andReturn(FactoriaBOsStub);
        EasyMock.expect(reservaStub.compruebaPermisos(login, password, usuario)).andReturn(true);
        EasyMock.replay(reservaStub);

        ReservaException excepcionEsperada = assertThrows(ReservaException.class, () -> reservaStub.realizaReserva(login, password, socio, isbns));

        assertEquals(resultadoEsperadoString, excepcionEsperada.getMessage());

    }

}