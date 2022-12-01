package algorithmen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus findet die kürzesten Pfade in einem gewichteten Graphen.
 * Algorithmus: Dijkstra
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_Dijkstra extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Kürzester Pfad (Dijkstra)";
    }

    // Anfang Methoden

    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 

        info("Erzeuge leere toDo-Liste und füge Startknoten hinzu");
        List<Knoten> toDo = new ArrayList<Knoten>();
        getStartKnoten().setBesucht(true);
        getStartKnoten().setWert(0);
        toDo.add(getStartKnoten());

        while(toDo.size()>0) {
            info("Sortiere toDo-Liste");
            Collections.sort(toDo);
            info("Nimm ersten Knoten aus der toDo-Liste (momentan "+toDo.size()+" Elemente) heraus");
            Knoten k = toDo.remove(0);
            infoIndentMore();
            k.setMarkiert(true);
            info("Markiere den Knoten");
            info("Er hat Entfernung "+k.getIntWert());
            info("Für jeden Nachbarknoten");
            infoIndentMore();
            for(Knoten n : g.getNachbarknoten(k)) {
                if(!n.isMarkiert()){
                    info("- ist noch nicht markiert");
                    Kante ka = g.getKante(k, n);
                    if(!n.isBesucht() || n.getDoubleWert() > k.getDoubleWert()+ka.getGewicht()){
                        if(n.isBesucht()) {
                            List<Kante> eingehend = g.getEingehendeKanten(n, ka2 -> !ka2.isGeloescht() && ka2.isMarkiert());
                            Kante alterWeg = eingehend.get(0);
                            // Kante alterWeg = g.beschraenkeKantenAuf(g.getEingehendeKanten(n), Graph.MARKIERT, Graph.NICHTGELOESCHT).get(0);
                            // alterWeg.setGeloescht(true); 
                            // alterWeg.setMarkiert(false);
                            alterWeg.setGeloescht(true); 
                            alterWeg.setMarkiert(false);

                            info(" loesche bisherigen Weg dorthin");
                        }
                        n.setWert(k.getIntWert()+ka.getGewicht());
                        if(!toDo.contains(n)) toDo.add(n);
                        ka.setMarkiert(true);
                        n.setBesucht(true);
                        info("  setze Entfernung "+(n.getDoubleWert())+" und füge ggf. ToDo-Liste hinzu.");
                        info("  toDo-Liste hat jetzt "+toDo.size()+" Elemente");
                    } else {
                        info("  keine neue beste Entfernung");
                        ka.setGeloescht(true);
                    }
                } 
            }
            infoIndentLess();
            infoIndentLess();
            step();
        }
        info("ToDo-Liste fertig abgearbeitet");

    } // end
    // Ende Methoden

}

