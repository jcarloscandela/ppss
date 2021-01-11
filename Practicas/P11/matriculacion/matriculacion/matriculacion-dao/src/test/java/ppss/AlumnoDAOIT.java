package ppss;

import org.apache.log4j.BasicConfigurator;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlumnoDAOIT {
    private FactoriaDAO _Factory;
    private IAlumnoDAO _Alumno;
    private IDatabaseTester databaseTester;

    @BeforeAll
    public static void only_once() {
        // Para evitar el mensaje
        // SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
        // SLF4J: Defaulting to no-operation (NOP) logger implementation
        BasicConfigurator.configure();

    }

    @BeforeEach
    public void setUp() throws Exception {
        databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/matriculacion?useSSL=false", "root", "ppss");

        _Factory = new FactoriaDAO();
        _Alumno = _Factory.getAlumnoDAO();
    }

    @Test
    public void testA1() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1985);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 22);

        AlumnoTO a = new AlumnoTO();
        a.nif = "33333333C";
        a.nombre = "Elena Aguirre Juarez";
        a.setFechaNacimiento(cal.getTime());

        _Alumno.addAlumno(a);

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla3.xml");

        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testA2() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 22);

        AlumnoTO a = new AlumnoTO();
        a.nif = "11111111A";
        a.nombre = "Alfonso Ramirez Ruiz";
        a.setFechaNacimiento(cal.getTime());

        DAOException expectedException = new DAOException("Error al conectar con BD");
        DAOException actualException = new DAOException();
        try{
            _Alumno.addAlumno(a);
        }catch(DAOException ex){
            actualException = ex;
        }
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testA3() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 22);

        AlumnoTO a = new AlumnoTO();
        a.nif = "44444444D";
        a.nombre = null;
        a.setFechaNacimiento(cal.getTime());

        DAOException expectedException = new DAOException("Error al conectar con BD");
        DAOException actualException = new DAOException();
        try{
            _Alumno.addAlumno(a);
        }catch(DAOException ex){
            actualException = ex;
        }
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testA4() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 22);

        AlumnoTO a = null;

        DAOException expectedException = new DAOException("Error al conectar con BD");
        DAOException actualException = new DAOException();
        try{
            _Alumno.addAlumno(a);
        }catch(DAOException ex){
            actualException = ex;
        }
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testA5() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.YEAR, 1982);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 22);

        AlumnoTO a = new AlumnoTO();
        a.nif = null;
        a.nombre = "Pedro Garcia Lopez";
        a.setFechaNacimiento(cal.getTime());

        DAOException expectedException = new DAOException("Error al conectar con BD");
        DAOException actualException = new DAOException();
        try{
            _Alumno.addAlumno(a);
        }catch(DAOException ex){
            actualException = ex;
        }
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }

    @Test
    public void testB1() throws Exception{
        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla2.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        _Alumno.delAlumno("11111111A");

        IDatabaseConnection connection = databaseTester.getConnection();

        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("alumnos");

        IDataSet expectedDataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");

        ITable expectedTable = expectedDataSet.getTable("alumnos");

        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void testB2() throws Exception{

        IDataSet dataSet = new FlatXmlDataFileLoader().load("/tabla4.xml");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        DAOException expectedException = new DAOException("No se ha borrado ningun alumno");
        DAOException actualException = new DAOException();
        try{
            _Alumno.delAlumno("33333333C");
        }catch(DAOException ex){
            actualException = ex;
        }
        assertEquals(expectedException.getMessage(), actualException.getMessage());
    }
}



