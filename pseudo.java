package com.mycompany.simulacion;

import javax.swing.JOptionPane;

public class pseudo extends javax.swing.JFrame{

    public static void main(String[] args) {
        pseudo obj1 = new pseudo();
        obj1.Congruencial();
    }
    
    public void Congruencial(){
        
        int fin, N , a, X, c, m, nSub, ConP = 0, ConT = 0, Con4 = 0; //variables para contar PARES y TERCIAS y CUATROS;
        JOptionPane.showMessageDialog(null, "Generador de numeros pseudoaleatorios metodo congruencial lineal, bajo la formula \n Xn = (AXn-1+C) mod M");
        fin = Integer.parseInt(JOptionPane.showInputDialog(null, "Los números serán generados entre 0 y ... ", 1));
        N = Integer.parseInt(JOptionPane.showInputDialog(null, "Proporciona el numero de veces que se genera un número aleatorio. \n n=", 20));
        a = Integer.parseInt(JOptionPane.showInputDialog(null, "Proporciona el valor para A (Multiplicador)",16807));
        c = Integer.parseInt(JOptionPane.showInputDialog(null, "Proporciona el valor para C (Incremento)", 0));
        X = Integer.parseInt(JOptionPane.showInputDialog(null, "Proporciona el valor para Xo (Semilla)"));
        m = Integer.parseInt(JOptionPane.showInputDialog(null, "Proporciona el valor para M (Módulo)", 2147483647));
        nSub = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el numero de sub-intervalos entre 5 y 10", 5)); //pide los sub-intervalos
        int nums[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //arreglo definido de los numeros que pueden salir en la prueba de independencia
        int numC[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //arreglo para contar los numeros que van saliendo
        //creacion de eventos en un arreglo con sus respectivos valores establecidos para la prueba de independencia
        //               [0] TodosDiferenctes  [1] UnPar   [2] DosPares  [3] Tercia       [4] Full          [5] Poker     [6] Quintilla
        float Eventos[] = {(float)0.3024, (float)0.504, (float)0.108, (float)0.072, (float)0.009, (float)0.0045, (float)0.0001};
        float FEc[] = new float[Eventos.length]; //arreglo de la frecuencia esperada para la independencia
        float FOc[] = new float[Eventos.length]; //arreglo de la frecuencia observada para la independencia
        float X0c = 0; //valor para comparar en la tabla chi cuadrada en cuanto a la independencia
        float X0cc[] = new float[Eventos.length]; //arreglo para sumar chi cuadrada3
        String fc; //string para la cadena del flotante
        
        for (int i = 0; i < Eventos.length; i++) { //frecuencia esperada de la independencia
            FEc[i] = Eventos[i] * N;
        }
        
        float mid = (float)fin / 2; //saca la mitad de el valor maximo de los numeros generados
        int BI[] = new int[N]; //arreglo para los binarios
        float lonSub = (float)fin / nSub; //divide el valor maximo entre los sub-intervalos
        float lonSubAux = (float)lonSub; //toma el valor de los sub-intervalos para auxiliar
        String cad = "Números Pseudoaleatorios\n";
        String cad2= "Números Pseudoaleatorios\n";
        int FE = N / nSub; //saca la frecuencia esperada para la uniformidad
        int FEa[] = new int[nSub]; //arreglo de las frecuencias esperadas
        
        for (int j = 0; j < nSub; j++) { //ciclo para llenar el arreglo de las FE
            FEa[j] = FE;
        }
        
        int FOa[] = new int[nSub]; //arreglo de las frecuencias obtenidas
        int r = 0, Pos = 10; //variable para poder mostrar todos los numeros en una sola ventana
        int gradosL = nSub - 1; //calculo de los grados de libertad
        float[] ranS = new float[nSub];
        for (int f = 0; f < nSub; f++) { //ciclo de asignación del rango de los sub-intervalos
            ranS[f] = (float) lonSub;
            lonSub = lonSub + lonSubAux;
        }
        
        for (int z = 0; z < N ; z++) {
            r = this.generaCongruencial(a, X, c, m);
            X = r;
            int na;
            float naf;
            if (fin > 1) {
                na= X % fin;
                if (na <= mid) { //ciclo para asignar un 0 o 1 segun si el número es mayor o menor a la mitad
                    BI[z] = 0;
                } else {
                    BI[z] = 1;
                }
                for (int g = 0; g < nSub; g++) { //ciclo para la frecuencia observada
                    if (g == 0) {
                        if (na >= 0 && na <= ranS[g]) { //ciclo para evaluar si el numero esta en el intervalo (inicia en 0 hasta el primer intervalo)
                            FOa[g] = FOa[g] + 1;
                        }
                    } else {
                        if (na > ranS[g-1] && na <= ranS[g]) { //evaluar si el numero se encuentra en ese intervalo
                            FOa[g] = FOa[g] + 1;
                        }
                    }
                }
                if (z == Pos) { //ciclo para pasar de renglon en el JOptionPane
                    cad = cad + "[" + z + "] " + na + "\n";
                    cad2 = cad2 + "[" + z + "] " + na + "\n";
                    Pos = Pos + 10;
                } else {
                    cad = cad + "[" + z + "] "+ na + "    ";
                    cad2 = cad2 + "[" + z + "] "+ na + "\t";
                }
            }
            else {
                for (int i = 0; i < 10; i++) { //reinicio de el contador para los numeros encontrados para la prueba de independencia
                    numC[i] = 0;
                }
                ConP = 0; ConT = 0; Con4 = 0;
                na=X%100000;
                naf=(float)na/100000;
                if (naf <= mid) { //ciclo para asignar un 0 o 1 segun si el número es mayor o menor a la mitad
                    BI[z] = 0;
                } else {
                    BI[z] = 1;
                }
                fc = Float.toString(naf); //conversor de float a string para poder hacer subcadenas
        for (int i = 0; i < fc.length(); i++) { //ciclo para contar los numeros que van saliendo
            if (i == 7) { //limite para que sean 5 despues del punto
                break;
            } else if (i != 0 && i != 1) { //limite para que incie despues del 0 y punto
                for (int j = 0; j < 10; j++) {
                    if (Integer.parseInt(fc.substring(i, i + 1)) == nums[j]) { //conversor del numero evaluado de cadena a int
                        numC[j] = numC[j] + 1;
                    }
                }
            }
        }
        for (int i = 0; i <= 10; i++) { //arreglo para cuando los valores 5 valores son todos diferentes
            if (i == 10) { //si el ciclo llego a su ultima ejecución entonces son todos diferentes, añade 1 a el arreglo y rompe el ciclo
                FOc[0] = FOc[0] + 1;
                break;
            }
            if (numC[i] == 1 || numC[i] == 0) { //si los valores son 0 o 1 entonces no hay mas de 2 en ninguna posición
            } else {
                break;
            }
        }
        for (int i = 0; i < 10; i++) { //contador para tercias
            if (numC[i] == 3) {
                ConT = ConT + 1;
            }
        }
        for (int i = 0; i < 10; i++) { //contador para pokers
            if (numC[i] == 4) {
                Con4 = Con4 + 1;
            }
        }
        OUTER:
        for (int i = 0; i <= 10; i++) { //for para los pares y las tercias(en caso de un FULL)
            if (i == 10) { //al llegar a la ultima ejecución del ciclo, se determina si hay un FULL, PAR, 2 PARES o una TERCIA
                switch (ConP) {
                    case 1:
                        if (ConT == 1) {
                            FOc[4] = FOc[4] + 1;
                            break OUTER;
                        } else {
                            FOc[1] = FOc[1] + 1;
                            break OUTER;
                        }
                    case 2:
                        FOc[2] = FOc[2] + 1;
                        break OUTER;
                    default:
                        if (ConT == 1) {
                            FOc[3] = FOc[3] + 1;
                        }
                        break OUTER;
                }
            }
            if (numC[i] == 2) { //si el numero es 2 entonces el contador de pares aumenta
                ConP = ConP + 1;
            }
        }
        for (int i = 0; i <= 10; i++) { //for para los POKER y QUINTILLA
            if (i == 10) {
                if (ConT == 1) { //si hay una tercia entoces no cuenta para un poker
                    break;
                } else if (Con4 == 1) { //si no hay tercias y encuentra un valor repetido 4 veces, entonces hay un poker
                    FOc[5] = FOc[5] + 1;
                    break;
                } else {
                    break;
                    
                }
            }
            if (numC[i] == 5) { //si encuentra un 5 entonces todos los numeros son iguales, si es el caso entonces los demas valores son 0
                FOc[6] = FOc[6] + 1;
            }
        }
                for (int g = 0; g < nSub; g++) { //ciclo para la frecuencia observada
                    if (g == 0) {
                        if (naf >= 0 && naf <= ranS[g]) { //ciclo para evaluar si el numero esta en el intervalo (inicia en 0 hasta el primer intervalo)
                            FOa[g] = FOa[g] + 1;
                        }
                    } else {
                        if (naf > ranS[g-1] && naf <= ranS[g]) { //evaluar si el numero se encuentra en ese intervalo
                            FOa[g] = FOa[g] + 1;
                        }
                    }
                }
                if (z == Pos) { //ciclo para pasar de renglon en el JOptionPane
                    cad = cad + "[" + z + "] " + naf + "\n";
                    cad2 = cad2 + "[" + z + "] "+ naf + "\n";
                    Pos = Pos + 10;
                } else {
                    cad = cad + "[" + z + "] " + naf + "    ";
                    cad2 = cad2 + "[" + z + "] " + naf + "\t";
                }
            }
        }
        
        int cC = 0, cU = 0, mayL = 0, contador = 0;
        
        for (int in = 0; in < N; in++) { //ciclo para contar la mayor longitud de corrida
            switch (BI[in]) {
                case 0:
                    cC++;
                    cU = 0;
                    break;
                case 1:
                    cU++;
                    cC = 0;
                    break;
            }
            if (cC > mayL) {
                mayL = cC;
            } else if (cU > mayL){
                mayL = cU;
            }
        }
        
        int longC[] = new int[mayL]; //arreglo para longitud de corrida
        float FEb[] = new float[mayL]; //arreglo para la frecuencia esperada de alatoriedad
        int FOb[] = new int[mayL]; //arreglo para la frecuencia observada de alatoriedad
        
        for (int in = 0; in < N; in++) { //ciclo para la frecuencia observada de los ceros
            if (BI[in] == 1) { //evalua si el binario es 1 (coincide), entonces el contador aumenta
                contador++;
            } else { //si el numero ya no coincide, entonce agrega uno a la frecuencia observada [en el lugar de la longitud del contador]
                if (contador != 0) {
                    FOb[contador - 1] = FOb[contador - 1] + 1;
                    contador = 0;
                }
            }
            if (in == N - 1 && contador != 0) { //al ser el final del ciclo agrega ya el valor (si no conto nada entonces no)
                FOb[contador - 1] = FOb[contador - 1] + 1;
            }
        }
        
        contador = 0; //reinicio del contador
        
        for (int in = 0; in < N; in++) { //ciclo para la frecuencia observada de los unos
            if (BI[in] == 0) { //evalua si el binario es 0 (coindice), entonces el contador aumenta
                contador++;
            } else { //si el numero ya no coincide, entonce agrega uno a la frecuencia observada [en el lugar de la longitud del contador]
                if (contador != 0) {
                    FOb[contador - 1] = FOb[contador - 1] + 1;
                    contador = 0;
                }
            }
            if (in == N - 1 && contador != 0) { //al ser el final del ciclo agrega ya el valor (si no conto nada entonces no)
                FOb[contador - 1] = FOb[contador - 1] + 1;
            }
        }
        
        for (int ic = 0; ic < mayL; ic++) { //ciclo para asignar la longitud de corrida (como el ciclo inicia en 0, se auemnta 1, para ir acorde)
            longC[ic] = ic + 1;
        }
        
        for (int ic = 0; ic < mayL; ic++) { //ciclo para calcular la frecuencia esperada de la alatoriedad
            FEb[ic] = (N - longC[ic] + 3) / (float)Math.pow(2, (longC[ic] + 1));
        }
        
        float X0 = 0, X0b = 0; //valor para comparar en la tabla chi cuadrada en cuanto a la uniformidad y aleatoriedad
        float X0a[] = new float[nSub]; //arreglo para sumar chi cuadrada
        float X0bb[] = new float[mayL]; //arreglo para chi cuadrada2
        
        for (int i = 0; i < mayL; i++) { //ciclo de la formula para la alatoriedad
            X0bb[i] = (float)(FEb[i] - FOb[i]) * (FEb[i] - FOb[i]) / FEb[i];
            X0b = X0b + X0bb[i];
        }
        
        for (int i = 0; i < nSub; i++) { //ciclo de la formula para la uniformidad
            X0a[i] = (float)(FE - FOa[i]) * (FE - FOa[i]) / FE;
            X0 = X0 + X0a[i];
    }
        for (int i = 0; i < Eventos.length; i++) { //ciclo de la formula para la independencia
            X0cc[i] = (float)(FEc[i] - FOc[i]) * (FEc[i] - FOc[i]) / FEc[i];
            X0c = X0c + X0cc[i];
    }
        
        JOptionPane.showMessageDialog(null, cad);
        float Val = 0;
        
        switch(gradosL){ //switch para el resultado de la uniformidad en cuanto a los grados de libertad
            case 4:
                if (X0 < 9.4877) {
                    Val = (float)9.4877;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 9.4877, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)9.4877;
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 9.4877, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
            case 5:
                if (X0 < 11.0705) {
                    Val = (float)11.0705;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 11.0705, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)11.0705;
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 11.0705, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
            case 6:
                if (X0 < 12.5916) {
                    Val = (float)12.5916;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 12.5916, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)12.5916;
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 12.5916, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
            case 7:
                if (X0 < 14.0671) {
                    Val = (float)14.0671;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 14.0671, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)14.0671;
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 14.0671, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
            case 8:
                if (X0 < 15.5073) {
                    Val = (float)15.5073;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 15.5073, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)15.5073;
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 15.5073, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
            case 9:
                if (X0 <  16.9190) {
                    Val = (float)16.9190;
                    JOptionPane.showMessageDialog(null, X0 + " Es menor que 16.9190, entonces no se puede rechazar la uniformidad de los números aleatorios", "PRUEBA DE UNIFORMIDAD", 1);
                } else {
                    Val = (float)16.9190;
                    
                    JOptionPane.showMessageDialog(null, X0 + " Es mayor que 16.9190, entonces los números aleatorios no tienen uniformidad", "PRUEBA DE UNIFORMIDAD", 1);
                }
                break;
        }
        
        if (X0b < 3.8) { //comparación para ver si los numeros tiene alatoriedad
            JOptionPane.showMessageDialog(null, X0b + " Es menor que 3.8, entonces no se puede rechazar la aleatoriedad de los números", "PRUEBA DE ALATORIEDAD", 1);
        } else {
            JOptionPane.showMessageDialog(null, X0b + " Es mayor que 3.8, entonces los números no tiene aleatoriedad", "PRUEBA DE ALATORIEDAD", 1);
        }
        
        if (X0c < 12.59) { //comparación para ver si los numeros tienen independencia
            JOptionPane.showMessageDialog(null, X0c + " Es menor que 12.59, entoces los números son estadísticamente independientes", "PRUEBA DE INDEPENDENCIA", 1);
        } else {
            JOptionPane.showMessageDialog(null, X0c + " Es mayor que 12.59, entonces los números no son independientes", "PRUEBA DE INDEPENDENCIA", 1);
        }
        //
        System.out.println(cad2);
        System.out.println("");
        System.out.println("\t\t\tUNIFORMIDAD\nINTERVALO\t|\tFE\t|\tFO\t|\tX0\n----------------------------------------------------------------");
        for (int ic = 0; ic < nSub; ic++) {
            System.out.println(ranS[ic] + "\t\t|\t" + FEa[ic] + "\t|\t" + FOa[ic] +  "\t|\t" + X0a[ic] + "\n----------------------------------------------------------------");
        }
        System.out.println("X0 = " + X0 + "\nEl valor en tablas de la distribución Chi cuadrada es X0 = " + Val);
        System.out.println("");
        System.out.println("\t\t\tALATORIEDAD\nC\t|\tFE\t|\tFO\t|\tX0\n----------------------------------------------------------------");
        for (int ic = 0; ic < mayL; ic++) {
            System.out.println(longC[ic] + "\t|\t" + FEb[ic] + "\t|\t" + FOb[ic] + "\t|\t" + X0bb[ic] + "\n----------------------------------------------------------------");
        }
        System.out.println("X0 = " + X0b + "\nEl valor en tablas de la distribución Chi cuadrada es X0 = " + 3.8);
        System.out.println("");
        System.out.println("\t\t\tINDEPENDENCIA\nFE\t\t\t|\tFO\t|\tX0\n----------------------------------------------------------------");
        for (int i = 0; i < Eventos.length; i++) {
            System.out.println((double)FEc[i] + "\t|\t" + FOc[i] + "\t|\t" + X0cc[i] + "\n----------------------------------------------------------------");
        }
        System.out.println("X0 = " + X0c + "\nEl valor en tablas de la distribución Chi cuadrada es X0 = " + 12.59);
        //
    }
    
    private Integer generaCongruencial(int a,int X,int c, int m){
        return Math.abs((a*X+c)%m);
    }
    
}