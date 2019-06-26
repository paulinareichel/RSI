import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class wykonawca_1 extends Agent{

    protected void setup() {

        super.setup();

        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName( getAID() );
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType( "POTEGA" );
        serviceDescription.setName( getLocalName() );
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(this, dfAgentDescription);
        }
        catch (FIPAException e) { e.printStackTrace(); }
    }
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {e.printStackTrace();}

    }
}
