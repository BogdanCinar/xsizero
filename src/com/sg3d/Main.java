package com.sg3d;
//x si zero
//0 unde e liber
// 1/2 pentru x si 0

import com.sun.org.glassfish.external.statistics.annotations.Reset;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";     // RED

    public static void main(String[] args) {
        //Declararea tablei de joc, matrice de 3 linii si 3 coloane numere intregi
        int[][] table = new int[3][3];
        int player = 1;

        int noFreeSpot = 9;
        char statusJoc = 'N';
        int[] poz;
        while (statusJoc == 'N' && noFreeSpot != 0) {
            poz = getValues(player, table);
            if (isFreeSpot(poz[0], poz[1], table)) {
                player = insertTurn(poz[0], poz[1], player, table);
                noFreeSpot--;
                showTable(table);
                // statusJoc = validateGame(table);
                statusJoc = validateGame2(table);
            } else {
                System.out.println(RED + "Locul este deja marcat !!??\n\n" + RESET);

            }
        }
        System.out.println("*********************");
        if (statusJoc != 'N') {
            System.out.println("A castigat " + statusJoc);
        } else {
            System.out.println("REMIZA !!!");
        }

    }

    // Implementarea unei metode care primeste ca parametru doua numere (linie + coloana).
    // Metoda verifica daca numerele primite sunt valide pentru a accesa tabla de joc (linie + coloana)
    public static boolean validateRowColumn(int x) {
        return (x >= 0 && x <= 2);
    }

    public static boolean isFreeSpot(int row, int column, int[][] tab) {
        return (tab[row][column] == 0);
    }

    // Implementarea unei metode a afisare a tablei de joc.
    // Metoda primeste ca parametru tabla de joc, parcurge fiecare celula de pe tabla si afiseaza
    // spatiu liber, daca celula retine valoarea 0,
    // caracterul x, daca celula retine valoarea 1
    // caracterul y daca celula retine valoarea 2.

    public static void showTable(int[][] tab) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|");
                switch (tab[i][j]) {
                    case 1:
                        System.out.print("X");
                        break;
                    case 2:
                        System.out.print("0");
                        break;
                    default:
                        System.out.print(" ");
                        break;
                }
                System.out.print(" ");
            }
            System.out.print("|\n");
        }

    }

    public static int insertTurn(int i, int j, int player, int[][] tab) {
        int playerNou;
        if (player == 1) {
            tab[i][j] = 1;
            playerNou = 2;
        } else {
            tab[i][j] = 2;
            playerNou = 1;
        }
        return playerNou;
    }

    public static int[] getValues(int player, int[][] tab) {
        Scanner sc = new Scanner(System.in);
        int[] element = new int[2];

        int i = 0;
        while (i < 2) {
            if (i == 0) {
                System.out.print("Introduceti linia pentru jucatorul " + player + " := ");
            } else {
                System.out.print("Introduceti coloana pentru jucatorul " + player + " := ");
            }
            element[i] = sc.nextInt();
            if (validateRowColumn(element[i])) {
                ++i;
            } else {
                if (i == 0) {
                    System.out.println(RED + "Linia {" + element[0] + "} este invalida" + RESET);

                } else {
                    System.out.println(RED + "Coloana {" + element[1] + "} este invalida" + RESET);
                }
                i = 0;
            }
        }
        return element;
    }

    public static char validateGame(int[][] tab) {
        char status;
        int value;
        //linia 1 sau coloana 1
        if ((tab[0][0] == tab[0][1] && (tab[0][1] == tab[0][2])) ||
                (tab[0][0] == tab[1][0] && (tab[1][0] == tab[2][0]))) {
            value = tab[0][0];
        }//linia 2 sau coloana 2
        else if ((tab[1][0] == tab[1][1] && (tab[1][1] == tab[1][2])) ||
                (tab[0][1] == tab[1][1] && (tab[1][1] == tab[2][1]))) {
            value = tab[1][1];
        }//linia 3 sau coloana 3
        else if ((tab[2][0] == tab[2][1] && (tab[2][1] == tab[2][2])) ||
                (tab[0][2] == tab[1][2] && (tab[1][2] == tab[2][2]))) {
            value = tab[2][2];
        }//diagonala 1 sau diagonala 2
        else if ((tab[0][0] == tab[1][1] && (tab[1][1] == tab[2][2])) ||
                (tab[0][2] == tab[1][1] && (tab[1][1] == tab[2][0]))) {
            value = tab[1][1];
        } else value = 0;

        switch (value) {
            case 1:
                status = 'X';
                break;
            case 2:
                status = '0';
                break;
            default:
                status = 'N';
                break;
        }
        return status;
    }


    public static char validateGame2(int[][] a) {
        char status;
        int pLinie = 1;
        int pColoana = 1;
        int value = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                pLinie = pLinie * a[i][j];
                pColoana = pColoana * a[j][i];
                if (i == 2) {
                    switch (pLinie) {
                        case 1:
                            value = 1;
                            break;
                        case 8:
                            value = 2;
                            break;
                        default:
                            value = 0;
                    }
                    pLinie = 1;
                }
            }
        }
        switch (value) {
            case 1:
                status = 'X';
                break;
            case 2:
                status = '0';
                break;
            default:
                status = 'N';
                break;
        }
        return status;
    }
}
