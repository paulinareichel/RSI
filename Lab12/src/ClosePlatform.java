import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import jade.content.onto.basic.*;

public class ClosePlatform extends Agent
{
    public void setup() {
        getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
        getContentManager().registerOntology(JADEManagementOntology.getInstance());

        CreateAgent ca = new CreateAgent();
        ca.setAgentName("john");
        ca.setClassName("jade.core.Agent"); //jade.core.Agent
        ca.setContainer(new ContainerID("Main-Container", null));

        Action actExpr = new Action(getAMS(), ca);

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(getAMS());
        request.setOntology(JADEManagementOntology.getInstance().getName());
        request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        try {
            getContentManager().fillContent(request, actExpr);
            addBehaviour(new AchieveREInitiator(this, request) {
                protected void handleInform(ACLMessage inform) {
                    System.out.println("Agent successfully created");
                }
                protected void handleFailure(ACLMessage failure) {
                    System.out.println("Error creating agent.\n"+failure);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Tworzony Agent, który zamyka platformę
        addBehaviour(new WakerBehaviour(this, 5000) {
            protected void onWake() {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(getAMS());
                msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
                msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                msg.setOntology(JADEManagementOntology.getInstance().getName());
                try {
                    getContentManager().fillContent(msg,new Action(getAID(), new ShutdownPlatform()));
                    send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
