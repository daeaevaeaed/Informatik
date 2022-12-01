package algorithmen;

import java.util.ArrayList;
import java.util.List;
import graph.*;
/**
 * Dieser Algorithmus testet, ob ein Eulerkreis existiert.
 * Algorithmus: Zunächst wird auf geraden Grad der Knoten getestet, danach 
 * mit Tiefensuche der Zusammenhang des Graphen überprüft.
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_EulerkreisExistenz extends GraphAlgo {

    public  String getBezeichnung() {
        return "Eulerkreis (Existenz)";
    }

    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 

        boolean gradOk = true;
        info("Setze gradOK auf true");
        for(Knoten k: g.getAlleKnoten()) {
            info("Knoten "+g.getNummer(k)+" hat Grad "+g.getNachbarknoten(k).size());
            if(g.getNachbarknoten(k).size() % 2 != 0) {
                gradOk = false;
                info("Setze gradOK auf false");
            }
        }
        info("Alle Knoten untersucht");
        step();

        if(!gradOk) {
            melde("Es gibt keinen Euler-Kreis, da der Grad nicht immer gerade ist");
            return;
        }

        List<Knoten> toDo = new ArrayList<Knoten>();
        getStartKnoten().setBesucht(true);
        toDo.add(getStartKnoten());
        info("Erzeuge leere toDo-Liste und füge Startknoten hinzu");

        int nr=0;
        while(toDo.size()>0) {
            info("Nimm ersten Knoten aus der toDo-Liste (momentan "+toDo.size()+" Elemente) heraus");
            Knoten k = toDo.remove(0);
            nr++;
            infoIndentMore();
            k.setMarkiert(true);
            k.setWert(nr);
            info("Markiere den Knoten und gib ihm die Nummer "+nr);
            info("Für jeden Nachbarknoten");
            infoIndentMore();
            for(Knoten n : g.getNachbarknoten(k)) {
                if(!n.isBesucht()){
                    info("- kennzeichne als besucht, füge der ToDo-Liste am Anfang hinzu.");
                    toDo.add(0, n);
                    g.getKante(k,n).setMarkiert(true);
                    n.setBesucht(true);
                    info("  toDo-Liste hat jetzt "+toDo.size()+" Elemente");
                } else {
                    info("- ist schon als besucht gekennzeichnet.");
                }
            }
            infoIndentLess();
            infoIndentLess();
            step();
        }
        if(nr == g.getAnzahlKnoten()) {
            melde("Es gibt einen Euler-Kreis");
        } else 
        {
            melde("Es gibt keinen Euler-Kreis, da der Graph nicht zusammenhängend ist.");
        }

    } // end

}

