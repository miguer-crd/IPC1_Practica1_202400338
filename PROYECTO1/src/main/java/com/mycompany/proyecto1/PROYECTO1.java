package com.mycompany.proyecto1;

import java.util.Scanner;
import java.util.Random;

public class PROYECTO1 {

    private static final int FILAS_PEQUENO = 5;
    private static final int COLUMNAS_PEQUENO = 6;

    private static final String fantasma = "@";
    private static final String premio = "0";
    private static final String premio_especial = "$";
    private static final String pared = "X";
    private static final String pacman = "<";

    private static final int PUNTOS_PREMIO = 10;
    private static final int PUNTOS_PREMIO_ESPECIAL = 15;

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    // ===== HISTORIAL =====
    private static String[] nombres = new String[100]; // hasta 100 partidas
    private static int[] puntos = new int[100];
    private static int contadorPartidas = 0;

    // ================= MAIN =================
    public static void main(String [] args){

        System.out.println("---------------------------------------");
        System.out.println("        | BIENVENIDO A PAC-MAN |       ");
        System.out.println("---------------------------------------");
        System.out.println("      ");
        int opcionInicio;

        System.out.println(" - ELIJA UNA OPCION: ");
        System.out.println("    1. CREAR UN TABLERO");
        System.out.println("    2. VER PUNTUACION");
        System.out.println("    3. SALIR");
        System.out.println(" --------------------------------------");

        opcionInicio = sc.nextInt();
        opcionElegida(opcionInicio);

        System.out.println("Gracias por jugar PAC-MAN ;P ");
    }

    // ================= MENU =================
    public static void opcionElegida(int opcionInicio){

        int numeroPremios, numeroParedes, numeroTrampas;

        switch (opcionInicio) {

            case 1:

                int puntaje = 0;
                int vidas = 3;

                System.out.println("  ");
                System.out.println("---------------------------------------");
                System.out.println("        | DATOS DEL TABLERO|       ");
                System.out.println("---------------------------------------");
                System.out.println("INGRESE LOS SIGUIENTES VALORES:");
                System.out.println("      ");
                System.out.print(" -NOMBRE DE USUARIO: ");
                String nombreUsuario = sc.next();
                numeroPremios = asignarCantidades("PREMIOS", 0.4);
                numeroParedes = asignarCantidades("PAREDES", 0.2);
                numeroTrampas = asignarCantidades("FANTASMA", 0.2);

                String[][] tablero = new String[FILAS_PEQUENO][COLUMNAS_PEQUENO];

                for(int i = 0; i < FILAS_PEQUENO; i++){
                    for(int j = 0; j < COLUMNAS_PEQUENO; j++){
                        tablero[i][j] = " ";
                    }
                }

                colocarElemento(tablero, premio, numeroPremios);
                colocarElemento(tablero, premio_especial, 1);
                colocarElemento(tablero, pared, numeroParedes);
                colocarElemento(tablero, fantasma, numeroTrampas);

                imprimirTablero(tablero);

                int personajeFila, personajeColumna;

                do{
                    System.out.println("-----------------------------------------");
                    System.out.println("  | ESCOJA DONDE COLOCAR AL PERSONAJE |   ");
                    System.out.println("-----------------------------------------");
                    System.out.print(" -FILA: ");
                    personajeFila = sc.nextInt();
                    System.out.print(" -COLUMNA: ");
                    personajeColumna = sc.nextInt();

                    if(!verificacionPosicionPersonaje(personajeFila, personajeColumna, tablero)){
                        System.out.println("POSICIÓN NO VALIDA, ESCOJA OTRO LUGAR");
                    }

                }while(!verificacionPosicionPersonaje(personajeFila, personajeColumna, tablero));

                tablero[personajeFila-1][personajeColumna-1] = pacman;
                imprimirTablero(tablero);

                int[] posicion = {personajeFila-1, personajeColumna-1};

                boolean jugando = true;

                while(jugando){

                    System.out.println("  ");
                    System.out.println("----------------------------------------------------------");
                    System.out.println("| JUGADOR: |"+ nombreUsuario + "     | PUNTEO: |" + puntaje + "      | VIDAS: |" + vidas);
                    System.out.println("----------------------------------------------------------");
                    System.out.println("8. ARRIBA");
                    System.out.println("5. ABAJO");
                    System.out.println("6. DERECHA");
                    System.out.println("4. IZQUIERDA");
                    System.out.println("F. PAUSA");
                    System.out.print(">");

                    String movimiento = sc.next();

                    if(movimiento.equalsIgnoreCase("F")){

                        System.out.println(" ----- JUEGO EN PAUSA -----");
                        System.out.println("3. REGRESAR");
                        System.out.println("4. TERMINAR PARTIDA");
                        System.out.print(">");

                        int pausa = sc.nextInt();

                        if(pausa == 4){
                            nombres[contadorPartidas] = nombreUsuario;
                            puntos[contadorPartidas] = puntaje;
                            contadorPartidas++;
                            System.out.println("REGRESANDO AL MENU PRINCIPAL...");
                            main(null);
                            return;
                        }

                    } else {

                        int resultado = moverPersonaje(tablero, posicion, movimiento);

                        if(resultado == 1){
                            puntaje += PUNTOS_PREMIO;
                        }
                        if(resultado == 2){
                            puntaje += PUNTOS_PREMIO_ESPECIAL;
                        }
                        if(resultado == 3){
                            vidas--;
                            if(vidas <= 0){
                                System.out.println("GAME OVER");
                                nombres[contadorPartidas] = nombreUsuario;
                                puntos[contadorPartidas] = puntaje;
                                contadorPartidas++;
                                System.out.println("REGRESANDO AL MENU PRINCIPAL...");
                                main(null);
                                return;
                            }
                        }

                        imprimirTablero(tablero);

                        if(!quedanPremios(tablero)){
                            System.out.println("FELICIDADES! HAS RECOGIDO TODOS LOS PREMIOS!");
                            nombres[contadorPartidas] = nombreUsuario;
                            puntos[contadorPartidas] = puntaje;
                            contadorPartidas++;
                            System.out.println("REGRESANDO AL MENU PRINCIPAL...");
                            main(null);
                            return;
                        }
                    }
                }

                break;

            case 2:

                System.out.println(" ------ HISTORIAL DE PARTIDAS ------ ");

                if(contadorPartidas == 0){
                    System.out.println("NO HAY PARTIDAS GENERADAS");
                } else {
                    for(int i = contadorPartidas-1; i >= 0; i--){
                        System.out.println("|  JUGADOR: |" + nombres[i] + " |  PUNTOS: |" + puntos[i] + "|");
                    }
                }

                System.out.println("REGRESANDO AL MENU PRINCIPAL...");
                main(null);
                return;

            case 3:
                System.out.println("SALIENDO...");
                return;

            default:
                System.out.println("OPCION INVALIDA");
                main(null);
        }
    }

