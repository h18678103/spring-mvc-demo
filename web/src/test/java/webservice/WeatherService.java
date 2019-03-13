package webservice;

import javax.xml.ws.Endpoint;

/**
 * @author huqinsong
 * @date 2019/3/5
 */
public class WeatherService {

    public static void main(String[] args) {
        String address = "http://127.0.0.1:12345/weather";
        WeatherInterfaceImpl impl = new WeatherInterfaceImpl();
        Endpoint.publish(address, impl);
    }
}
