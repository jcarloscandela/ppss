package ppss;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tags({@Tag("SinParametrizar"), @Tag("LlanosTest")})
class LlanosTest {

    Tramo resultadoEsperado, resultadoReal;
    ArrayList<Integer> lecturas;
    Llanos llanos;

    @BeforeEach void initialize() {
        resultadoEsperado = new Tramo();
        resultadoReal = new Tramo();
        lecturas = new ArrayList<Integer>();
        llanos = new Llanos();
    }


    @Test
    @Tag("LlanosA")
    public void C1A_buscarTramoLlano() {
        lecturas.add(3);
        resultadoEsperado = new Tramo(0,0);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("LlanosA")
    public void C2A_buscarTramoLlano() {
        lecturas.add(100);
        lecturas.add(100);
        lecturas.add(100);
        lecturas.add(100);
        resultadoEsperado = new Tramo(0,3);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("LlanosA")
    public void C3A_buscarTramoLlano() {
        lecturas.add(120);
        lecturas.add(140);
        lecturas.add(180);
        lecturas.add(180);
        lecturas.add(180);
        resultadoEsperado = new Tramo(2,2);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("LlanosB")
    public void C1B_buscarTramoLlano() {
        lecturas.add(-1);
        resultadoEsperado = new Tramo(0,0);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("LlanosB")
    public void C2B_buscarTramoLlano() {
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);
        lecturas.add(-1);
        resultadoEsperado = new Tramo(0,3);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);

        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("LlanosB")
    public void C3B_buscarTramoLlano() {
        lecturas.add(120);
        lecturas.add(140);
        lecturas.add(-10);
        lecturas.add(-10);
        lecturas.add(-10);
        resultadoEsperado = new Tramo(2,2);
        resultadoReal = llanos.buscarTramoLlanoMasLargo(lecturas);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @AfterAll static void finalState(){

    }


}