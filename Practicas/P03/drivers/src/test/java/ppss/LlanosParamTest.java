package ppss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

@Tag("Parametrizado")
@Tag("LlanosParamTest")
class LlanosParamTest {

    Tramo resultadoEsperado, resultadoReal;
    ArrayList<Integer> lecturas;
    Llanos llanos;

    @BeforeEach
    void initialize() {
        resultadoEsperado = new Tramo();
        resultadoReal = new Tramo();
        lecturas = new ArrayList<Integer>();
        llanos = new Llanos();
    }

    @ParameterizedTest(name = "Lecturas {0}, Tramo {1}")

    @MethodSource("casosDePrueba")
    void testParametrizado(Integer[] lecturas, Tramo resultadoEsperado) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for(int i: lecturas){
            intList.add(i);
        }
        resultadoReal = llanos.buscarTramoLlanoMasLargo(intList);
        assertEquals(resultadoEsperado, resultadoReal, () -> generateFailureMessage(resultadoEsperado,resultadoReal));
    }

    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(new Integer[]{3}, new Tramo(0,0)),
                Arguments.of(new Integer[]{100,100,100,100}, new Tramo(0,3)),
                Arguments.of(new Integer[]{120,140,180,180,180}, new Tramo(2,2)),
                Arguments.of(new Integer[]{-1}, new Tramo(0,0)),
                Arguments.of(new Integer[]{-1,-1,-1,-1}, new Tramo(0,3)),
                Arguments.of(new Integer[]{120,140,-10,-10,-10}, new Tramo(2,2))

        );
    }

    private String generateFailureMessage(Tramo resultadoEsperado, Tramo resultadoReal) {
        String message = "";
        message = message + "El resultado esperado era: " + resultadoEsperado.toString() + " y el resultado ha sido:" + resultadoReal.toString();
        return message;
    }
}