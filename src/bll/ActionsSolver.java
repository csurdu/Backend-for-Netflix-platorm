package bll;

import bll.actions.ActionFactory;
import bll.data.PageBrowser;
import bll.data.PlatformManager;
import bll.data.StreamingPlatform;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Action;
import fileio.Input;
import fileio.Output;

import java.io.File;
import java.io.IOException;

public class ActionsSolver {
    private Input input;
    private PlatformManager platform;
    private String outputFileName;
    private ArrayNode output;

    public ActionsSolver(Input input, String outputFileName) {
        this.input = input;
        this.platform = new PlatformManager(new StreamingPlatform(input.getUsers(), input.getMovies()), new PageBrowser());
        this.outputFileName = outputFileName;
        this.output = new ObjectMapper().createArrayNode();
    }

    public void executeActions() throws Exception {
        for (Action action : input.getActions()) {
            JsonNode actionNode = doAction(action);
            //System.out.println(action);
            if (actionNode != null) {
                //System.out.println("    " + actionNode.toString());
                output.add(actionNode);
            } else {
                //System.out.println("    No output");
            }
        }
    }

    private JsonNode doAction(Action action) throws Exception {
        Output actionOutput = platform.doAction(ActionFactory.buildActionFromInput(action));
        if (actionOutput != null) {
            return new ObjectMapper().convertValue(actionOutput, JsonNode.class);
        }
        return null;
    }

    public void writeToOutputFile() throws IOException {
        File outputFile = new File(outputFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(outputFile, output);
    }
}
