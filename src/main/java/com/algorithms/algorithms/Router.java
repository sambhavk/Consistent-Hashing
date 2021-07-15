package com.algorithms.algorithms;

import com.algorithms.models.Node;
import com.algorithms.models.Request;

public interface Router {
    void addNode(Node node);

    void removeNode(Node node);

    Node getAssignedNode(Request request);
}
