package algorithmen;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus nummeriert alle Knoten des Graphen.
 * Algorithmus: Breitensuche mit ToDo-Liste (Schlange)
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_Breitensuche extends GraphAlgo {

    // Anfang Attribute

    public String getBezeichnung() {
        return "Nummerierung (Breitensuche)";
    }

// Anfang Methoden
    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        int nr = 0;
        info("Erzeuge leere toDo-Liste und füge Startknoten hinzu");
        List<Knoten> toDo = new ArrayList<Knoten>();
        toDo.add(getStartKnoten());

        while(toDo.size()>0) {
            info("Nimm ersten Knoten aus der toDo-Liste (momentan "+toDo.size()+" Elemente) heraus");
            Knoten k = toDo.remove(0);
            nr++;
            infoIndentMore();
            k.setBesucht(false);
            k.setMarkiert(true);
            k.setWert(nr);
            info("Markiere den Knoten und gib ihm die Nummer "+nr);
            info("Für jeden Nachbarknoten");
            infoIndentMore();
            for(Knoten n : g.getNachbarknoten(k)) {
                if(!n.isMarkiert()){
                    if( !toDo.contains(n)) {
                        toDo.add(n);
                        g.getKante(k,n).setMarkiert(true);
                        n.setBesucht(true);
                        info("- ist noch nicht markiert, füge der ToDo-Liste am Ende hinzu.\n"
                             +"  toDo-Liste hat jetzt "+toDo.size()+" Elemente");
                    } else {
                        info("- ist schon in ToDo-Liste");
                    }
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

