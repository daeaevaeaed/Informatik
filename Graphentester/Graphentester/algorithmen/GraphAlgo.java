package algorithmen;

import java.lang.Thread;
import java.nio.file.*;
import graph.*;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.application.Platform;


/**
 *
 * description
 *
 * @version 1.0 from 26.04.2019
 * @author 
 */

public abstract class GraphAlgo extends Thread {

    // Anfang Attribute
    private boolean stepping;
    private boolean waitforrepaint;
    private boolean waitforclick;
    protected boolean inArbeit;
    private GraphPlotter gp;
    private Knoten startKnoten;
    private int speed =100;
    private Hilfe hilfe;
    protected Graph g;
    private List<String> aktuellerZustand;
    // Ende Attribute

    // Anfang Methoden
    public GraphAlgo() {
        stepping = true;    
        waitforrepaint = false;
        waitforclick = false;
        aktuellerZustand = null;
        setDaemon(true);
    }

    public void setGUIElemente(GraphPlotter graphPlotter, Hilfe hilfe) {
        gp = graphPlotter;
        g = gp.getGraph();
        this.hilfe = hilfe;
        if (hilfe != null) hilfe.setGraphPlotter(gp);
    }
    
    public void setGraph(Graph g) {
        this.g = g;
        gp = null;
        hilfe = null;
    }

    public void step() {
        if(gp == null) return;
        try{
            gp.updateImage();
            aktuellerZustand = g.getStatus();
            waitforclick = true;
            if (hilfe != null) hilfe.setReviewAllowed(true);
            int i = 0;
            while((waitforclick && (stepping || i*10 < speed)) && !isInterrupted()){
              Thread.sleep(10);
              i++;
            }
            if (hilfe != null) hilfe.setReviewAllowed(false);
            g.setStatus(aktuellerZustand);
            aktuellerZustand = null;
        }catch(Exception e) {
            // Erneutes Stop, damit nicht stop während des Sleeps hier abgefangen wird.
            stop();
        }
    }

    public boolean getWaitforrepaint() {
        return waitforrepaint;
    }

    public void setWaitforrepaint(boolean waitforrepaintNeu) {
        waitforrepaint = waitforrepaintNeu;
    }

    public boolean getWaitforclick() {
        return waitforclick;
    }

    public void setWaitforclick(boolean waitforclickNeu) {
        waitforclick = waitforclickNeu;
    }

    public void setStepping(boolean stepping) {
        this.stepping = stepping;
    }

    public void setSpeed(int delay) {
        this.speed = delay;
    }

    public void run()
    {
        if(!inArbeit && gp != null)
        {
            // System.out.println("Algorithmus gestartet");
            inArbeit = true;
            try{
                if (hilfe != null) hilfe.setReviewAllowed(false);
                fuehreAlgorithmusAus();
                // System.out.println("Algorithmus beendet");
            } catch( ThreadDeath e){
                // System.out.println("Algorithmus vorzeitig beendet."); 
            }
            if (hilfe != null) hilfe.setReviewAllowed(true);
            inArbeit = false;
            return;
        }
        else
        {
            return;
        }
    }
    // Ende Methoden

    public void setStartKnoten(Knoten k) {
        startKnoten = k;
    }

    public Knoten getStartKnoten() {
        if (startKnoten != null) {
            return startKnoten;
        } else {
            return g.getKnoten(0);
        } // end of if-else
    }

    public abstract void fuehreAlgorithmusAus();

    public abstract String getBezeichnung();

    public void melde(String s) {
        info(s);
        Platform.runLater(() -> {
                Alert meldung = new Alert(AlertType.INFORMATION, s, ButtonType.OK);
                meldung.setTitle("Information");
                meldung.setHeaderText(null);
                meldung.showAndWait();
            });
    }

    public void info(String s) {
        if(hilfe != null) hilfe.append(s+"\n");
    }

    public void resetInfo() {
        if(hilfe != null) hilfe.loescheAlles();
    }

    public void infoIndentMore() {
        if(hilfe != null) hilfe.indentMore();

    }

    public void infoIndentLess() {
        if(hilfe != null) hilfe.indentLess();

    }


}
