package com.algorithms.models;

import java.util.Objects;

public class Node {
    private final String id;
    private final int weight;
    private final String ipAddress;

    /**
     * @param id
     * @param ipAddress
     */
    public Node(String id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.weight = 1;
    }

    /**
     * @param id
     * @param weight
     * @param ipAddress
     */
    public Node(String id, int weight, String ipAddress) {
        this.id = id;
        this.weight = weight;
        this.ipAddress = ipAddress;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        return id.equals(other.id);
    }

}
