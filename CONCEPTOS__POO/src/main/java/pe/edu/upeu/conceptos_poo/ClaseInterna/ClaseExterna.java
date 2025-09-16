package pe.edu.upeu.ClaseInterna;

public class ClaseExterna {
    int a,b;
        int operacion() {
            return a + b;
        }
    class ClaseInterna1{
        int r;
        void mensaje(){
            r=a+b;
            System.out.println("La suma es "+r);
        }
    }
    class ClaseInterna2{
        void OtraOperacion(){
            System.out.println("La operacion de la resta es: "+(a-b));
        }
    }

    public static void main(String[] args) {
        ClaseExterna oe=new ClaseExterna();
        oe.a=8;
        oe.b=2;
        System.out.println("La operacion es "+oe.operacion());
        ClaseInterna1 ci1=oe.new ClaseInterna1();
        ci1.mensaje();
        ClaseInterna2 ci2=oe. new ClaseInterna2();
        ci2.OtraOperacion();
    }

}
