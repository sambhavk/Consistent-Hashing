package test.java.com.algorithms;

import com.algorithms.LoadBalancer;
import com.algorithms.algorithms.ConsistentHashing;
import com.algorithms.models.Node;
import com.algorithms.models.Request;
import com.algorithms.models.Service;

import org.junit.Assert;
import org.junit.Test;

public class LoadBalancerTester {

    @Test
    public void LBDefault() {
        LoadBalancer lb = new LoadBalancer();
        final var consistentHashing1 = new ConsistentHashing(point -> (long) Math.abs(point.hashCode()) % 100, 1);
        final var consistentHashing2 = new ConsistentHashing(point -> (long) Math.abs(point.hashCode()) % 100, 1);
        final String arithmaticServiceId = "arith", calculusServiceId = "calc";

        lb.register(new Service(arithmaticServiceId, consistentHashing1,
                new String[] { "addition", "substraction", "division", "multiplication" }));
        lb.register(new Service(calculusServiceId, consistentHashing2, new String[] { "differential", "integral" }));

        final Node aNode1 = new Node("31", 2, "10.242.27.97"), aNode2 = new Node("77", 3, "10.242.27.77");
        lb.addNode(arithmaticServiceId, aNode1);
        lb.addNode(arithmaticServiceId, aNode2);

        final Node cNode1 = new Node("8", 4, "10.242.77.97"), cNode2 = new Node("64", 5, "10.247.27.77");
        lb.addNode(calculusServiceId, cNode1);
        lb.addNode(calculusServiceId, cNode2);

        var arithNode1 = lb.getHandler(new Request("r-101", arithmaticServiceId, "addition"));
        var arithNode2 = lb.getHandler(new Request("r-102", arithmaticServiceId, "substraction"));
        var arithNode3 = lb.getHandler(new Request("r-103", arithmaticServiceId, "addition"));
        var arithNode4 = lb.getHandler(new Request("r-777", arithmaticServiceId, "addition"));
        Assert.assertEquals(aNode1, arithNode1);
        Assert.assertEquals(aNode1, arithNode2);
        Assert.assertEquals(aNode1, arithNode3);
        Assert.assertEquals(aNode2, arithNode4);

        // scale down
        lb.removeNode(arithmaticServiceId, aNode2.getId());

        arithNode4 = lb.getHandler(new Request("r-777", arithmaticServiceId, "addition"));
        Assert.assertEquals(aNode1, arithNode4);

        // scale up. Old Request handling Nodes will be same and new requests load will
        // be distributed to new nodes.
        final Node aNode3 = new Node("42", 2, "0.0.0.0"), aNode4 = new Node("43", 2, "1.0.0.0");
        lb.addNode(arithmaticServiceId, aNode3);
        lb.addNode(arithmaticServiceId, aNode4);

        arithNode1 = lb.getHandler(new Request("r-101", arithmaticServiceId, "addition"));
        arithNode2 = lb.getHandler(new Request("r-102", arithmaticServiceId, "substraction"));
        arithNode3 = lb.getHandler(new Request("r-103", arithmaticServiceId, "addition"));
        arithNode4 = lb.getHandler(new Request("r-777", arithmaticServiceId, "addition"));
        var arithNode5 = lb.getHandler(new Request("r-424", arithmaticServiceId, "addition"));

        Assert.assertEquals(aNode1, arithNode1);
        Assert.assertEquals(aNode1, arithNode2);
        Assert.assertEquals(aNode1, arithNode3);
        Assert.assertEquals(aNode1, arithNode4);
        Assert.assertEquals(aNode3, arithNode5);

        // Similarly for calculus service
    }
}
