package algorithmen;

import java.util.List;
import java.nio.file.*;
import java.util.Random;
import java.util.Arrays;

import graph.*;

/**
 * Dieser Algorithmus bestimmt die kleinste dominierende Menge in einem Graphen
 * und bestimmt den Zeitbedarf.
 * Algorithmus: Genetischer Algorithmus
 * @version 1.0 from 10.12.2020
 * @author Thomas Schaller
 */

public class GraphAlgo_DominatingSetGenetisch extends GraphAlgo {

    private int popGroesse=500;
    private int fitGroesse=80;
    private int[][] population;
    private int[][] fittest;
    private int generation;

    // Anfang Attribute

    public String getBezeichnung() {
        return "Dominierende Menge (Genetisch)";
    }


    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        population = new int[popGroesse][g.getAnzahlKnoten()];
        double[] bewertungen = new double[popGroesse];
        for(int i=0; i<popGroesse; i++) {
            population[i] = erzeugeZufaelligeTeilmenge();
            bewertungen[i] = getBewertung(population[i]);
        }
        fittest = new int[fitGroesse][g.getAnzahlKnoten()];
        for(int i=0; i < fitGroesse; i++) {
            int beste = 0;
            for(int j=1; j<popGroesse; j++) {
                if(bewertungen[j] > bewertungen[beste]) {
                    beste = j;
                }
            }
            fittest[i] = population[beste];
            bewertungen[beste] = Double.MIN_VALUE;
        }
        showTeilmenge(fittest[0]);

        Random r = new Random();
        for(generation = 0; generation < 100; generation++) {
            for(int j=0; j <fitGroesse; j++) {
                population[j]=fittest[j];
                bewertungen[j] = getBewertung(population[j]);
            }
            for(int j=fitGroesse; j<popGroesse; j++) {
                int i1 = r.nextInt(fitGroesse);
                int i2 = r.nextInt(fitGroesse);
                population[j] = mutiere(kreuze(fittest[i1],fittest[i2]));
                bewertungen[j] = getBewertung(population[j]);
            }
            fittest = new int[fitGroesse][g.getAnzahlKnoten()];
            for(int i=0; i < fitGroesse; i++) {
                int beste = 0;
                for(int j=1; j<popGroesse; j++) {
                    if(bewertungen[j] > bewertungen[beste]) {
                        beste = j;
                    }
                }
                fittest[i] = population[beste];
                bewertungen[beste] = Double.MIN_VALUE;
            }
            showTeilmenge(fittest[0]);
            this.info("Bisher beste dominierende Menge (Generation "+generation+"): "+(g.getAnzahlKnoten()-getBewertung(fittest[0]))+" Knoten.");
            step();
        }

        step();
    }

    public int[] erzeugeZufaelligeTeilmenge(){
        Random r = new Random();
        int[] teilmenge = new int[g.getAnzahlKnoten()];

        for(int i=0; i< g.getAnzahlKnoten(); i++) {
            teilmenge[i] = r.nextInt(2);
        }
        return teilmenge;
    }

    public int[] kreuze(int[] tm1, int[] tm2) {
        Random r = new Random();
        int crossover = r.nextInt(tm1.length);
        int[] new_tm = Arrays.copyOf(tm1, tm1.length);
        for(int j = crossover; j< tm2.length; j++) {
            new_tm[j] = tm2[j];
        }
        return new_tm;
    }

    public int[] mutiere(int[] tm) {
        Random r = new Random();
        int anz_mut = r.nextInt(3);
        int[] new_tm = Arrays.copyOf(tm, tm.length);
        for(int z =0; z<anz_mut; z++) {
            int pos1 = r.nextInt(tm.length);
            if(new_tm[pos1]==0) new_tm[pos1]=1; else new_tm[pos1]=0;
        }
        return new_tm;
    }

    public void showTeilmenge(int[] tm) {
        g.initialisiereAlleKnoten();
        for(int i=0; i<tm.length; i++) {
            if(tm[i]==1) {
                g.getKnoten(i).setMarkiert(true);
                g.getKnoten(i).setBesucht(false);
                for(Knoten k : g.getNachbarknoten(g.getKnoten(i))) {
                    if(!k.isMarkiert()) k.setBesucht(true);
                }
            }
        }
    }

    public double getBewertung(int[] tm) {
        int anz_ueberdeckt = 0;
        for(int i=0; i<tm.length; i++) {
            if(tm[i]==0)
            {
                for(Knoten k: g.getNachbarknoten(g.getKnoten(i))) {
                    if(tm[g.getNummer(k)]==1) {
                        anz_ueberdeckt++;
                        break;
                    }
                }
            }
        }

        return anz_ueberdeckt;
    }



    // Ende Methoden
}
