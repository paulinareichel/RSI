import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class ag_wys_odb extends Agent {

    Random r;
    protected void setup() {
        System.out.println("My name is : " +getLocalName());
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                r = new Random();
                int state = r.nextInt(2);
                System.out.println("wylosowana liczba: "+state);
                if (state == 0 ) {
                    ACLMessage m = new ACLMessage(ACLMessage.CFP);
                    m.addReceiver(new AID("Ala", AID.ISLOCALNAME));
                    m.setOntology("presence");
                    m.setContent("MESSAGE: CFP ");
                    send(m);
                }else{
                    ACLMessage m = new ACLMessage(ACLMessage.REQUEST);
                    m.addReceiver(new AID("Ala", AID.ISLOCALNAME));
                    m.setOntology("presence");
                    m.setContent("MESSAGE: REQUEST ");
                    send(m);
                }
                //addBehaviour(new OneShotBehaviour(this) {
                ACLMessage msg = myAgent.receive();
                if (msg != null) {
                    System.out.println("Message "+msg.getContent());
                    if (msg.getPerformative() == 7){
                      //  myAgent.doDelete();
                    }
                }else {
                    block();
                }
            }
        });
    }
}