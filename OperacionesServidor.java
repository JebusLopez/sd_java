package CadenaMayusMinus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperacionesServidor extends Thread{
    
    Socket sc;
    int contador;
    String data, mensaje;
    DataInputStream in;
    DataOutputStream out;
    
    OperacionesServidor(Socket sc, int contador) {
        this.sc = sc;
        this.contador = contador;           
    }
    
    public void run(){
        try {
            convertir();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void convertir() throws IOException {
        System.out.println("Hilo de atencion numero: " + contador);
        System.out.println("Operaciones Iniciadas " + getName() + " " + sc.getInetAddress());
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());
        while(data != "salir"){
            data = in.readUTF();
            System.out.println("data: " + data);
            char[] aData = data.toCharArray();
            for(int i =0; i<aData.length; i++){
                if(Character.isUpperCase(aData[i])){
                    aData[i] = Character.toLowerCase(aData[i]);
                }else if(Character.isLowerCase(aData[i])){
                    aData[i] = Character.toUpperCase(aData[i]);
                }
            }
            out.writeUTF(String.valueOf(aData));
        }
    }
}
