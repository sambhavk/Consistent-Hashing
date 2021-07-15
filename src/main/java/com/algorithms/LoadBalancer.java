package com.algorithms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.algorithms.models.Node;
import com.algorithms.models.Request;
import com.algorithms.models.Service;

public class LoadBalancer {
    private final Map<String, Service> services;
    private final Map<String, Node> nodes;

    /**
     * @param services
     * @param nodes
     */
    public LoadBalancer() {
        this.services = new ConcurrentHashMap<>();
        this.nodes = new ConcurrentHashMap<>();
    }

    public void register(Service service) {
        services.put(service.getId(), service);
    }

    public void addNode(String serviceId, Node node) {
        nodes.put(node.getId(), node);
        services.get(serviceId).getRouter().addNode(node);
    }

    public void removeNode(String serviceId, String nodeId) {
        services.get(serviceId).getRouter().removeNode(nodes.remove(nodeId));
    }

    public Node getHandler(Request request) {
        return services.get(request.getServiceId()).getRouter().getAssignedNode(request);
    }
}
