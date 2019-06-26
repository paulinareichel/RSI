import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Klasa4 extends Agent {

    protected void setup() {
        System.out.println("startuje");
        addBehaviour(new MyThreeStepBehaviour());
    }
}

class MyThreeStepBehaviour extends Behaviour {
    private int step = 0;

    public void action() {
        switch (step++) {
            case 0:
                System.out.println("Krok pierwszy");
                break;
            case 1:
                System.out.println("Krok drugi");
                break;
            case 2:
                System.out.println("Krok trzeci");
                break;
        }
    }

    public boolean done() {
        return step == 3;
    }
}
