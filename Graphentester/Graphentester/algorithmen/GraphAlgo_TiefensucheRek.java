package algorithmen;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus nummeriert alle Knoten des Graphen.
 * Algorithmus: Tiefensuche rekursiv
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_TiefensucheRek extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Nummerierung (Tiefensuche rekursiv)";
    }


    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        nummeriere(getStartKnoten(), 0);
    } // end

    private int nummeriere(Knoten k, int nr) {
        // Abbruchbedingung
        if(k.isBesucht()) {
            info("Untersuche "+g.getKnoteninfo(k,false)+" => ist schon besucht");
        } else {
            nr++;
            k.setBesucht(true);
            k.setWert(nr);
            info("Untersuche "+g.getKnoteninfo(k,false)+" => bekommt Nummer: "+nr);
            step();
            info("Untersuche Nachbarn von "+g.getKnoteninfo(k,false));
            infoIndentMore();
            List<Knoten> nachbarn = g.getNachbarknoten(k);
            for(Knoten n : nachbarn) {
                nr = nummeriere(n,nr);
            }
            info("Keine weiteren Nachbarn von "+g.getKnoteninfo(k,false));
            infoIndentLess();
            step();
        }
        return nr;

    }
    // Ende Methoden

}

