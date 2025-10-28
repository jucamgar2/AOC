package utils;

import java.util.Objects;

public class NodePair{
    private Node from;
    private Node to;
    
    public NodePair(Node from, Node to){
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodePair pairNode = (NodePair) o;
        return this.from.getValue().equals(pairNode.getFrom().getValue()) &&
                 this.to.getValue().equals(pairNode.getTo().getValue());
    }

    @Override
    public int hashCode(){
        return Objects.hash(from.getValue(), to.getValue());
    }
}
