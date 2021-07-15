package com.algorithms.models;

import com.algorithms.algorithms.Router;

public class Service {
    private final String id;
    private final Router router;
    private final String[] methods;

    /**
     * @param id
     * @param router
     * @param methods
     */
    public Service(String id, Router router, String[] methods) {
        this.id = id;
        this.router = router;
        this.methods = methods;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the router
     */
    public Router getRouter() {
        return router;
    }

    /**
     * @return the methods
     */
    public String[] getMethods() {
        return methods;
    }

}
