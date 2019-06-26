import jade.core.Agent;

public class Klasa1 extends Agent{

    protected void setup()
    {
        System.out.println("Startuje");

        System.out.println("Zaraz sie usune");
        doDelete();
    }

}