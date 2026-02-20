package com.mycompany.proyecto1;
import java.util.Scanner;
import java.util.Random;

public class PROYECTO1 {
   private static final int FILAS_PEQUENO = 5;
    private static final int COLUMNAS_PEQUENO =6;
    private static final int FILAS_GRANDE = 10;
    private static final int COLUMNAS_GRANDE = 10;
    
    private static final String fantasma = "@";
    private static final String premio = "0";
    private static final String premio_especial = "$";
    private static final String pared = "X";
    private static final String pacman = "<";
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    
    public static void main(String [] args){
        
        System.out.println("----------- BIENVENIDO A PAC-MAN ----------");
        System.out.println("      ");
        int opcionInicio;
    
        System.out.println(" - ELIJA UNA OPCION: ");
        System.out.println("    1. CREAR UN TABLERO");
        System.out.println("    2. VER PUNTUACION");
        System.out.println("    3. SALIR");
        System.out.println(" -------------------------");
        
        opcionInicio = sc.nextInt();
        opcionElegida(opcionInicio);

        System.out.println("Gracias por jugar PAC-MAN ;P ");
        
    }
    
    public static void opcionElegida(int opcionInicio){
        
            int numeroPremios;
            int numeroParedes;
            int numeroTrampas;
        
        switch (opcionInicio) {
                case 1:
                    System.out.println("  ");
                    System.out.println("INGRESE LOS SIGUIENTES VALORES");
                    
                    //Asigna premios
                    numeroPremios = asignarCantidades("PREMIOS", 0.4);
                    
                    //Asigna paredes
                    numeroParedes = asignarCantidades("PAREDES",0.2);
                    
                    //Asigna trampas
                    numeroTrampas = asignarCantidades("FANTASMA",0.4);
                    
                    System.out.println("    ");
                    System.out.println("CREANDO TABLERO NUEVO...");
                    
                    String[][] tablero = new String[FILAS_PEQUENO][COLUMNAS_PEQUENO];
                    
                    //Llenando de espacios la matriz
                    for(int i = 0; i < FILAS_PEQUENO; i++){
                        for(int j = 0; j < COLUMNAS_PEQUENO; j++){
                            tablero[i][j]= " ";
                        }
                    }
                    
                    //Poniendo premios en la matriz
                    colocarElemento(tablero, premio,numeroPremios);
                    
                    //Poniendo las paredes en la matriz
                    colocarElemento(tablero, pared, numeroParedes);
                    
                    //Poniendo fantasmas
                    colocarElemento(tablero,fantasma,numeroTrampas);
                    
                    System.out.println("TABLERO DE: "+FILAS_PEQUENO+"X"+COLUMNAS_PEQUENO+" CREADO!");
                    
                    imprimirTablero(tablero);
                    System.out.println();
                    
                    int personajeFila;
                    int personajeColumna;
                    
                    do{
                        System.out.println("  ");
                        System.out.println("ESCOJA DONDE COLOCAR AL PERSONAJE:");
                        System.out.print("FILA: ");
                        personajeFila = sc.nextInt();
                        System.out.print("COLUMNA: ");
                        personajeColumna = sc.nextInt();
                        if(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero) == false){
                            System.out.println("POSICIÓN NO VALIDA, ESCOJA OTRO LUGAR");
                        }
                    }while(verificacionPosicionPersonaje(personajeFila,personajeColumna,tablero) == false);
                    
                    
                    System.out.println("Personaje colocado exitosamente!");
                    tablero[personajeFila-1][personajeColumna-1] = "<";
                    imprimirTablero(tablero);
                    int[] posicion = { personajeFila -1, personajeColumna -1 };
                    
                    String movimientoPersonaje;
                    boolean jugando = true;

                    while(jugando){

                        System.out.println("Mueve tu personaje");
                        System.out.println("8. Arriba");
                        System.out.println("5. Abajo");
                        System.out.println("6. Derecha");
                        System.out.println("4. Izquierda");
                        System.out.println("F. Pausa");
                        System.out.print(">");

                        movimientoPersonaje = sc.next();

                        if(movimientoPersonaje.equalsIgnoreCase("F")){

                            System.out.println("=== JUEGO EN PAUSA ===");
                            System.out.println("3. Regresar");
                            System.out.println("4. Terminar partida");
                            System.out.print(">");

                            int opcionPausa = sc.nextInt();

                            if(opcionPausa == 3){
                                System.out.println("Regresando a la partida...");
                            }
                            else if(opcionPausa == 4){
                                System.out.println("Terminando partida...");
                                jugando = false;  
                            }
                            else{
                                System.out.println("Opción inválida");
                            }

                        } else {

                            moverPersonaje(tablero,posicion,movimientoPersonaje);
                            imprimirTablero(tablero);

                        }
                    }

                    break;
                case 2:
                    //TODO: IMPLEMENTAR SECCION DE PUNTOS
                    break;
                case 3:
                    // TODO: IMPLEMENTAR SALIR
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo");
        }
        
    }
    
    public static boolean moverPersonaje(String[][] tablero, int[] posicion, String direccion){
        int filaActual = posicion[0];
        int columnaActual = posicion[1];
        
        int nuevaFila = filaActual;
        int nuevaColumna = columnaActual;
        
        switch(direccion){
            case "8": //Arriba
                nuevaFila--;
                //nuevaFila = nuevaFila-1;
                break;
            case "6": //Derecha
                nuevaColumna++;
                break;
            case "4"://Izquiera
               nuevaColumna--;
               break;
            case "5": //Abajo
               nuevaFila++;
               break;
            default:
                System.out.println("Movimiento no válido");
                return false;
        }
        
        // Movimiento entre los bordes
        if(nuevaFila < 0){
            nuevaFila = FILAS_PEQUENO - 1;
        }
        if(nuevaFila >= FILAS_PEQUENO){
            nuevaFila = 0;
        }

        if(nuevaColumna < 0){
            nuevaColumna = COLUMNAS_PEQUENO - 1;
        }
        if(nuevaColumna >= COLUMNAS_PEQUENO){
            nuevaColumna = 0;
        }
        
        //TODO: Verificar Límites
        
        //Verificar Paredes:
        if(tablero[nuevaFila][nuevaColumna].equals(pared)){
            System.out.println("Choque contra una pared");
            return false;
        }
        
        //TODO: Aplicar sistema de premios
        
        if(tablero[nuevaFila][nuevaColumna].equals(fantasma)){
            System.out.println("Te atrapó un fantasma!");
        }
        
        tablero[filaActual][columnaActual]=" ";
        tablero[nuevaFila][nuevaColumna]=pacman;
        
        posicion[0] = nuevaFila;
        posicion[1] = nuevaColumna;
        
        return true;
    }
    
    public static void imprimirTablero(String[][] tablero) {

        // Borde superior
        for (int j = 0; j < COLUMNAS_PEQUENO + 2; j++) {
            System.out.print("- ");
        }
        System.out.println();

        // Filas del tablero
        for (int i = 0; i < FILAS_PEQUENO; i++) {
            System.out.print("|");

            for (int j = 0; j < COLUMNAS_PEQUENO; j++) {
                System.out.print(tablero[i][j] + " ");
            }

            System.out.println("|");
        }

        // Borde inferior
        for (int j = 0; j < COLUMNAS_PEQUENO + 2; j++) {
            System.out.print("- ");
        }
        System.out.println();
    }

    
    public static boolean verificacionPosicionPersonaje(int x, int y, String[][] tablero){
        
        if(tablero[x-1][y-1].equals(" ")){
            return true;
        }
        
        //Posible método para poder implementar choques con paredes.
        
        return false;
        
    }
    
    public static int asignarCantidades(String objeto, double porcentaje){
        boolean bandera = false;
        int cantidad = 1;
        while(bandera == false){
            System.out.print("ELIJA LA CANTIDAD DE "+objeto+": ");
            cantidad = sc.nextInt();
            bandera = true;
            int maxPremios = (int)(FILAS_PEQUENO * COLUMNAS_PEQUENO * porcentaje);
            if (cantidad < 1 || cantidad > maxPremios) {
                System.out.println("ERROR, PORFAVOR INGRESE UN DATO CORRECTO.");
                bandera = false;
            }  
        }
        
        return cantidad;
        
    }
    
    public static void colocarElemento(String[][] tablero, String simbolo, int cantidad) {
        int colocados = 0;

        while (colocados < cantidad) {
            int fila = rand.nextInt(FILAS_PEQUENO);
            int columna = rand.nextInt(COLUMNAS_PEQUENO);

            if (tablero[fila][columna].equals(" ")) {
                tablero[fila][columna] = simbolo;
                colocados++;
            }
        }
    }

}