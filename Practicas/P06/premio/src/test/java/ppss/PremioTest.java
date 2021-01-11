package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PremioTest {


    private Premio premioMock;
    private ClienteWebService clienteMock;

    private float numeroAleatorio;
    @BeforeEach
    void setUp(){
        premioMock = partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createMock();
        clienteMock = createMock(ClienteWebService.class);
    }

    @Test
    public void c1PremioTest () throws ClienteWebServiceException {

        numeroAleatorio = 0.07f;

        EasyMock.expect(clienteMock.obtenerPremio()).andReturn("entrada final Champions");
        EasyMock.replay(clienteMock);

        premioMock.cliente = clienteMock;

        EasyMock.expect(premioMock.generaNumero()).andReturn(numeroAleatorio);
        EasyMock.replay(premioMock);

        String real = premioMock.compruebaPremio();
        String expected = "Premiado con entrada final Champions";

        EasyMock.verify(premioMock, clienteMock);
        assertEquals(expected, real);
    }

    @Test
    public void c2PremioTest () throws ClienteWebServiceException {

        numeroAleatorio = 0.03f;
        ClienteWebServiceException e = new ClienteWebServiceException();

        EasyMock.expect(clienteMock.obtenerPremio()).andThrow(e);
        EasyMock.replay(clienteMock);

        premioMock.cliente = clienteMock;

        EasyMock.expect(premioMock.generaNumero()).andReturn(numeroAleatorio);
        EasyMock.replay(premioMock);

        String real = premioMock.compruebaPremio();
        String expected = "No se ha podido obtener el premio";

        EasyMock.verify(premioMock, clienteMock);
        assertEquals(expected, real);
    }

}