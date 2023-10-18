package central.vehiculos;

import org.springframework.stereotype.Component;

import central.vehiculos.servicios.SOAPImplementacion;

import jakarta.xml.ws.Endpoint;

@Component
public class EndpointPublisher {

    @PostConstruct
    public void publish() {
        System.out.println("Iniciando el servicio");
        String address = "http://0.0.0.0:8081/";
        Endpoint.publish(address, new SOAPImplementacion());
    }
}
