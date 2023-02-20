import bll.ActionsSolver;
import bll.DataInputReader;
import fileio.Input;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Input input = DataInputReader.readInputFromFile(args[0]);
            ActionsSolver solver = new ActionsSolver(input, args[1]);
            solver.executeActions();
            solver.writeToOutputFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
