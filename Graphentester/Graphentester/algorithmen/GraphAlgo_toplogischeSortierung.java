package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus findet eine topologische Sortierung des Graphen.
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_toplogischeSortierung extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Topologische Sortierung";
    }



    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        String reihenfolge = "";
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        info("Bestimme die Anzahl der eingehenden Kanten für jeden Knoten");
        infoIndentMore();
        List<Knoten> knoten = g.getAlleKnoten();
        for(Knoten k: knoten) {
            k.setWert(g.getEingehendeKanten(k).size());
            info("Setze Wert von von "+g.getKnoteninfo(k, false)+" auf "+g.getEingehendeKanten(k).size());
        }
        infoIndentLess();
        step();
        
        while(knoten.size()>0) {
          Collections.sort(knoten);
          info("Sortiere die noch nicht markierten Knoten nach ihrem Wert");
          Knoten k = knoten.get(0);
          k.setMarkiert(true);
          info("Nimm Knoten "+g.getKnoteninfo(k,false)+" und markiere ihn.");

          if(k.getIntWert() != 0) {
              melde("Fehler: Wert ist nicht 0 - Zyklus vorhanden");
              knoten.clear();
              return;
          } else {
             reihenfolge += " "+g.getKnoteninfo(k, false);
             info("Füge ihn der Liste hinzu: "+reihenfolge);
             knoten.remove(k);
             for(Knoten k2 : g.getNachbarknoten(k)) {
               k2.setWert(k2.getIntWert()-1);
             }
             info("Reduziere den Wert aller Nachbarn von Knoten "+g.getNummer(k)+" um 1");
          }
          step();
        }
        melde("Topologische Sortierung: "+reihenfolge);
        
        
    } // end

    // Ende Methoden

}

