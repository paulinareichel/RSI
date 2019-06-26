import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class Klasa_2_4 extends Agent {
    protected void setup() {
        System.out.println("My name is : " + getLocalName());
        addBehaviour(new Klasa_2_4.FourStepBehaviour());
        addBehaviour(new Klasa_2_4.FourStepBehaviour());
        addBehaviour(new Klasa_2_4.FourStepBehaviour());
    }

    private class FourStepBehaviour extends Behaviour {
        private int step = 1;

        public void action() {
            switch (step) {
                case 1:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok pierwszy");
                        }
                    });
                    break;
                case 2:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok drugi");
                        }
                    });
                    break;
                case 3:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent){
                        public void action() {
                            System.out.println("Krok trzeci");
                            removeBehaviour(this);
                        }
                    });
                    break;
            }
            step++;
        }

        public boolean done() {
            return step == 4;
        }

    }

}