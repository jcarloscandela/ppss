package grupo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultipathExampleTest {
    MultipathExample multi;
    int a,b,c,resultadoEsperado,resultadoReal;
    @BeforeEach
    public void init(){
        multi = new MultipathExample();
    }
    @Test
    public void C1(){
        resultadoEsperado = 12;
        a = 6;
        b = 6;
        c = 0;
        resultadoReal = multi.multiPath(a,b,c);
        assertEquals(resultadoEsperado,resultadoReal);
    }

    @Test
    public void C2(){
        resultadoEsperado = 0;
        a = 4;
        b = 4;
        c = 0;
        resultadoReal = multi.multiPath(a,b,c);
        assertEquals(resultadoEsperado,resultadoReal);
    }
}
