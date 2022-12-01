package algorithmen;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.nio.file.*;
import java.util.Random;

import graph.*;
/**
 * Dieser Algorithmus bestimmt die kleinste dominierende Menge in einem Graphen
 * und bestimmt den Zeitbedarf.
 * Algorithmus: Greedy mit Strategie:
 *  Nimm den Knoten mit den meisten  Nachbarn
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_DominatingSetGreedyD extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Dominierende Menge (Greedy (d))";
    }

    /** Bestimmt besten Knoten nach Strategie:
     *  Nimm den Knoten mit den meisten  Nachbarn
     */
    private Knoten bestimmeBesten() {
        List<Knoten> knoten = g.getAlleKnoten(k->!k.isMarkiert());

        info("Wiederhole für jeden noch nicht markierten Knoten");
        infoIndentMore();
        for(Knoten k : knoten) {
            List<Knoten> nachbarn = g.getNachbarknoten(k, kn -> !kn.isMarkiert() && !kn.isBesucht());
            k.setWert(nachbarn.size());
            if(!k.isMarkiert() && !k.isBesucht()) k.setWert(k.getIntWert()+1);
            info("Setze Wert von Knoten Nr. "+g.getNummer(k)+" auf "+nachbarn.size()+" neu überdeckte Knoten");
        }
        infoIndentLess();
        info("Sortiere die Liste");
        knoten.sort(Comparator.comparing(Knoten::getIntWert));
        Knoten bester = knoten.get(0);
        info("Nimm den Knoten mit den wenigsten neu überdeckten Knoten => Knoten Nr. "+g.getNummer(bester));
        return bester;
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        List<Knoten> knoten = g.getAlleKnoten(k->!k.isMarkiert() && !k.isBesucht());
        info("Solange es noch nicht überdeckte Knoten gibt, wiederhole...");
        int nr = 1;
        while(knoten.size() > 0) {
            info("Bestimme "+(nr++)+". hinzuzufügenden Knoten");
            infoIndentMore();
            Knoten bester = bestimmeBesten();
            bester.setMarkiert(true);
            bester.setBesucht(false);
            info("Markiere diesen Knoten ...");
            List<Knoten> nachbarn = g.getNachbarknoten(bester,kn->!kn.isMarkiert() && !kn.isBesucht());
            for(Knoten k : nachbarn) {
                k.setBesucht(true);
            }
            info("... und setze alle bisher nicht überdeckten Nachbarn auf besucht");
            knoten = g.getAlleKnoten(kn->!kn.isMarkiert() && !kn.isBesucht());
            step();
            infoIndentLess();
        }// end of while

    } 

    // Ende Methoden
}
