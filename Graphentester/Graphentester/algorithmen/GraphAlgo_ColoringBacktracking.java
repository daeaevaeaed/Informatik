package algorithmen;

import java.util.List;
import java.util.Collections;
import java.nio.file.*;

import graph.*;
/**
 * Dieser Algorithmus färbt einen Graphen, so dass keine benachbarten Knoten
 * die gleiche Farbe haben und möglichst wenige Farben benutzt werden.
 * Algorithmus: Backtracking
 *
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_ColoringBacktracking extends GraphAlgo {

    // Anfang Attribute
    int besteAnzahl;
    List<String> beste;

    public  String getBezeichnung() {
        return "Map Coloring (Vollständing)";
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        if (g.getAnzahlKnoten()==0) {
            return;
        } 
        besteAnzahl = Integer.MAX_VALUE;
        bestimmeColoring(0);
        g.setStatus(beste);
        step();
    }

    private void bestimmeColoring(int benutzteFarben) {
        int min = Integer.MAX_VALUE;

        List<Knoten> knoten = g.getAlleKnoten(k->k.getFarbe()<=0);
        List<String> status = g.getStatus();
        if(knoten.size() == 0) {
            if(benutzteFarben < besteAnzahl)  {
                besteAnzahl = benutzteFarben;
                beste = status;
                info("Neue beste Lösung: "+besteAnzahl+" Farben");
            }
            else {
                info("Keine neue beste Lösung");
            }
            step();
            return;
        }

        Knoten aktuellerKnoten = knoten.get(0);
        info("Bearbeite einen noch nicht gefärbten Knoten: Knoten Nr. "+g.getNummer(aktuellerKnoten));
        infoIndentMore();

        boolean[] farbenliste = new boolean[g.getAnzahlKnoten()+1];
        List<Knoten> nachbarn = g.getNachbarknoten(aktuellerKnoten);
        info("Setze alle Farbe der Farbenliste auf unbenutzt und prüfe alle Nachbarknoten");
        infoIndentMore();
        // speichere alle Farben in dem Array farbenliste[], die in der Adjazenzliste vom Knoten k als Wert vorkommen
        for (Knoten k : nachbarn){
            info("Knoten "+g.getNummer(k)+": Setze Farbe "+k.getFarbe()+" auf benutzt");
            farbenliste[k.getFarbe()]=true;
        }
        infoIndentLess();

        info("Teste alle zulässigen Farben");
        infoIndentMore();
        for(int i=1; i<5; i++) {
            if(!farbenliste[i]){
                aktuellerKnoten.setFarbe(i);
                info("Setze Knoten "+g.getNummer(aktuellerKnoten)+" auf Farbe "+i);
                if(knoten.size()>1) step();
                infoIndentMore();
                bestimmeColoring(Math.max(i, benutzteFarben));
                info("Kehre zu Knoten Nr. "+g.getNummer(aktuellerKnoten)+" zurück");
                infoIndentLess();
            } else {
                info("Farbe "+i+" ist benutzt");
            }
        }
        infoIndentLess();

        aktuellerKnoten.setFarbe(0);
        infoIndentLess();
    } 

    // Ende Methoden
}
