import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class Klasa3 extends Agent {

    protected void setup() {
        System.out.println("startuje");
        addBehaviour(new CyclicBehaviour() {
            public void action() {
                System.out.println("wykonuje");
            }
        });
    }
}

