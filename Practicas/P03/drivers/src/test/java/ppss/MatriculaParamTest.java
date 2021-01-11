package ppss;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Parametrizado")
@Tag("MatriculaParamTest")
public class MatriculaParamTest {
    Matricula mat= new Matricula();

    @ParameterizedTest(name = "Edad: {0}, Familia numerosa: {1}, Repetidor: {2} - Resultado esperado: {3}")

    @MethodSource("casosDePrueba")
    void testParametrizado(int edad, boolean familiaNumerosa, boolean repetidor, float resultadoEsperado) {
        float resultadoReal = mat.calculaTasaMatricula(edad,familiaNumerosa,repetidor);
        assertEquals(resultadoEsperado,resultadoReal, () -> generateFailureMessage(resultadoEsperado,resultadoReal));
    }

    private static Stream<Arguments> casosDePrueba() {
        return Stream.of(
                Arguments.of(19, false, true, 2000.00f),
                Arguments.of(68, false, true, 250.00f),
                Arguments.of(19, true, true, 250.00f),
                Arguments.of(19, false, false, 500.00f),
                Arguments.of(61, false, false, 400.00f)
        );
    }

    private String generateFailureMessage(float resultadoEsperado, float resultadoReal) {
        String message = "";
        message = message + "El resultado esperado era: " + resultadoEsperado + " y el resultado ha sido:" + resultadoReal;
        return message;
    }
}