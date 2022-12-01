package algorithmen;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus findet die kürzesten Pfade in einem ungewichteten Graphen.
 * Algorithmus: Algorithmus A von Moore
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_Moore extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "Kürzester Pfad (Moore)";
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
            info("Nimm ersten Knoten aus der toDo-Liste (momentan "+toDo.size()+" Elemente) heraus");
            Knoten k = toDo.remove(0);
            infoIndentMore();
            k.setMarkiert(true);
            info("Markiere den Knoten");
            info("Er hat Entfernung "+k.getIntWert());
            info("Für jeden Nachbarknoten");
            infoIndentMore();
            for(Knoten n : g.getNachbarknoten(k)) {
                if(!n.isBesucht()){
                        n.setWert(k.getIntWert()+1);
                        toDo.add(n);
                        info("- ist noch nicht markiert, setze Entfernung "+(k.getIntWert()+1)+" und füge der ToDo-Liste am Ende hinzu.");
                        g.getKante(k,n).setMarkiert(true);
                        n.setBesucht(true);
                        info("  toDo-Liste hat jetzt "+toDo.size()+" Elemente");
                } else {
                    info("- ist schon markiert");
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

