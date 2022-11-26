package com.mycompany.simulacion;

public class num_ps {

    public static void main(String[] args) {
    }

    public double Congruencial() {
        int fin;
        float i = 0;
        fin = 1;
        int a, X, c, m;
        a = 16807;
        c = 0;
        X = (int)(Math.random()*100);
        m = 2147483647;
        int r = 0;
            r = this.generaCongruencial(a, X, c, m);
            X = r;
            int na;
            float naf;
            if (fin > 1) {
                na = X % fin;
                i = na;
            } else {
                na = X % 100000;
                naf = (float) na / 100000;
                i = naf;
            }
        return i;
    }

    private Integer generaCongruencial(int a, int X, int c, int m) {
        return Math.abs((a * X + c) % m);
    }
    
}