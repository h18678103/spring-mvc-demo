package webservice;

import javax.jws.WebService;

/**
 * @author huqinsong
 * @date 2019/3/5
 */
@WebService
public class WeatherInterfaceImpl implements WeatherInterface{
    @Override
    public String getWeatherByCity(String city) {
        System.out.println("接收到城市="+city);
        return city + "：天气好冷";
    }
}
