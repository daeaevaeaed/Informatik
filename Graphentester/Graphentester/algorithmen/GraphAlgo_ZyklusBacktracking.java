package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus ist ein Beispiel für einen Backtracking-Algorithmus.
 * Er sucht einen Zyklus im Graphen.
 * Algorithmus: Backtracking
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_ZyklusBacktracking extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Zyklensuche (Backtracking)";
    }

    public void fuehreAlgorithmusAus() {
        List<String> loesung = backtracking(getStartKnoten());
        if(loesung != null) g.setStatus(loesung);
        step();
    }

    public List<String> backtracking(Knoten k){

        List<String> loesung = null;

        info("Untersuche Knoten "+g.getNummer(k));

        // Abbruchbedingung
        if (k.isMarkiert()) {
            // Ausführung unterbrechen
            info("Knoten ist schon bearbeitet => Zyklus gefunden");
            step();
            loesung = g.getStatus();
        } else {

            List<String> aktuellerZustand = g.getStatus();
            info("Knoten ist noch nicht bearbeitet => Speichere Zustand des Graphen");

            // Aktion mit Knoten durchführen, z.B. markieren
            k.setMarkiert(true);
            info("Markiere den Knoten und betrachte alle nicht markierten, ausgehenden Kanten");
            // Ausführung unterbrechen
            step();

            // Probiere alle Möglichkeiten (
            // hier alle nicht markierten, ausgehenden Kanten
            List<Kante> ausgehend = g.getAusgehendeKanten(k, ka->!ka.isMarkiert());
            infoIndentMore();
            int nr=1;
            for(Kante ausgehendeKante : ausgehend) {
                k.setMarkiert(true);
                ausgehendeKante.setMarkiert(true);
                info("Probiere Kante "+nr);
                infoIndentMore();
                Knoten nachbar = ausgehendeKante.getAnderesEnde(k);
                loesung =  backtracking(nachbar);
                infoIndentLess();
                g.setStatus(aktuellerZustand);
                info("Kehre zurück");
                step();
                if(loesung != null) break;
                nr++;
            }
            infoIndentLess();
        }
        return loesung;
    }
    // Ende Methoden

}

