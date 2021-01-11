package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {
    int[] coleccionReal, coleccionEsperada;
    int numElem,numElemEsperado;
    int elemBorrar;
    DataArray ResultadoReal, ResultadoEsperado;
    @BeforeEach
    void initialize() {

        coleccionReal = new int[10];
        coleccionEsperada = new int[10];
        ResultadoReal = new DataArray();
    }

    @Test
    public void C1_DataArrayDelete() throws DataException {
        coleccionReal = new int[]{1,3,5,7,0,0,0,0,0,0};
        coleccionEsperada = new int[]{1,3,7,0,0,0,0,0,0,0};
        numElem = 4;
        elemBorrar = 5;
        ResultadoReal = new DataArray(coleccionReal,numElem);
        ResultadoReal.delete(elemBorrar);

        numElemEsperado = 3;
        ResultadoEsperado = new DataArray(coleccionEsperada,numElemEsperado);

        try {
            assertAll("GrupoTestC1",
                    ()->assertArrayEquals(ResultadoReal.getColeccion(),ResultadoEsperado.getColeccion()),
                    ()->assertEquals(ResultadoReal,ResultadoEsperado),
                    ()->assertEquals(ResultadoReal.size(),ResultadoEsperado.size()));

        }catch(Exception ex){
            assertEquals("", ex.getMessage());
        }
    }

    @Test
    public void C2_DataArrayDelete() throws DataException {
        coleccionReal = new int[]{1,3,3,5,7,0,0,0,0,0};

        numElem = 5;
        elemBorrar = 3;

        ResultadoReal = new DataArray(coleccionReal,numElem);
        coleccionReal = ResultadoReal.delete(elemBorrar);

        coleccionEsperada = new int[]{1,3,5,7,0,0,0,0,0,0};
        numElemEsperado = 4;
        ResultadoEsperado = new DataArray(coleccionEsperada,numElemEsperado);

        try {
            assertAll("GrupoTestC2",
                    ()->assertArrayEquals(ResultadoEsperado.getColeccion(),coleccionReal),
                    ()->assertEquals(ResultadoEsperado, ResultadoReal),
                    ()->assertEquals(ResultadoEsperado.size(),ResultadoReal.size()));

        }catch(Exception ex){
            assertEquals("", ex.getMessage());
        }
    }

    @Test
    public void C3_DataArrayDelete() throws DataException {
        coleccionReal = new int[]{1,2,3,4,5,6,7,8,9,10};
        coleccionEsperada = new int[]{1,2,3,5,6,7,8,9,10};
        numElem = 9;
        elemBorrar = 4;
        numElemEsperado = 10;

        ResultadoReal = new DataArray(coleccionReal,numElem);
        coleccionReal = ResultadoReal.delete(elemBorrar);
        ResultadoEsperado = new DataArray(coleccionEsperada,numElemEsperado);

        try {
            assertAll("GrupoTestC3",
                    ()->assertArrayEquals(ResultadoEsperado.getColeccion(),coleccionReal),
                    ()->assertEquals(ResultadoEsperado, ResultadoReal),
                    ()->assertEquals(ResultadoEsperado.size(),ResultadoReal.size()));

        }catch(Exception ex){
            assertEquals("", ex.getMessage());
        }

    }


}