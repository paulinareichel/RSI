import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class zlecajacy extends Agent{
    private zlecajacy myAgent;
    private Random random;
    private Integer messageNumber;

    protected void setup() {
        super.setup();

        myAgent = this;
        random = new Random();
        messageNumber = null;

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                System.out.println("Zlecajacy: random pick (int) number from 0 - 100 ");
                messageNumber = (int)(Math.round(random.nextDouble()*100));
                System.out.println("Zlecajacy: msg number: "+messageNumber);
            }
        });

        FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        addBehaviour(new TickerBehaviour(this, 20000) {
            protected void onTick() {
                System.out.println("Zlecajacy: checking services");

                DFAgentDescription dfd = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType( "POTEGA" );
                dfd.addServices(sd);

                SearchConstraints ALL = new SearchConstraints();
                ALL.setMaxResults((long) -1);
                try
                {
                    DFAgentDescription[] result = DFService.search(myAgent, dfd, ALL);
                    System.out.println("Zlecajacy: serwices contains 'POTEGA' : " + result.length);
                    if(result.length>0) {
                        send(result[0].getName());
                        recieve(this);
                    }
                }
                catch (FIPAException fe) { System.out.println("Zlecajacy: ERROR while checking services"); }
            }
        });
    }
    private void send(AID aid) {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(aid);
        msg.setContent(Integer.toString(messageNumber));
        send(msg);
        System.out.println("Zlecajacy: sending msg, type: REQUEST ");
    }
    private void recieve(Behaviour b) {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg1 = blockingReceive(mt);
        if(msg1!= null) {
            if(msg1.getPerformative() == ACLMessage.INFORM){
                System.out.println("Zlecajacy: received msg, type: INFORM ");
                System.out.println( "Zlecajacy: msg content: " + msg1.getContent());
                myAgent.doDelete();
            }
        }
        else {
            b.block();
        }
    }
    protected void takeDown() {
        System.out.println("Zlecajacy: DELETING");
    }
}
