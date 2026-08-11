package aoc2023.day20;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import structure.BaseDay;
import utils.ConjunctionModule;
import utils.Pulse;
import utils.PulseModule;

public class Day20Solution2023 extends BaseDay {

    @Override
    protected Day20Y2023 getInputData() {
        return Day20Y2023.readDay20Data();
    }

    @Override
    public void runDaySolution() {
        Day20Y2023 input = getInputData();
        System.out.println("--------------------------PART1--------------------------");
        part1Solution(input);
        System.out.println("--------------------------PART2--------------------------");
        part2Solution(input);
    }

    @Override
    protected void part1Solution(Object input) {
        System.out.println("Solución: " + getTotalPulsesMultiply((Day20Y2023) input));
    }

    private long getTotalPulsesMultiply(Day20Y2023 input) {
        long low = 0;
        long high = 0;
        for (int i = 0; i < 1000; i++) {
            Queue<Pulse> queue = new ArrayDeque<>();
            queue.add(new Pulse("button", "broadcaster", false));
            while (!queue.isEmpty()) {
                Pulse pulse = queue.poll();
                if (pulse.high()) {
                    high++;
                } else {
                    low++;

                }
                PulseModule module = input.getModules().get(pulse.to());
                if (module != null) {
                    module.receive(pulse, queue);
                }
            }
        }
        return low * high;
    }

    @Override
    protected void part2Solution(Object input) {
        System.out.println("Solución: " + getFewestPulsesToLowRx((Day20Y2023) input));
    }

    private long getFewestPulsesToLowRx(Day20Y2023 input) {
        ConjunctionModule rxParent = null;
        for (PulseModule module : input.getModules().values()) {
            if (module.outputs.contains("rx")) {
                rxParent = (ConjunctionModule) module;
                break;
            }
        }
        if (rxParent == null) {
            throw new IllegalStateException();
        }
        Map<String, Long> firstSeen = new HashMap<>();
        Map<String, Long> periods = new HashMap<>();
        long buttonPress = 0;
        while (periods.size() < rxParent.memory.size()) {
            buttonPress++;
            Queue<Pulse> queue = new ArrayDeque<>();
            queue.add(new Pulse("button", "broadcaster", false));
            while (!queue.isEmpty()) {
                Pulse pulse = queue.poll();
                if (pulse.to().equals(rxParent.name) && pulse.high()) {
                    String inputName = pulse.from();
                    if (!firstSeen.containsKey(inputName)) {
                        firstSeen.put(inputName, buttonPress);
                    } else if (!periods.containsKey(inputName)) {
                        periods.put(inputName,
                                buttonPress - firstSeen.get(inputName));
                    }
                }
                PulseModule module = input.getModules().get(pulse.to());
                if (module != null) {
                    module.receive(pulse, queue);
                }
            }
        }
        long result = 1;
        for (long period : periods.values()) {
            result = lcm(result, period);
        }
        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}
