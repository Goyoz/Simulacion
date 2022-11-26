package com.mycompany.simulacion;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        num_ps obj = new num_ps();
        String NC = JOptionPane.showInputDialog(null, "Proporciona el numero de clientes", "Simulacion de un cajero de banco", 3);
        int tpa = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el tiempo de atención"));
        int tpl = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el tiempo de llegada para los clientes"));
        String guion = "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String aster = "******************************************************************************************************************************************************************************************************************\n";
        String cad = "Clientes\tTiempo entre\t\tTiempo de\tTiempo de servicio\tInicio del\tTiempo que termina\tTiempo en\tOciosidad\tTiempo en\n";
        cad = cad + "\tllegadas 1-"+tpl+"m\t\tllegada\tservicio 1-"+tpa+"m\t\tservicio\tel servicio\t\tel sistema\tdel Sistema\tespera\n";
        cad = cad + aster;
        int nc = Integer.parseInt(NC);
        int[][] r = new int[nc][9];
        cad = cad + (r[0][0] = 1) + "\t";
        cad = cad + "-\t\t";
        r[0][1] = 0;
        cad = cad + (r[0][2] = 0) + "\t";
        
        do
        {
            r[0][3] = tpa;
        } while (r[0][3] > tpa || r[0][3] < 1);//aqui se definen los tiempos en ser atendidos cada cliente      
        cad = cad + (r[0][3]) + "\t\t";
        cad = cad + (r[0][4] = 0) + "\t";
        cad = cad + (r[0][5] = r[0][3]) + "\t\t";
        cad = cad + (r[0][6] = r[0][3]) + "\t";
        cad = cad + (r[0][7] = 0) + "\t";
        cad = cad + (r[0][8] = 0) + "\t\n" + guion;
        for (int x = 1; x < nc; x++)
        {
            cad = cad + (r[x][0] = x + 1) + "\t";
            do
            {
                r[x][1] = tpl;
            } while (r[x][1] > tpl || r[x][1] < 1);//aqui se definen los tiempos entre las llegadas de cada cliente
            cad = cad + r[x][1] + "\t\t";
            cad = cad + (r[x][2] = r[x][1] + r[x - 1][2]) + "\t";
            do
            {
                r[x][3] = (int) Math.round(obj.Congruencial()*10);
            } while (r[x][3] > 6 || r[x][3] < 1);
            cad = cad + r[x][3] + "\t\t";
            if (r[x][2] < r[x - 1][5])
            {
                r[x][4] = r[x - 1][5];
            } else
            {
                r[x][4] = r[x][2];
            }
            cad = cad + r[x][4] + "\t";
            cad = cad + (r[x][5] = r[x][4] + r[x][3]) + "\t\t";
            r[x][8] = r[x][4] - r[x][2];
            cad = cad + (r[x][6] = r[x][3] + r[x][8]) + "\t";
            cad = cad + (r[x][7] = r[x][4] - r[x - 1][5]) + "\t";
            cad = cad + r[x][8] + "\n";//+guion;
        }
        int ti_en_sis = 0;
        for (int x = 0; x < nc; x++)
        {
            ti_en_sis = ti_en_sis + r[x][6];
        }
        double ti_pro_en_sis = (double) ti_en_sis / nc;
        cad = cad + "Tiempo promedio en el sistema: " + ti_pro_en_sis + "\n";
        int ti_ocioso = 0;
        for (int x = 0; x < nc; x++)
        {
            ti_ocioso = ti_ocioso + r[x][7];
        }
        double porcentaje_ocioso = (double) ti_ocioso / r[nc - 1][5] * 100;
        cad = cad + "% de Tiempo ocioso: " + porcentaje_ocioso + "%\n";
        int ti_espera = 0;
        for (int x = 0; x < nc; x++)
        {
            ti_espera = ti_espera + r[x][8];
        }
        double ti_prom_esp_cli = (double) ti_espera / nc;
        cad = cad + "Tiempo promedio de espera por cliente: " + ti_prom_esp_cli + "\n";

        JOptionPane.showMessageDialog(null, new JTextArea(cad), "RESULTADO", 1);
    }
}