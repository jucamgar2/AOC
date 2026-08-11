package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ConjunctionModule extends PulseModule {

    public Map<String, Boolean> memory = new HashMap<>();

    @Override
    public void receive(Pulse pulse, Queue<Pulse> queue) {
        memory.replace(pulse.from(), pulse.high());
        sendPulse(queue, !memory.values().stream().allMatch(value-> value));
    }

    private void sendPulse(Queue<Pulse> queue, boolean high){
        for(String output: outputs){
            queue.add(new Pulse(this.name, output, high));
        }
    }
    
}
