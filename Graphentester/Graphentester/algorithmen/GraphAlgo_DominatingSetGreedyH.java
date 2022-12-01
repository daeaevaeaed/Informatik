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
 *  ein nicht abgedeckten Knoten, der von möglichst vielen schon ausgewählten Knoten die Entfernung 3 hat
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_DominatingSetGreedyH extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Dominierende Menge (Greedy (h))";
    }

    /** Bestimmt besten Knoten nach Strategie:
     *  ein nicht abgedeckten Knoten, der von möglichst vielen schon ausgewählten Knoten die Entfernung 3 hat
     */
    private Knoten bestimmeBesten() {
        Random r = new Random();
        List<Knoten> markierte = g.getAlleKnoten(k->k.isMarkiert() );
        List<Knoten> nichtabgedeckte = g.getAlleKnoten(k->!k.isMarkiert() && !k.isBesucht() );
        if(markierte.size()==0) return g.getKnoten(r.nextInt(g.getAnzahlKnoten()));

        List<Knoten> entfernung3 = new ArrayList<Knoten>();
        List<String> status =  g.getStatus();
        g.initialisiereAlleKnoten();
        List<String> zaehlstatus = g.getStatus();

        for(Knoten start: markierte) {
            info("Bestimme Entfernung von Knoten "+g.getKnoteninfo(start,false)+" zu allen anderen Knoten");
            g.initialisiereAlleKnoten();
            GraphAlgo moore = new GraphAlgo_Moore();
            moore.setGraph(g);
            moore.setStartKnoten(start);
            moore.fuehreAlgorithmusAus();

            entfernung3 = g.getAlleKnoten(k->k.getIntWert()==3);
            entfernung3.retainAll(nichtabgedeckte);
            info("Habe "+entfernung3.size()+" noch nicht überdeckte Knoten mit Entfernung 3 gefunden ");
            g.setStatus(zaehlstatus);
            for(Knoten kandidat: entfernung3) {
                kandidat.setWert(kandidat.getIntWert()+1);
            }
            info("... und erhöher die Anzahl bei diesen Knoten");
            zaehlstatus= g.getStatus();

        }
        info("Sortiere die Knoten nach der Anzahl der ausgewählten Knoten mit Abstand 3");
        nichtabgedeckte.sort(Comparator.comparing(Knoten::getIntWert).reversed());

        Knoten bester = nichtabgedeckte.get(0);
        bester.setFarbe(5);
        info("Nehme den besten");
        step();
        g.setStatus(status);
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
