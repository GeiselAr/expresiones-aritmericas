
public class Nodo {

    private String token;
    private Nodo siguiente;

    public Nodo(String token, Nodo siguiente) {
        this.token = token;
        this.siguiente = siguiente;
    }

    public String getToken() {
        return token;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }
}