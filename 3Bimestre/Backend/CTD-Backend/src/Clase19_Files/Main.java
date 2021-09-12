package Clase19_Files;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Contacto> contactos = new ArrayList<>();
            contactos.add(new Contacto("Ian Vaernet", "ianvaernet@gmail.com", "+5493624360065"));
            contactos.add(new Contacto("Julian Alvarado", "julianalga@outlook.com", "+568232465"));
            contactos.add(new Contacto("Nayla Saez", "naylasaez@outlook.com", "+54925647451"));

            FileOutputStream fos = new FileOutputStream("OutputFile.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactos);
            oos.close();

            FileInputStream fis = new FileInputStream("OutputFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Contacto> contactosLeidos = (ArrayList<Contacto>) ois.readObject();
            contactosLeidos.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
