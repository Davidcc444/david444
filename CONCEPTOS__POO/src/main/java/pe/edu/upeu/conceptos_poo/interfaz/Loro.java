package pe.edu.upeu.interfaz;

public class Loro implements Animal{
    @Override
    public void emitirSonido() {
        System.out.println("Miauuuuu Miauuuu");
    }

    @Override
    public void dormir() {
        System.out.println("Hola estoy durmiendo");
    }
}
