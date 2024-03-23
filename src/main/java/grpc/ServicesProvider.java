package grpc;

import io.grpc.BindableService;

import java.util.Collection;

public interface ServicesProvider {
    Collection<BindableService> getBindableServices();
    int getServerPort();
    String getProviderName();
}
