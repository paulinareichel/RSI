import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.Scanner;

public class Klasa5 extends Agent {
    protected void setup() {
        System.out.println("Startuje");
        addBehaviour(new CheckNumberBehaviour());
    }

}

class CheckNumberBehaviour extends SimpleBehaviour {

    boolean finished = false;

    public void action() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe calkowita : ");
        int pom = scanner.nextInt();
        if (pom < 0) {
            System.out.println("Zaraz sie usune");
            finished = true;
        }
    }


    public boolean done() {
        return finished;
    }
}
