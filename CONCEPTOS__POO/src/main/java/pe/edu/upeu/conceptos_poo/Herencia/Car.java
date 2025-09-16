package pe.edu.upeu.Herencia;

public class Car extends Vehiculo{
    String color="Blancp";
    String modelo="Hilux";
    void caracteristicas(){
        marca="Toyota";
        System.out.println("La marca de este vehiculo es:"+marca);
        System.out.println("Marca: "+marca);
        System.out.println("Modelo: "+modelo);
        Sonido();

    }
    public static void main(String[] args) {
        Car c =new Car();
        c.caracteristicas();
    }
}
