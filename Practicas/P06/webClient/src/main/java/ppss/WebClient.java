package ppss;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
    public String getContent(URL url) {
        StringBuilder content = new StringBuilder();
        try {
            //creamos una conexión http
            HttpURLConnection connection = createHttpURLConnection(url);
            //abrimos la conexión http, puede lanzar la excepción IOException
            InputStream is = connection.getInputStream();
            //leemos el contenido de la conexión
            byte[] buffer = new byte[2048];
            int count;
            while (-1 != (count = is.read(buffer))) {
                content.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }

    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}