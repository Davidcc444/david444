package pe.edu.upeu.encapsulamineto;

public class ClaseGeneral {
    public static void main(String[] args) {
        ClaseGeneral cg=new ClaseGeneral();
        Persona persona=new Persona();
        persona.setNombre("David");
        persona.setEdad(18);
        persona.saludar();
        System.out.println("Nombre: "+persona.getNombre());
    }
}
