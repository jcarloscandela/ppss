package ppss.ejercicio1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GestorLlamadasTest {

    @Test
    public void c1CalculaConsumoTest() {
        TestableGestorLlamadas instance = new TestableGestorLlamadas();
        int minutos = 10;
        instance.setHora(15);
        double resultadoEsperado = 208;

        double resultadoReal = instance.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void c2CalculaConsumoTest() {
        TestableGestorLlamadas instance = new TestableGestorLlamadas();
        int minutos = 10;
        instance.setHora(22);
        double resultadoEsperado = 105;

        double resultadoReal = instance.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }
}