package com.diegomalone.movielist.network;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class NetworkServer {

    private static NetworkServer instance;

    private MockWebServer server;

    public static NetworkServer getInstance() {
        if (instance != null) {
            return instance;
        }

        instance = new NetworkServer();

        return instance;
    }

    private NetworkServer() {
        server = new MockWebServer();

        ServiceFactory.baseUrl = server.url("").toString();
    }

    public void enqueue(MockResponse response) {
        server.enqueue(response);
    }
}
