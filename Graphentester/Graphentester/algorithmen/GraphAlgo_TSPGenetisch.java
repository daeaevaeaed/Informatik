package algorithmen;

import java.util.List;
import java.nio.file.*;
import java.util.Random;
import java.util.Arrays;

import graph.*;

/**
 *
 * description
 *
 * @version 1.0 from 26.04.2019
 * @author 
 */

public class GraphAlgo_TSPGenetisch extends GraphAlgo {

    private int popGroesse=500;
    private int fitGroesse=80;
    private int[][] population;
    private int[][] fittest;
    private int generation;

    // Anfang Attribute

    public String getBezeichnung() {
        return "TSP (Genetisch)";
    }

    // Anfang Methoden
    public void fuehreAlgorithmusAus() {
        population = new int[popGroesse][g.getAnzahlKnoten()+1];
        double[] rundreiseLaenge = new double[popGroesse];
        for(int i=0; i<popGroesse; i++) {
            population[i] = erzeugeZufaelligeRundreise();
            rundreiseLaenge[i] = getLaenge(population[i]);
        }
        fittest = new int[fitGroesse][g.getAnzahlKnoten()+1];
        for(int i=0; i < fitGroesse; i++) {
            int beste = 0;
            for(int j=1; j<popGroesse; j++) {
                if(rundreiseLaenge[j] < rundreiseLaenge[beste]) {
                    beste = j;
                }
            }
            fittest[i] = population[beste];
            rundreiseLaenge[beste] = Double.MAX_VALUE;
        }
        showRundreise(fittest[0]);

        Random r = new Random();
        for(generation = 0; generation < 300; generation++) {

            for(int j=0; j <fitGroesse; j++) {
                population[j]=fittest[j];
                rundreiseLaenge[j] = getLaenge(population[j]);
            }
            for(int j=fitGroesse; j<popGroesse; j++) {
                int i1 = r.nextInt(fitGroesse);
                int i2 = r.nextInt(fitGroesse);
                population[j] = mutiere2(kreuze(fittest[i1],fittest[i2]));
                rundreiseLaenge[j] = getLaenge(population[j]);
            }

            fittest = new int[fitGroesse][g.getAnzahlKnoten()+1];
            for(int i=0; i < fitGroesse; i++) {
                int beste = 0;
                for(int j=1; j<popGroesse; j++) {
                    if(rundreiseLaenge[j] < rundreiseLaenge[beste]) {
                        beste = j;
                    }
                }
                fittest[i] = population[beste];
                rundreiseLaenge[beste] = Double.MAX_VALUE;
            }
            showRundreise(fittest[0]);
            step();
        }

        step();
    }

    public int[] erzeugeZufaelligeRundreise(){
        Random r = new Random();
        int[] rundreise = new int[g.getAnzahlKnoten()+1];

        for(int i=0; i< g.getAnzahlKnoten(); i++) rundreise[i] = i;
        for(int i=0; i< 1000; i++) {
            int p1 = r.nextInt(rundreise.length-2)+1;
            int p2 = r.nextInt(rundreise.length-2)+1;
            int d = rundreise[p1];
            rundreise[p1] = rundreise[p2];
            rundreise[p2] = d;
        }
        rundreise[g.getAnzahlKnoten()]=rundreise[0];
        return rundreise;
    }

    public int[] kreuze(int[] rr1, int[] rr2) {
        Random r = new Random();
        int crossover = r.nextInt(rr1.length);
        int[] new_r = Arrays.copyOf(rr1, rr1.length);
        for(int j = 0; j< rr2.length-1; j++) {
            boolean schonEnthalten = false;
            for(int i = 0; i<crossover; i++) {
                if(rr2[j] == new_r[i]) {
                    schonEnthalten=true;
                    break;
                }
            }
            if(!schonEnthalten) {
                new_r[crossover] = rr2[j];
                crossover++;
            }
        }
        rr2[rr2.length-1] = rr2[0];
        return new_r;
    }

    public int[] mutiere(int[] rr) {
        Random r = new Random();
        int anz_mut = r.nextInt(3);
        int[] new_r = Arrays.copyOf(rr, rr.length);
        for(int z =0; z<anz_mut; z++) {
            int pos1 = r.nextInt(rr.length-1);
            int pos2 = r.nextInt(rr.length-1);
            int d = new_r[pos1];
            new_r[pos1] = new_r[pos2];
            new_r[pos2] = d;
        }
        new_r[new_r.length-1] = new_r[0];
        return new_r;
    }

    public int[] mutiere2(int[] rr) {
        Random r = new Random();
        int start= r.nextInt(rr.length-1);
        int laenge = r.nextInt(7);
        int[] new_r = Arrays.copyOf(rr, rr.length);
        for(int i=0; i< laenge; i++) {
            new_r[(start+laenge-1-i)%(rr.length-1)]=rr[(start+i)%(rr.length-1)];
        }
        new_r[new_r.length-1] = new_r[0];
        return new_r;
    }

    public void showRundreise(int[] rundreise) {
        g.initialisiereAlleKanten();
        for(int i=0; i<rundreise.length-1; i++) {
            g.getKante(rundreise[i],rundreise[i+1]).setMarkiert(true);

        }
        info(getInfo());
    }

    public double getLaenge(int[] rundreise) {
        double laenge = 0;   
        for(int i=0; i<rundreise.length-1; i++) {
            laenge += g.getKante(rundreise[i],rundreise[i+1]).getGewicht();
        }
        return laenge;
    }

    public String getInfo() {
        List<Kante> kanten = g.getAlleKanten();
        double laenge = 0;
        int anz =0;
        for(Kante k:kanten) {
            if(k.isMarkiert()) {
                laenge+=k.getGewicht();
                anz++;
            }
        }

        //return ""+generation+";"+laenge;
        return "Bisher beste Weglänge (Generation "+generation+"): "+laenge+" km.";
    }

    // Ende Methoden
}
