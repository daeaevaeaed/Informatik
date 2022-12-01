package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;

/**
 *
 * Dieser Algorithmus sucht einen minimal Spanning Tree
 * Algorithmus: Kruskal
 *
 * @version 1.0 from 11.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_MST_Kruskal extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "MST (Kruskal)";
    }


    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        int farbe = 1;
        List<Kante> kanten = g.getAlleKanten();
        List<Knoten> knoten = g.getAlleKnoten();
        info("Hole eine Liste aller Kanten und eine aller Knoten");
        Collections.sort(kanten);
        info("Sortiere Kanten aufsteigend");
        info("Wiederhole für alle Kanten:");
        for (Kante k: kanten) {
            info("Bearbeite Kante mit Gewicht: "+k.getGewicht());
            infoIndentMore();
            int f1 = k.getStart().getFarbe();
            int f2 = k.getZiel().getFarbe();
            if(f1 == 0 && f2 == 0) {
                info("Beide Knoten gehören noch zu keinem Teilgraphen");
                k.getStart().setFarbe(farbe);
                k.getZiel().setFarbe(farbe);
                k.setMarkiert(true);
                info("=> setze beide auf Farbe "+farbe+" und markiere die Kante");
                farbe++;
            } else
            if(f1 == 0) {
                info("Der Knoten Nr. "+g.getNummer(k.getStart())+" gehört noch zu keinem Teilgraphen");
                k.getStart().setFarbe(f2);
                k.setMarkiert(true);
                info("=> setze ihn auf die Farbe des Knotens Nr. "+g.getNummer(k.getZiel())+" und markiere die Kante");
            } else
            if(f2 == 0) {
                info("Der Knoten Nr. "+g.getNummer(k.getZiel())+" gehört noch zu keinem Teilgraphen");
                k.getZiel().setFarbe(f1);
                k.setMarkiert(true);
                info("=> setze ihn auf die Farbe des Knotens Nr. "+g.getNummer(k.getStart())+" und markiere die Kante");
            } else
            if(f1 == f2) {
                info("Beide Knoten gehören zum gleichen Teilgraphen");
                k.setGeloescht(true);
                info("lösche die Kante");
            } else 
            {
                info("Beide Knoten gehören zu unterschiedlichen Teilgraphen");
                int min = Math.min(f1,f2);
                int max = Math.max(f1,f2);
                for(Knoten k1 : knoten) {
                    if(k1.getFarbe() == max) k1.setFarbe(min);
                }
                info("=> färbe alle Knoten mit Farbe "+max+" mit der Farbe "+min);
                k.setMarkiert(true);
                info("   und markiere die Kante");
            }
            infoIndentLess();
            step();
        }
    } // end of for

    // Ende Methoden

}

