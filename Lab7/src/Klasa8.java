import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class Klasa8 extends Agent {

    protected void setup() {
        System.out.println("Start agenta");

        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println("maly tick");
            }
        });

        AgentPktB behaviour = new AgentPktB(this, 5000);
        addBehaviour(behaviour);

        addBehaviour(new TickerBehaviour(this, 50000) {
            protected void onTick() {
                removeBehaviour(behaviour);
            }
        });

        addBehaviour(new TickerBehaviour(this, 100000) {
            protected void onTick() {
                System.out.println("Koniec agenta");
                doDelete();
            }
        });
    }

}

class AgentPktB extends TickerBehaviour {
    public AgentPktB(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {
        System.out.println("duzy tick");
    }
}
