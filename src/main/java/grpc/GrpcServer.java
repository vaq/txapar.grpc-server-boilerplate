package grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Client applications must implement exactly one instance of the ServicesProvider and ServerConfigurationProvider interfaces.
 * <p>
 * Invoking the start method of the GrpcServer class will start the gRPC server.
 */
@Slf4j
public class GrpcServer {

    private final ServicesProvider servicesProvider;

    public GrpcServer(ServicesProvider servicesProvider) {
        this.servicesProvider = servicesProvider;
    }

    public void start() {

        var grpcServer = new Internal(servicesProvider);

        try {
            grpcServer.start();
        } catch (Exception e) {
            Runtime.getRuntime().exit(1);
        }

    }

    @Slf4j
    private static class Internal {


        private final ServicesProvider servicesProvider;
        private Server server;

        Internal(ServicesProvider servicesProvider) {
            this.servicesProvider = servicesProvider;
        }

        public void start() throws IOException, InterruptedException {

            var serverPort = servicesProvider.getServerPort();
            var serviceName = servicesProvider.getProviderName();

            log.info("Attempting to start gRPC server for " + serviceName);

            var serverBuilder = ServerBuilder.forPort(serverPort);
            servicesProvider.getBindableServices().forEach(serverBuilder::addService);
            server = serverBuilder.build().start();

            log.info("gRPC server started, listening on port " + serverPort);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {

                log.error("Shutting down gRPC server since JVM is shutting down");

                try {
                    Internal.this.stop();
                } catch (InterruptedException e) {
                    log.error("Error shutting down gRPC server", e);

                }
                log.error("Server shut down");
            }));

            // Await termination on the thread that started the server.
            server.awaitTermination();
        }

        private void stop() throws InterruptedException {
            if (server != null) {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            }
        }


    }


}
