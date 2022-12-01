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
 *  ein nicht abgedeckten Knoten, der von den ausgewählten Knoten eine möglichst große Entfernung hat
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_DominatingSetGreedyI extends GraphAlgo {

    // Anfang Attribute

    public  String getBezeichnung() {
        return "Dominierende Menge (Greedy (i))";
    }

    /** Bestimmt besten Knoten nach Strategie:
     *  ein nicht abgedeckten Knoten, der von den ausgewählten Knoten eine möglichst große Entfernung hat
     */   

    private Knoten bestimmeBesten() {
        Random r = new Random();
        List<Knoten> markierte = g.getAlleKnoten(k->k.isMarkiert() );
        List<Knoten> nichtabgedeckte = g.getAlleKnoten(k->!k.isMarkiert() && !k.isBesucht() );
        if(markierte.size()==0) return g.getKnoten(r.nextInt(g.getAnzahlKnoten()));

        List<String> status =  g.getStatus();
        g.initialisiereAlleKnoten();
        for(Knoten k : g.getAlleKnoten()) {
            k.setWert(Integer.MAX_VALUE);
            k.setMarkiert(false);
        }
        info("Setze alle Entfernungen auf unendlich");
        List<Knoten> toDo = new ArrayList<Knoten>();

        for(Knoten start: markierte) {
            for(Knoten k : g.getAlleKnoten()) {
                k.setBesucht(false);
                k.setMarkiert(false);
            }
            info("Bestimme Entfernung von Knoten "+g.getKnoteninfo(start,false)+" zu allen anderen Knoten");

            start.setBesucht(true);
            start.setWert(0);
            toDo.add(start);

            while(toDo.size()>0) {
                Knoten k = toDo.remove(0);
                k.setMarkiert(true);
                for(Knoten n : g.getNachbarknoten(k)) {
                    if(!n.isBesucht() && n.getIntWert()>k.getIntWert()+1){
                        n.setWert(k.getIntWert()+1);
                        toDo.add(n);
                        g.getKante(k,n).setMarkiert(true);
                        n.setBesucht(true);
                    } 
                }
            }
            info("... und reduziere Entfernung, wenn nötig.");

        }
        info("Sortiere Knoten nach Entfernung");
        nichtabgedeckte.sort(Comparator.comparing(Knoten::getIntWert).reversed());

        Knoten bester = nichtabgedeckte.get(0);
        bester.setFarbe(5);
        info("... und nimm den am weitesten entfernten");
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
