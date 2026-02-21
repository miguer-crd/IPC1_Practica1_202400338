# IPC1\_Practica1\_202400338

## Manual de uso del programa:

El programa inicia con un menú de tres opciones:

1. Crear un tablero.
2. Ver puntuacion.
3. Salir

Al seleccionar la primera opción (Crear un tablero) se solicitara al usuario que ingrese la cantidad de:

* Premios
* Paredes
* Trampas

Los premios, paredes y trampas se distribuyen aleatoriamente en el tablero.

Finalmente, se le pide al usuario la fila y columna donde desea posicionar su Pac-Man



Al seleccionar la segunda opción (ver puntuación) el usuario podrá visualizar un listado de las partidas que ha ralizado, incluyendo el username y su puntaje.

Y al seleccionar la tercera opción, la aplicación se finalizará. La información acumulada (historial) se borrará.

## Pausar juego

* Se detiene el juego presionando la tecla 'F'.
* Aparece un menú de pausa con las opciones:

  * . Regresar (regresa a la partida)
  * Terminar partida (regresa al menú de inicio, registra la partida)

## Movimientos:

* 8: ARRIBA
* 5: ABAJO
* 6: DERECHA
* 4: IZQUIERDA

## Puntajes

* Premio simple (0): 10 puntos
* Premio especial ($): 15 puntos

## Reglas

* El jugador inicia con 3 vidas y el puntaje en 0.
* Gana si obtiene todos los premios.
* No hay bordes externos: Si Pac-Man se pasa del extremo derecho,
  reaparece en el izquierdo (y viceversa); igual aplica para arriba y abajo.
* No es posible atravesar las Paredes (X).
* Al pasar por un Fantasma (@), el jugador pierde una vida y el fantasma se
  borra.
* Al pasar sobre un Premio (0 o $), el jugador gana puntos y el premio se
  borra.
* El juego termina si: el jugador gana, pierde todas sus vidas, o pausa y
  termina la partida.
* Al terminar, se almacenan los datos (Usuario, Puntos) para el historial.
