package agent;

import environment.Room;
import fr.irit.smac.amak.Amas;
import fr.irit.smac.amak.Scheduling;
import main.Config;

import java.util.ArrayList;
import java.util.List;

public class MyAMAS extends Amas<Room> {

    private List<SmartLightingAgent> lightingAgents;
    private List<SmartWindowAgent> windowAgents;


    public MyAMAS(Room env) {
        // Set the environment and use default scheduling
        super(env, Scheduling.DEFAULT);
    }

    @Override
    protected void onInitialAgentsCreation() {
        lightingAgents = new ArrayList<SmartLightingAgent>();
        windowAgents = new ArrayList<SmartWindowAgent>();

        //
        for (int i = 0; i < Config.LAMPS_NUMBER; i++) {
            lightingAgents.add(new SmartLightingAgent(i, this));
        }

        //
        for (int i = 0; i < Config.SHUTTERS_NUMBER; i++) {
            windowAgents.add(new SmartWindowAgent(i, this));
        }


    }

    @Override
    protected void onSystemCycleEnd() {
        //code that must be executed at the end of each cycle
        // For example, draw a point on a chart
    }
}