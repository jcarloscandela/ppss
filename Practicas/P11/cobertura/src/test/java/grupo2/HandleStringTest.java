package grupo2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleStringTest {
    String input,esperado, real;
    HandleStrings handler;

    @BeforeEach
    public void init(){
        handler = new HandleStrings();
    }

    @Test
    public void C1(){
        esperado = "hola";
        input = "tal:hola:tal2";
        real = handler.extractMiddle(input);
        assertEquals(esperado,real);
    }
}
