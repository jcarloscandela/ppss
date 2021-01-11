package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.FacturaException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

public class testMockEasyMock {

    int numElements;
    Cliente cliente;
    String idCliente;
    float precioCliente;
    Factura resultadoEsperado, resultadoReal;
    GestorPedidos mockGP;

    Buscador mockBuscador;

    @BeforeEach
    public void setUp(){
    mockBuscador = EasyMock.createMock(Buscador.class);
    mockGP = EasyMock.partialMockBuilder(GestorPedidos.class).addMockedMethod("getBuscador").createMock();
    }
    @Test
    public void c1testFactura() throws FacturaException {
        idCliente = "cliente1";
        precioCliente = 20.0f;
        numElements = 10;
        resultadoEsperado = new Factura("cliente1");
        resultadoEsperado.setTotal_factura(200.0f);
        cliente = new Cliente(idCliente,precioCliente);
        EasyMock.expect(mockBuscador.elemPendientes(cliente)).andReturn(numElements);
        EasyMock.replay(mockBuscador);
        EasyMock.expect(mockGP.getBuscador()).andReturn(mockBuscador);
        EasyMock.replay(mockGP);

        resultadoReal = mockGP.generarFactura(cliente);

        assertEquals(resultadoEsperado, resultadoReal);
        EasyMock.verify(mockBuscador,mockGP);
    }

    @Test
    public void c2testFactura() throws FacturaException {
        idCliente = "cliente1";
        precioCliente = 20.0f;
        numElements = 0;
        FacturaException exceptionEsperada = new FacturaException("No hay nada pendiente de facturar");
        cliente = new Cliente(idCliente,precioCliente);
        EasyMock.expect(mockBuscador.elemPendientes(cliente)).andReturn(numElements);
        EasyMock.replay(mockBuscador);
        EasyMock.expect(mockGP.getBuscador()).andReturn(mockBuscador);
        EasyMock.replay(mockGP);

       assertThrows(FacturaException.class, () -> mockGP.generarFactura(cliente));

       EasyMock.verify(mockBuscador, mockGP);
    }
}
