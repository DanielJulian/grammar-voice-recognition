package recognition;

import javax.speech.recognition.*;

public class GrammarListener extends ResultAdapter {

    String gst = "";
    Recognizer recognizer;

    public GrammarListener(Recognizer recognizer) {
        this.recognizer = recognizer;
    }

    @Override
    public void resultAccepted(ResultEvent re) {
        try {
            Result res = (Result) (re.getSource());
            ResultToken[] tokens = res.getBestTokens();

            String[] args = new String[1];
            args[0] = "";
            for (ResultToken token : tokens) {
                gst = token.getSpokenText();
                args[0] += gst + " ";
                System.out.print(gst + " ");
            }
            System.out.println();
            if (gst.equals("Salir")) {
                recognizer.deallocate();
                args[0] = "Nos vemos!";
                System.out.println(args[0]);
                System.exit(0);
            } else {
                recognizer.suspend();
                recognizer.resume();
            }
        } catch (Exception ex) {
            System.out.println("Ha ocurrido algo inesperado " + ex);
        }
    }
}
