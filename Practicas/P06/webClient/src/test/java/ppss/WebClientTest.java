package ppss;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebClientTest {
    WebClient wb;
    HttpURLConnection httpMock;
    String resultadoEsperado, resultadoReal;
    URL url;

    @BeforeEach
    public void setUp(){
        wb = EasyMock.createMockBuilder(WebClient.class).addMockedMethod("createHttpURLConnection").createMock();
        httpMock = EasyMock.createMock(HttpURLConnection.class);
    }
    @Test
    public void c1testGetContent() throws IOException {
        //url = new URL(new ByteArrayInputStream("http://google.es".getBytes()));
        url = new URL("http://google.es");
        resultadoEsperado = "Se puede abrir la conexión";
        EasyMock.expect(httpMock.getInputStream()).andReturn(new ByteArrayInputStream(resultadoEsperado.getBytes()));
        EasyMock.replay(httpMock);
        EasyMock.expect(wb.createHttpURLConnection(url)).andReturn(httpMock);
        EasyMock.replay(wb);
        resultadoReal = wb.getContent(url);
        assertEquals(resultadoEsperado, resultadoReal);
        EasyMock.verify(httpMock,wb);
    }

    @Test
    public void c2testGetContent() throws IOException {
        //url = new URL(new ByteArrayInputStream("http://google.es".getBytes()));
        url = new URL("http://google.es");
        resultadoEsperado = null;
        EasyMock.expect(httpMock.getInputStream()).andThrow(new IOException());
        EasyMock.replay(httpMock);
        EasyMock.expect(wb.createHttpURLConnection(url)).andReturn(httpMock);
        EasyMock.replay(wb);
        resultadoReal = wb.getContent(url);
        assertEquals(resultadoEsperado, resultadoReal);
        EasyMock.verify(httpMock,wb);
    }
}
