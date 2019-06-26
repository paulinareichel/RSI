import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class Klasa2 extends Agent {
    protected void setup() {
        System.out.println("Startuje");
        addBehaviour(new BehaviourTest(this));
    }

}

class BehaviourTest extends OneShotBehaviour {
    public BehaviourTest(Agent a) {
        super(a);
    }

    public void action() {
        System.out.println("Wykonuje ");
    }


}
