package utils;

import java.util.Queue;

public class FlipFlopModule extends PulseModule{

    boolean on;

    @Override
    public void receive(Pulse pulse, Queue<Pulse> queue) {
        if(!pulse.high()){
            on = !on;
            sendPulses(queue);
        }
    }

    private void sendPulses(Queue<Pulse> queue){
        boolean high = on;
        for(String output: outputs){
            queue.add(new Pulse(this.name, output, high));
        }
    }
    
}
