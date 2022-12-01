package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;

/**
 *
 * Dieser Algorithmus sucht einen minimal Spanning Tree
 * Algorithmus: Prim
 *
 * @version 1.0 from 11.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_MST_Prim extends GraphAlgo {

    // Anfang Attribute

    // Ende Attribute
    public  String getBezeichnung() {
        return "MST (Prim)";
    }

    public void fuehreAlgorithmusAus() {
        int markiert = 0;
        List<Knoten> knoten;
        List<Kante> kanten;
        knoten = g.getAlleKnoten();
        kanten = g.getAlleKanten();

        if(knoten.size()==0) return;

        Collections.sort(kanten);
        info("Sortiere die Kanten nach ihrem Gewicht:");
        infoIndentMore();
        for(Kante ka2 : kanten) {
            info("Kante ("+g.getNummer(ka2.getStart())+"-"+g.getNummer(ka2.getZiel())+") mit Gewicht "+ka2.getGewicht());
        }
        infoIndentLess();
        if(getStartKnoten()!= null) {
            getStartKnoten().setMarkiert(true);
            info("Setze Startknoten auf markiert");
        } else {

            knoten.get(0).setMarkiert(true);
            info("Setze einen beliebigen Knoten auf markiert");
        }
        markiert++;
        step();

        while(knoten.size() > markiert) {
            info("Suche Kante mit dem geringsten Gewicht von markiertem Teilbaum zu unmarkiertem Teilbaum");
            infoIndentMore();
            Kante ka=null;
            for(Kante ka2 : kanten) {
                if(ka2.getStart().isMarkiert() != ka2.getZiel().isMarkiert()) {
                    ka = ka2;
                    break;
                }
                if(ka2.getStart().isMarkiert() && ka2.getZiel().isMarkiert()) {
                    info("Kante ("+g.getNummer(ka2.getStart())+"-"+g.getNummer(ka2.getZiel())+") mit Gewicht "+ka2.getGewicht()+": Beide Knoten schon markiert.");
                } else {
                    info("Kante ("+g.getNummer(ka2.getStart())+"-"+g.getNummer(ka2.getZiel())+") mit Gewicht "+ka2.getGewicht()+": Beide Knoten noch nicht markiert.");
                } 

            }
            infoIndentLess();
            if(ka != null) {
                ka.setMarkiert(true);
                kanten.remove(ka);
                info("Kante ("+g.getNummer(ka.getStart())+"-"+g.getNummer(ka.getZiel())+") mit Gewicht "+ka.getGewicht()+" gefunden. Markiere sie.");
                ka.getStart().setMarkiert(true);
                ka.getZiel().setMarkiert(true);
                markiert++;
                info("Markiere die angrenzenden Knoten.");
            }
            step();
        }    

    }

}