    // ================= METODOS =================

    public static int asignarCantidades(String objeto, double porcentaje){
        int cantidad;
        int max = (int)(FILAS_PEQUENO * COLUMNAS_PEQUENO * porcentaje);

        do{
            System.out.print(" -ELIJA LA CANTIDAD DE " + objeto + ": ");
            cantidad = sc.nextInt();
        }while(cantidad < 1 || cantidad > max);

        return cantidad;
    }

    public static void colocarElemento(String[][] tablero, String simbolo, int cantidad){
        int colocados = 0;
        while(colocados < cantidad){
            int fila = rand.nextInt(FILAS_PEQUENO);
            int columna = rand.nextInt(COLUMNAS_PEQUENO);

            if(tablero[fila][columna].equals(" ")){
                tablero[fila][columna] = simbolo;
                colocados++;
            }
        }
    }

    public static boolean verificacionPosicionPersonaje(int x, int y, String[][] tablero){
        return tablero[x-1][y-1].equals(" ");
    }

    public static void imprimirTablero(String[][] tablero){

        for(int j=0;j<COLUMNAS_PEQUENO+2;j++) System.out.print("- ");
        System.out.println();

        for(int i=0;i<FILAS_PEQUENO;i++){
            System.out.print("|");
            for(int j=0;j<COLUMNAS_PEQUENO;j++){
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println("|");
        }

        for(int j=0;j<COLUMNAS_PEQUENO+2;j++) System.out.print("- ");
        System.out.println();
    }

    public static boolean quedanPremios(String[][] tablero){
        for(int i=0;i<FILAS_PEQUENO;i++){
            for(int j=0;j<COLUMNAS_PEQUENO;j++){
                if(tablero[i][j].equals(premio) || tablero[i][j].equals(premio_especial)){
                    return true;
                }
            }
        }
        return false;
    }

    public static int moverPersonaje(String[][] tablero, int[] posicion, String direccion){

        int fila = posicion[0];
        int columna = posicion[1];

        switch(direccion){
            case "8": fila--; break;
            case "5": fila++; break;
            case "6": columna++; break;
            case "4": columna--; break;
            default: return 0;
        }

        if(fila < 0) fila = FILAS_PEQUENO-1;
        if(fila >= FILAS_PEQUENO) fila = 0;
        if(columna < 0) columna = COLUMNAS_PEQUENO-1;
        if(columna >= COLUMNAS_PEQUENO) columna = 0;

        if(tablero[fila][columna].equals(pared)){
            System.out.println("CHOQUE CONTRA PARED");
            return 0;
        }

        if(tablero[fila][columna].equals(premio)){
            System.out.println("PREMIO RECOGIDO!");
            tablero[posicion[0]][posicion[1]] = " ";
            tablero[fila][columna] = pacman;
            posicion[0] = fila;
            posicion[1] = columna;
            return 1;
        }

        if(tablero[fila][columna].equals(premio_especial)){
            System.out.println("PREMIO ESPECIAL RECOGIDO!");
            tablero[posicion[0]][posicion[1]] = " ";
            tablero[fila][columna] = pacman;
            posicion[0] = fila;
            posicion[1] = columna;
            return 2;
        }

        if(tablero[fila][columna].equals(fantasma)){
            System.out.println("TE ATRAPO UN FANTASMA!");
            tablero[posicion[0]][posicion[1]] = " ";
            tablero[fila][columna] = pacman;
            posicion[0] = fila;
            posicion[1] = columna;
            return 3;
        }

        tablero[posicion[0]][posicion[1]] = " ";
        tablero[fila][columna] = pacman;
        posicion[0] = fila;
        posicion[1] = columna;
        return 0;
    }
}