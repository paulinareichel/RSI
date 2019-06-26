import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREInitiator;
import jade.util.leap.Iterator;
import jade.util.leap.List;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import jade.domain.mobility.MobileAgentDescription;
import jade.domain.mobility.MobilityOntology;
import jade.domain.mobility.MoveAction;

import java.util.Random;

import jade.content.ContentElement;
import jade.content.onto.basic.*;

public class ContainerAgent extends Agent {

    Random generator;
    public void setup() {
        generator = new Random();
        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                send();
                recieve();
            }
        });
    }
    private void send() {
        getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
        getContentManager().registerOntology(JADEManagementOntology.getInstance());

        QueryPlatformLocationsAction q = new QueryPlatformLocationsAction();
        Action action = new Action(getAMS(), q);

        ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(getAMS());
        message.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        message.setOntology(JADEManagementOntology.getInstance().getName());
        try {
            getContentManager().fillContent(message, action);
            send(message);
        }
        catch(Exception e) {
            System.out.println("Cannot send request");
            System.out.println(e.getMessage());
        }

    }
    private void recieve() {
        ACLMessage receivedMessage = blockingReceive(MessageTemplate.MatchSender(getAMS()));
        ContentElement content = null;
        try {
            content = getContentManager().extractContent(receivedMessage);
        }catch(Exception e) {System.out.println("Error");}
        // received message is a Result object, whose Value field is a List of
        // ContainerIDs
        Result result = (Result) content;
        List listOfPlatforms = (List) result.getValue();

        // use it
        Iterator iter = listOfPlatforms.iterator();
        int i = (int)Math.round(generator.nextDouble()*listOfPlatforms.size());
        int ii = 0;
        while (iter.hasNext()) {
            ContainerID next = (ContainerID) iter.next();
            if(i==ii)
            {
                System.out.println("I move to container " + next);//+ contName);
                doMove(next);
            }
            System.out.println(next.getID());
            ii++;
        }
    }
}
