package com.portfolio.api.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TestDataReader se encarga de leer archivos JSON desde el classpath
 * y convertirlos en objetos Java utilizando Jackson.
 */
public class TestDataReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Lee un archivo JSON y lo mapea a un array de datos.
     *
     * @param fileName Nombre del archivo en src/test/resources/testdata/
     * @return Array de datos en formato Object[][].
     */
    public static Object[][] readTestData(String fileName) {
        // Intentamos cargar el recurso usando el classloader del sistema
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata/" + fileName)) {
            if (is == null) {
                throw new RuntimeException("No se pudo encontrar el archivo de datos: " + fileName);
            }

            List<List<String>> data = objectMapper.readValue(is, new TypeReference<List<List<String>>>() {});
            Object[][] result = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                List<String> row = data.get(i);
                result[i] = row.toArray();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo de datos: " + fileName, e);
        }
    }
}
