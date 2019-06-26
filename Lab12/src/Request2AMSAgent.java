import jade.core.*;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.domain.*;
import jade.domain.JADEAgentManagement.*;
import jade.content.onto.basic.*;

public class Request2AMSAgent extends Agent {

    public void setup() {

        getContentManager().registerLanguage(new jade.content.lang.sl.SLCodec(0));
        getContentManager().registerOntology(JADEManagementOntology.getInstance());

        CreateAgent ca = new CreateAgent();
        ca.setAgentName("john");
        ca.setClassName("jade.core.Agent");
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
            } );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //delete Agent
/*
    addBehaviour(new WakerBehaviour(this, 1000) {
        protected void onWake() {
        	KillAgent ka = new KillAgent();
        	ka.setAgent(new AID("john", AID.ISLOCALNAME));
        	Action a = new Action();
        	a.setActor(getAMS());
            a.setAction(ka);
        	ACLMessage request2 = new ACLMessage(ACLMessage.REQUEST);
        	request2.clearAllReceiver();
        	request2.addReceiver(getAMS());
        	request2.setOntology(JADEManagementOntology.NAME);
        	request2.setSender(getAID());
        	request2.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        	request2.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        	try {
        		System.out.println("Try to delete john");
        		getContentManager().fillContent(request2, a);
        		send(request2);
        	}
        	catch (Exception e) {
        		System.out.println("Cannot delete john");
        	}
        }
    });
  */  //delete Agent end

        /**/
        addBehaviour(new WakerBehaviour(this, 2000) {
            protected void onWake() {
                // Create a request to perform the where-is-agent action
                WhereIsAgentAction wa = new WhereIsAgentAction();
                wa.setAgentIdentifier(new AID("john", AID.ISLOCALNAME));
                Action actExpr = new Action(getAMS(), wa);
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                request.addReceiver(getAMS());
                request.setOntology(JADEManagementOntology.getInstance().getName());
                request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
                request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

                try {
                    getContentManager().fillContent(request, actExpr);
                    addBehaviour(new AchieveREInitiator(myAgent, request) {
                        protected void handleInform(ACLMessage inform) {
                            try {
                                Result r = (Result)myAgent.getContentManager().extractContent(inform);
                                ContainerID cid = (ContainerID) r.getValue();
                                System.out.println("john is in "+cid);
                            }
                            catch (Exception e) {
                                System.out.println("john was killed");
                            }
                        }
                    });
                }
                catch (Exception e) {
                    System.out.println("john was killed");
                }
            }
        });
    }
    protected void takeDown() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(getAMS());
        msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        msg.setOntology(JADEManagementOntology.getInstance().getName());
        try {
            getContentManager().fillContent(msg,new Action(getAID(), new ShutdownPlatform()));
            send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
