package parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Value;

public class ObjectParseJSON {
    private ObjectMapper obj = new ObjectMapper();

    public String parseObjToJson(Object object) throws JsonProcessingException {
        return obj.writeValueAsString(object);
    }

    public Value parseJsonToObj(String str) throws JsonProcessingException {
        return obj.readValue(str, Value.class);
    }
}
