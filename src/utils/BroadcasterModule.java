package utils;

import java.util.Queue;

public class BroadcasterModule extends PulseModule{

    @Override
    public void receive(Pulse pulse, Queue<Pulse> queue) {
        for(String output: outputs){
            queue.add(new Pulse(this.name, output, pulse.high()));
        }   
    }
    
}
