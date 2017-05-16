package ru.stqa.pfste.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

    @Test
    public void testMyIP() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("80.87.200.214"); // http://software-testing.ru
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void testInvalidIP() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("80.87.200.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
