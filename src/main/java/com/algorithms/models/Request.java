package com.algorithms.models;

public class Request {
    private final String id;
    private final String serviceId;
    private final String method;

    /**
     * @param id
     * @param serviceId
     * @param method
     */
    public Request(String id, String serviceId, String method) {
        this.id = id;
        this.serviceId = serviceId;
        this.method = method;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

}
