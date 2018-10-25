package systemeMultiAgent;

import java.util.Random;

import fr.irit.smac.amak.Agent;
import fr.irit.smac.lxplot.LxPlot;
import fr.irit.smac.lxplot.commons.ChartType;
import systemeMultiAgent.AgentLumiereIntelligente.State;

public class AgentVolletAuto  extends Agent<MyAMAS,Salle>{
	
    private CapteurLuminosite interieur;
    private CapteurLuminosite exterieur;
    private Salle date;
    int id;
    
    
    public enum State {
        OUVERT, FERMER
    }

    private State state = State.OUVERT;
    
	public AgentVolletAuto(int id, MyAMAS amas,CapteurLuminosite interieur,CapteurLuminosite exterieur) 
	{
        super(amas);
        this.id = id;
        this.interieur = interieur;
        this.exterieur = exterieur;     
    }
	
    @Override
    protected void onPerceive() 
    {
        // Nothing goes here as the perception of neighbors criticality is already made
        // by the framework
    }
    
    @Override
    protected void onDecideAndAct() {
        State nextState = state;
        switch (state) {
        case OUVERT:

        		if(interieur.Valeur()<10 || exterieur.Valeur()>15)
        		{
        			
        			System.out.println(" Ouverture volet numero "+ this.id);
        			break;
        		}
        case FERMER:
        		if (exterieur.Valeur()<10)
        		{
        			System.out.println("fermeture volet numero "+ this.id);
        			break;
        		}
        	
        default:
            break;

        }

        state = nextState;
    }

    @Override
    protected void onUpdateRender()
    {
        LxPlot.getChart("Etat Vloets", ChartType.BAR).add(id, exterieur.Valeur());


}}
