package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    private Calendario calendarioMock;
    private  GestorLlamadas testObject;
    
    @BeforeEach
    void setUp(){
        testObject = EasyMock.partialMockBuilder(GestorLlamadas.class)
                .addMockedMethod("getCalendario")
                .createMock();
        calendarioMock = EasyMock.createMock(Calendario.class);
    }

    @Test
    public void c1GestorLlamadasTest (){

        int minutos = 22;
        int hora = 10;

        calendarioMock = EasyMock.createMock(Calendario.class);
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(hora);
        EasyMock.replay(calendarioMock);
        EasyMock.expect(testObject.getCalendario()).andReturn(calendarioMock);
        EasyMock.replay(testObject);
        double real = testObject.calculaConsumo(minutos);
        double expected = 457.6;
        EasyMock.verify(testObject, calendarioMock);
        assertEquals(expected, real);

    }

    @Test
    public void c2GestorLlamadasTest (){
        int minutos = 13;
        int hora = 21;

        calendarioMock = EasyMock.createMock(Calendario.class);
        EasyMock.expect(calendarioMock.getHoraActual()).andReturn(hora);
        EasyMock.replay(calendarioMock);
        EasyMock.expect(testObject.getCalendario()).andReturn(calendarioMock);
        EasyMock.replay(testObject);
        double real = testObject.calculaConsumo(minutos);
        double expected = 136.5;
        EasyMock.verify(testObject, calendarioMock);
        assertEquals(expected, real);

    }

}