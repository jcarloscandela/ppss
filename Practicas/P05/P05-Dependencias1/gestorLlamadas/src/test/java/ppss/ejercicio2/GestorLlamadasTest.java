package ppss.ejercicio2;

import org.junit.jupiter.api.Test;
import ppss.ejercicio1.TestableGestorLlamadas;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestorLlamadasTest {

    @Test
    public void c1CalculaConsumoTest() {
        GestorLlamadasTestable instance = new GestorLlamadasTestable();
        CalendarioSTUB c = new CalendarioSTUB(15);
        int minutos = 10;
        instance.setCalendario(c);
        instance.calculaConsumo(minutos);
        double resultadoEsperado = 208;

        double resultadoReal = instance.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    public void c2CalculaConsumoTest() {
        GestorLlamadasTestable instance = new GestorLlamadasTestable();
        CalendarioSTUB c = new CalendarioSTUB(22);
        int minutos = 10;
        instance.setCalendario(c);
        instance.calculaConsumo(minutos);
        double resultadoEsperado = 105;

        double resultadoReal = instance.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }

}