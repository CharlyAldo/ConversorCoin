import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivo {
    public void guardarJson(Moneda moneda) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = moneda.base() + "_a_" + moneda.destino() + ".json";
        FileWriter escritura = new FileWriter(nombreArchivo);
        escritura.write(gson.toJson(moneda));
        escritura.close();
        System.out.println("💾 Archivo JSON guardado como: " + nombreArchivo);
    }
}
