import java.util.ArrayList;
import java.util.List;

public class Buzon {

    public List<String> mensajes=new ArrayList<String>();

    public synchronized void escribir(String mensaje){
        mensajes.add(mensaje);
        notifyAll();
    }

    public synchronized String leer(){
        while(mensajes.isEmpty()){
            try {
                System.out.println("Esperando mensaje");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String mensaje = mensajes.get(0);
        mensajes.remove(0);

        return mensaje;
    }
}
