import java.util.Scanner;

public class Main {

    private static Scanner teclado = new Scanner(System.in);
    private static Analizador analizador = new Analizador();


    private static Pila pila = new Pila();

    private static String expresion = "";

    public static void main(String[] args) {
        System.out.println("Bienvenido ");
        menu();
        System.out.println("Programa finalizado. Nos vemos pronto");
    }

    private static void menu() {
        int opcion = 0;

        while (opcion != 8) {
            mostrarOpciones();
            opcion = leerOpcion();

            try {
                if (opcion == 1) {
                    ingresarExpresion();
                } else if (opcion == 2) {
                    convertir();
                } else if (opcion == 3) {
                    evaluar();
                } else if (opcion == 4) {
                    agregarToken();
                } else if (opcion == 5) {
                    sacarToken();
                } else if (opcion == 6) {
                    verTope();
                } else if (opcion == 7) {
                    System.out.println("Pila (tope primero): " + pila.mostrar());
                } else if (opcion != 8) {
                    System.out.println("Opción invalida. seleccione un número del 1 al 8.");
                }
            } catch (RuntimeException error) {

                System.out.println("Error: " + error.getMessage());
            }
        }
    }

    private static void mostrarOpciones() {
        System.out.println();
        System.out.println("---* ANALIZADOR DE EXPRESIONES *---");
        System.out.println("Expresión actual: " + expresion);
        System.out.println("--- Analizar una expresión ---");
        System.out.println("1. Ingresar una expresión");
        System.out.println("2. Convertir a postfijo");
        System.out.println("3. Evaluar la expresión");
        System.out.println("--- Probar la pila ---");
        System.out.println("4. Agregar un token (push)");
        System.out.println("5. Sacar un token (pop)");
        System.out.println("6. Ver el tope (peek)");
        System.out.println("7. Mostrar la pila");
        System.out.println("8. Salir");
        System.out.print("Elija una opción: ");
    }

    private static int leerOpcion() {
        String texto = teclado.nextLine().trim();
        if (analizador.esNumero(texto)) {
            return Integer.parseInt(texto);
        }
        return 0;
    }


    private static void ingresarExpresion() {
        System.out.print("Escriba la expresión, por ejemplo (precio - 4) * cantidad: ");
        expresion = teclado.nextLine().trim();
        System.out.println("Expresión guardada.");
    }


    private static void convertir() {
        if (expresion.isEmpty()) {
            System.out.println("Primero ingrese una expresión con la opción 1.");
            return;
        }
        System.out.println("Conversión paso a paso:");
        String postfijo = analizador.convertirAPostfijo(expresion);
        System.out.println("Postfijo: " + postfijo);
    }


    private static void evaluar() {
        if (expresion.isEmpty()) {
            System.out.println("Primero ingrese una expresión con la opción 1.");
            return;
        }
        System.out.println("Conversión paso a paso:");
        String postfijo = analizador.convertirAPostfijo(expresion);
        System.out.println("Postfijo: " + postfijo);

        System.out.println("Evaluación paso a paso:");
        int resultado = analizador.evaluar(postfijo, teclado);
        System.out.println("Resultado: " + resultado);
    }


    private static void agregarToken() {
        System.out.print("Token que quiere agregar: ");
        String token = teclado.nextLine().trim();
        if (token.isEmpty()) {
            System.out.println("No escribió nada.");
            return;
        }
        pila.push(token);
        System.out.println("Se agregó " + token + ". Pila: " + pila.mostrar());
    }


    private static void sacarToken() {
        if (pila.estaVacia()) {
            System.out.println("La pila está vacía, no hay nada que sacar.");
            return;
        }
        System.out.println("Se sacó " + pila.pop() + ". Pila: " + pila.mostrar());
    }

    private static void verTope() {
        if (pila.estaVacia()) {
            System.out.println("La pila está vacía.");
            return;
        }
        System.out.println("El tope es " + pila.peek());
    }
}
