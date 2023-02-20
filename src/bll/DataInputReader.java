package bll;

import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.Input;

import java.io.File;
import java.io.IOException;

public class DataInputReader {

    public static Input readInputFromFile(String inputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(inputFile), Input.class);
    }
}
