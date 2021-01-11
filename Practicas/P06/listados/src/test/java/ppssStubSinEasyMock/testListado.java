package ppssStubSinEasyMock;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class testListado {
    Listados instance = new Listados();
    String resultadoEsperado, resultadoReal, tableName;
    ConnectionStub connStub = new ConnectionStub();
    StatementStub stStub = new StatementStub();
    ResultSetStub rsStub = new ResultSetStub();

    @Test
    public void c1testporApelldos() throws SQLException {
        resultadoEsperado = "Garcia, Planelles, Jorge\nPérez, Verdú, Carmen\n";

        rsStub.setString("Garcia, Planelles, Jorge\nPérez, Verdú, Carmen\n");
        stStub.setRS(rsStub);
        connStub.setStatement(stStub);
        resultadoReal = instance.porApellidos(connStub,tableName);
        assertEquals(resultadoEsperado,resultadoReal);
    }


    @Test
    public void c2testporApelldos() throws SQLException {
        rsStub.setString("Error");
        stStub.setRS(rsStub);
        connStub.setStatement(stStub);

        assertThrows(SQLException.class, () -> instance.porApellidos(connStub,tableName)) ;

    }
}
