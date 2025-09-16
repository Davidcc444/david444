package pe.edu.upeu.Enums;
enum GENERO{M,F}
enum NACIONALIDAD {Peruano,Venezolano,Boliviano}

public class Persona {
    static String nombre;

    static GENERO genero=GENERO.M;
    static NACIONALIDAD nacionaalidad=NACIONALIDAD.Peruano;

    public static void main(String[] args) {
        nombre="Josue";
        System.out.println(nombre+": Nombre");
        for (GENERO XX: GENERO.values()){
            System.out.println(XX);
        }
        for (NACIONALIDAD XX: NACIONALIDAD.values()){
            System.out.println(XX);
        }
    }

}
