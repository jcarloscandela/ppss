package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class testListadoMock {
    String ap1,ap2, nombre;
    Connection connMock;
    Statement stmMock;
    ResultSet rsMock;
    String resultadoEsperado,resultadoReal,tableName;
    Listados testObject;

    @BeforeEach
    public void setUp(){
        connMock = EasyMock.createMock(Connection.class);
        stmMock = EasyMock.createMock(Statement.class);
        rsMock = EasyMock.createStrictMock(ResultSet.class);

        tableName = "alumnos";
        testObject = new Listados();
    }

    @Test
    public void c1testPorApellido() throws SQLException {
        resultadoEsperado = "Garcia, Planelles, Jorge\nPérez, Verdú, Carmen\n";


        EasyMock.expect(rsMock.next()).andReturn(true);
        EasyMock.expect(rsMock.getString("apellido1")).andReturn("Garcia");
        EasyMock.expect(rsMock.getString("apellido2")).andReturn("Planelles");
        EasyMock.expect(rsMock.getString("nombre")).andReturn("Jorge");
        EasyMock.expect(rsMock.next()).andReturn(true);
        EasyMock.expect(rsMock.getString("apellido1")).andReturn("Pérez");
        EasyMock.expect(rsMock.getString("apellido2")).andReturn("Verdú");
        EasyMock.expect(rsMock.getString("nombre")).andReturn("Carmen");
        EasyMock.expect(rsMock.next()).andReturn(false);

        EasyMock.expect(stmMock.executeQuery("SELECT apellido1, apellido2, nombre FROM " + tableName))
                .andReturn(rsMock);
        EasyMock.expect(connMock.createStatement()).andReturn(stmMock);

        EasyMock.replay(connMock,stmMock,rsMock);
        resultadoReal = testObject.porApellidos(connMock, tableName);
        assertEquals(resultadoEsperado, resultadoReal);
        EasyMock.verify(connMock,stmMock,rsMock);
    }

    @Test
    public void testPorApellidosC2() throws Exception {
        resultadoEsperado = "";

        EasyMock.expect(connMock.createStatement()).andReturn(stmMock);
        EasyMock.expect(stmMock.executeQuery("SELECT apellido1, apellido2, nombre FROM " + tableName))
                .andReturn(rsMock);
        EasyMock.expect(rsMock.next()).andThrow(new SQLException());
        EasyMock.replay(connMock,stmMock,rsMock);
        try{
            resultadoReal = testObject.porApellidos(connMock, tableName);
            fail();
        }catch(SQLException e){
            System.out.println("Lanza: "+e.getMessage());
        }
        EasyMock.verify(connMock,stmMock,rsMock);
    }
}
