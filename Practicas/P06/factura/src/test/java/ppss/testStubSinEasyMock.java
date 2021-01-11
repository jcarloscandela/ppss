package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.FacturaException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testStubSinEasyMock {

        int numElements;
        Cliente cliente;
        String idCliente;
        float precioCliente;
        Factura resultadoEsperado, resultadoReal;
        GestorPedidosTestable gp;

        BuscadorStub stubBuscador;

        @BeforeEach
        public void setUp(){
            gp = new GestorPedidosTestable();
        }

        @Test
        public void c1testFactura() throws FacturaException {
            idCliente = "cliente1";
            precioCliente = 20.0f;
            numElements = 10;
            resultadoEsperado = new Factura("cliente1");
            resultadoEsperado.setTotal_factura(200.0f);
            cliente = new Cliente(idCliente,precioCliente);

            stubBuscador = new BuscadorStub(numElements);
            gp.setBuscador(stubBuscador);

            resultadoReal = gp.generarFactura(cliente);
            assertEquals(resultadoEsperado, resultadoReal);
        }

        @Test
        public void c2testFactura() throws FacturaException {
            idCliente = "cliente1";
            precioCliente = 20.0f;
            numElements = 0;
            cliente = new Cliente(idCliente,precioCliente);

            stubBuscador = new BuscadorStub(numElements);
            gp.setBuscador(stubBuscador);

            assertThrows(FacturaException.class, () ->  gp.generarFactura(cliente));
        }


}
