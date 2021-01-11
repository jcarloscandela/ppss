package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.FacturaException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class testStubEasyMock {

    int numElements;
    Cliente cliente;
    String idCliente;
    float precioCliente;
    Factura resultadoEsperado, resultadoReal;
    GestorPedidos gp, stubGP;

    Buscador mockBuscador;

    @BeforeEach
    public void setUp(){
        gp = new GestorPedidos();
        mockBuscador = EasyMock.createNiceMock(Buscador.class);
        stubGP = EasyMock.partialMockBuilder(GestorPedidos.class).addMockedMethod("getBuscador").createNiceMock();
    }
    @Test
    public void c1testFactura() throws FacturaException {
        idCliente = "cliente1";
        precioCliente = 20.0f;
        numElements = 10;
        resultadoEsperado = new Factura("cliente1");
        resultadoEsperado.setTotal_factura(200.0f);
        cliente = new Cliente(idCliente,precioCliente);
        EasyMock.expect(mockBuscador.elemPendientes(anyObject())).andStubReturn(numElements);
        EasyMock.replay(mockBuscador);
        EasyMock.expect(stubGP.getBuscador()).andStubReturn(mockBuscador);
        EasyMock.replay(stubGP);

        resultadoReal = stubGP.generarFactura(cliente);

        assertEquals(resultadoEsperado, resultadoReal);

    }

    @Test
    public void c2testFactura() throws FacturaException {
        idCliente = "cliente1";
        precioCliente = 20.0f;
        numElements = 0;
        FacturaException exceptionEsperada = new FacturaException("No hay nada pendiente de facturar");
        cliente = new Cliente(idCliente,precioCliente);
        EasyMock.expect(mockBuscador.elemPendientes(cliente)).andStubReturn(numElements);
        EasyMock.replay(mockBuscador);
        EasyMock.expect(stubGP.getBuscador()).andStubReturn(mockBuscador);
        EasyMock.replay(stubGP);

        assertThrows(FacturaException.class, () -> stubGP.generarFactura(cliente));

    }
}
