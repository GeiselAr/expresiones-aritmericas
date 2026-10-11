import java.util.Scanner;


public class Analizador {


    public String convertirAPostfijo(String expresion) {
        Pila pila = new Pila();
        String salida = "";
        String[] tokens = separarEnTokens(expresion);

        for (String token : tokens) {
            if (esNumero(token) || esVariable(token)) {
                salida = salida + token + " ";

            } else if (token.equals("(")) {
                pila.push(token);

            } else if (token.equals(")")) {

                while (!pila.estaVacia() && !pila.peek().equals("(")) {
                    salida = salida + pila.pop() + " ";
                }
                if (pila.estaVacia()) {
                    throw new RuntimeException("Hay un paréntesis ) sin su (");
                }
                pila.pop();

            } else if (esOperador(token)) {

                while (!pila.estaVacia() && esOperador(pila.peek())
                        && prioridad(pila.peek()) >= prioridad(token)) {
                    salida = salida + pila.pop() + " ";
                }
                pila.push(token);

            } else {
                throw new RuntimeException("Símbolo no reconocido: " + token);
            }

            System.out.println("  Lee " + token + "   | Pila: " + pila.mostrar()
                    + "   | Salida: " + salida);
        }

        while (!pila.estaVacia()) {
            String token = pila.pop();
            if (token.equals("(")) {
                throw new RuntimeException("Hay un paréntesis ( sin cerrar");
            }
            salida = salida + token + " ";
        }
        return salida.trim();
    }

    public int evaluar(String postfijo, Scanner teclado) {
        Pila pila = new Pila();
        String[] tokens = postfijo.split(" ");

        for (String token : tokens) {
            if (esOperador(token)) {

                int derecho = sacarNumero(pila);
                int izquierdo = sacarNumero(pila);
                int resultado = operar(izquierdo, derecho, token);
                pila.push(String.valueOf(resultado));

            } else if (esNumero(token)) {
                pila.push(token);

            } else {

                pila.push(pedirValor(token, teclado));
            }

            System.out.println("  Lee " + token + "   | Pila: " + pila.mostrar());
        }

        int resultado = sacarNumero(pila);
        if (!pila.estaVacia()) {
            throw new RuntimeException("Sobran números, falta un operador");
        }
        return resultado;
    }


    private int sacarNumero(Pila pila) {
        if (pila.estaVacia()) {
            throw new RuntimeException("Falta un número para una operación");
        }
        return Integer.parseInt(pila.pop());
    }


    private int operar(int izquierdo, int derecho, String operador) {
        if (operador.equals("+")) {
            return izquierdo + derecho;
        }
        if (operador.equals("-")) {
            return izquierdo - derecho;
        }
        if (operador.equals("*")) {
            return izquierdo * derecho;
        }
        if (derecho == 0) {
            throw new RuntimeException("No se puede dividir entre cero");
        }
        return izquierdo / derecho;
    }

    private String pedirValor(String variable, Scanner teclado) {
        System.out.print("  Valor de " + variable + ": ");
        String valor = teclado.nextLine().trim();
        while (!esNumero(valor)) {
            System.out.print("  Debe ser un número entero. Valor de " + variable + ": ");
            valor = teclado.nextLine().trim();
        }
        return valor;
    }


    private String[] separarEnTokens(String expresion) {
        String simbolos = "()+-*/";
        for (int i = 0; i < simbolos.length(); i++) {
            String simbolo = String.valueOf(simbolos.charAt(i));
            expresion = expresion.replace(simbolo, " " + simbolo + " ");
        }
        return expresion.trim().split("\\s+");
    }

    public boolean esNumero(String token) {
        if (token.isEmpty() || token.length() > 9) {
            return false;
        }
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isDigit(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private boolean esVariable(String token) {
        if (token.isEmpty()) {
            return false;
        }
        for (int i = 0; i < token.length(); i++) {
            if (!Character.isLetter(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean esOperador(String token) {
        return token.equals("+") || token.equals("-")
                || token.equals("*") || token.equals("/");
    }


    private int prioridad(String operador) {
        if (operador.equals("*") || operador.equals("/")) {
            return 2;
        }
        return 1;
    }
}




