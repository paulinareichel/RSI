import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class Klasa_2_3_1 extends Agent {
    protected void setup() {
        System.out.println("My name is : " + getLocalName());
        ThreadedBehaviourFactory threadedBehaviourFactory = new ThreadedBehaviourFactory();
        Behaviour a = new Klasa_2_3_1.FourStepBehaviour1();
        Behaviour b = new Klasa_2_3_1.FourStepBehaviour2();
        Behaviour c = new Klasa_2_3_1.FourStepBehaviour3();
        addBehaviour(threadedBehaviourFactory.wrap(a));
        addBehaviour(threadedBehaviourFactory.wrap(b));
        addBehaviour(threadedBehaviourFactory.wrap(c));
    }

    private class FourStepBehaviour1 extends Behaviour {
        private int step = 1;

        public void action() {
            switch (step) {
                case 1:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok pierwszy_z1");
                        }
                    });
                    break;
                case 2:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok drugi_z1");
                        }
                    });
                    break;
                case 3:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent){
                        public void action() {
                            System.out.println("Krok trzeci_z1");
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

    private class FourStepBehaviour2 extends Behaviour {
        private int step = 1;

        public void action() {
            switch (step) {
                case 1:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok pierwszy_z2");
                        }
                    });
                    break;
                case 2:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok drugi_z2");
                        }
                    });
                    break;
                case 3:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent){
                        public void action() {
                            System.out.println("Krok trzeci_z2");
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

    private class FourStepBehaviour3 extends Behaviour {
        private int step = 1;

        public void action() {
            switch (step) {
                case 1:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok pierwszy_z3");
                        }
                    });
                    break;
                case 2:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        public void action() {
                            System.out.println("Krok drugi_z3");
                        }
                    });
                    break;
                case 3:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent){
                        public void action() {
                            System.out.println("Krok trzeci_z3");
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