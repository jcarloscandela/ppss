package ppss;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class DataArrayTest {

    int elemento;
    int resultadoEsperado;
    String resultadoEsperadoS;
    int resultadoReal;
    String resultadoRealS;
    DataArray dataArray;

    @BeforeEach
    public void setUp(){
        dataArray = new DataArray(new int[10],0);
    }

    //Añadir un elemento a una colección vacía
    @Test
    public void testAddDataArrayC1() {
        System.err.println("--Add test C1");
        elemento = 3;
        dataArray.add(elemento);
        resultadoEsperado = 1;
        resultadoReal = dataArray.size();
        resultadoEsperadoS = "3,";
        resultadoRealS = dataArray.toString();
        assertEquals(resultadoEsperadoS, resultadoRealS);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    //Añadir un elemento a una colección de más de un elemento
    @Test
    public void testAddDataArrayC2() {
        System.err.println("--Add test C2");
        elemento = 3;
        dataArray.add(elemento);
        dataArray.add(elemento + 1);
        resultadoEsperado = 2;
        resultadoReal = dataArray.size();
        resultadoEsperadoS = "3,4,";
        resultadoRealS = dataArray.toString();
        assertEquals(resultadoEsperadoS, resultadoRealS);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    //Añadir un elemento a una colección llena
    @Test
    public void testAddDataArrayC3() {
        System.err.println("--Add test C3");
        elemento = 3;
        for (int i = 0; i <= 11; i++) {
            dataArray.add(elemento);
            elemento++;
        }
        resultadoEsperado = 10;
        resultadoReal = dataArray.size();
        resultadoEsperadoS = "3,4,5,6,7,8,9,10,11,12,";
        resultadoRealS = dataArray.toString();
        assertEquals(resultadoEsperadoS, resultadoRealS);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    //Borrar elemento de DataArray vacio
    @Test
    public void testDeleteC1() {
        System.err.println("--Delete test C1");
        elemento = 3;
        dataArray.delete(elemento);
        resultadoEsperadoS = "";
        resultadoRealS = dataArray.toString();
        resultadoReal = dataArray.size();
        resultadoEsperado = 0;
        assertEquals(resultadoEsperado, resultadoReal);
        assertEquals(resultadoEsperadoS, resultadoRealS);
    }

    //Borrar elemento de DataArray con un elemento
    @Test
    public void testDeleteC2() {
        System.err.println("--Delete test C2");
        elemento = 3;
        dataArray.add(elemento);
        dataArray.delete(elemento);
        resultadoEsperadoS = "";
        resultadoRealS = dataArray.toString();
        resultadoReal = dataArray.size();
        resultadoEsperado = 0;
        assertEquals(resultadoEsperado, resultadoReal);
        assertEquals(resultadoEsperadoS, resultadoRealS);
    }

    //Borrar elemento final de DataArray
    @Test
    public void testDeleteC3() {
        System.err.println("--Delete test C3");
        elemento = 3;
        dataArray.add(elemento - 2);
        dataArray.add(elemento - 1);
        dataArray.add(elemento);
        resultadoEsperadoS = "1,2,";
        dataArray.delete(elemento);
        resultadoRealS = dataArray.toString();
        resultadoReal = dataArray.size();
        resultadoEsperado = 2;
        assertEquals(resultadoEsperado, resultadoReal);
        assertEquals(resultadoEsperadoS, resultadoRealS);
    }

    //Borrar elemento medio de DataArray
    @Test
    public void testDeleteC4() {
        System.err.println("--Delete test C4");
        elemento = 3;
        dataArray.add(elemento - 2);
        dataArray.add(elemento);
        dataArray.add(elemento + 1);
        resultadoEsperadoS = "1,4,";
        dataArray.delete(elemento);
        resultadoRealS = dataArray.toString();
        resultadoReal = dataArray.size();
        resultadoEsperado = 2;
        assertEquals(resultadoEsperado, resultadoReal);
        assertEquals(resultadoEsperadoS, resultadoRealS);
    }

    //Borrar elemento inicio de DataArray
    @Test
    public void testDeleteC5() {
        System.err.println("--Delete test C5");
        elemento = 3;
        dataArray.add(elemento);
        dataArray.add(elemento + 1);
        dataArray.add(elemento + 2);
        resultadoEsperadoS = "4,5,";
        dataArray.delete(elemento);
        resultadoRealS = dataArray.toString();
        resultadoReal = dataArray.size();
        resultadoEsperado = 2;
        assertEquals(resultadoEsperado, resultadoReal);
        assertEquals(resultadoEsperadoS, resultadoRealS);
    }
}
