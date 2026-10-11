
public class Pila {


    private Nodo tope;

    public void push(String token) {
        tope = new Nodo(token, tope);
    }


    public String pop() {
        if (estaVacia()) {
            return null;
        }
        String token = tope.getToken();
        tope = tope.getSiguiente();
        return token;
    }


    public String peek() {
        if (estaVacia()) {
            return null;
        }
        return tope.getToken();
    }


    public boolean estaVacia() {
        return tope == null;
    }


    public String mostrar() {
        if (estaVacia()) {
            return "(vacía)";
        }
        String texto = "";
        Nodo actual = tope;
        while (actual != null) {
            texto = texto + actual.getToken() + " ";
            actual = actual.getSiguiente();
        }
        return texto.trim();
    }
}