import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class Klasa7 extends Agent {

    protected void setup() {
        System.out.println("Agent startuje");
        addBehaviour(new Behaviour() {
            boolean finished = false;

            public void action() {
                System.out.println("pierwsze");
                finished = true;
            }

            public boolean done() {
                return finished;
            }
        });
        addBehaviour(new NewTickBehaviour(this));
    }

}

class NewTickBehaviour extends Behaviour {
    private int step = 0;
    Agent agent;

    public NewTickBehaviour(Agent a) {
        super(a);
        agent = a;
    }

    public void action() {
        switch (step++) {
            case 0:
                agent.addBehaviour(new Behaviour() {
                    boolean finished = false;

                    public void action() {
                        System.out.println("drugie");
                        finished = true;
                    }

                    public boolean done() {
                        return finished;
                    }
                });
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