package pe.edu.upeu.AbsPolimorfismo;

public class Loro extends Animal{
    @Override
    public void sonidoAnimal() {
        System.out.println("Hola como te va");
    }
    @Override
    public void dornir(){
        System.out.println("No hagas bulla tengo sueño....");
    }
}
