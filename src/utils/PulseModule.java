package utils;

import java.util.List;
import java.util.Queue;

public abstract class PulseModule {
    public String name;
    public List<String> outputs;

    public abstract void receive(Pulse pulse, Queue<Pulse> queue);
}