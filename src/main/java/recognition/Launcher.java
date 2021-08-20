package recognition;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineModeDesc;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;

public class Launcher  {

    public static void main(String[] args) {

        try {
            Recognizer recognizer = createRecognizer();
            FileReader grammar1 = new FileReader("Gramatica.txt");

            RuleGrammar rg = recognizer.loadJSGF(grammar1);
            rg.setEnabled(true);
            recognizer.addResultListener(new GrammarListener(recognizer));

            System.out.println("Empiece a dictar");

            recognizer.commitChanges();
            recognizer.requestFocus();
            recognizer.resume();

        } catch (Exception e) {
            System.out.println("Ocurrió un Error");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static Recognizer createRecognizer() throws EngineException {
        Recognizer recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
        recognizer.allocate();
        return recognizer;
    }


}