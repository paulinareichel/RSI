import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.util.Random;

public class Klasa_1_2 extends Agent {

    protected void setup() {
        System.out.println("My name is : " + getLocalName());
        addBehaviour(new Klasa_1_2.FourStepBehaviour());
    }

    private class FourStepBehaviour extends Behaviour {
        int pom = 1;
        int stan = 0;
        Random r;

        public void action() {
            switch (pom) {
                case 1:
                    stan = 0;
                    System.out.println("A - stan: " + stan);
                    break;
                case 2:
                    r = new Random();
                    stan = r.nextInt(2);
                    System.out.println("B - stan: " + stan);
                    if (stan == 0) {
                        pom = 4;
                    }
                    break;
                case 3:
                    System.out.println("C - stan: " + stan);
                    break;
                case 4:
                    r = new Random();
                    stan = r.nextInt(2);
                    System.out.println("D - stan: " + stan);
                    if (stan == 1) {
                        pom = 1;
                    }
                    break;
                case 5:
                    System.out.println("E - stan: " + stan);
                    break;
            }
            pom++;
        }

        public boolean done() {
            return pom == 6;
        }
    }
}
