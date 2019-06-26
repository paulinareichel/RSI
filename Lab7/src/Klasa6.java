
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

import java.util.Scanner;

public class Klasa6 extends Agent {

    protected void setup() {
        System.out.println("Agent " + getLocalName());
        addBehaviour(new CheckNumberVersionTwo());
    }

}

class CheckNumberVersionTwo extends SimpleBehaviour {

    boolean finished = false;

    public void action() {
        System.out.println("Zachowanie startuje");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe: ");
        int num = scanner.nextInt();
        if (num < 0) {
            System.out.println("Konczenie dzialania agenta");
            finished = true;
        }
    }


    public boolean done() {
        if (finished) System.out.println("Zachowanie zakonczone");
        return finished;
    }
}

