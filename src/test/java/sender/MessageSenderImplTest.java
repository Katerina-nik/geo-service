package sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class MessageSenderImplTest {

    GeoService geoServiceMock = Mockito.mock();
    LocalizationServiceImpl localizationServiceMock = Mockito.mock();
    MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
    Map<String, String> headers = new HashMap<>();

    @Test
    void testSendRussian() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        when(localizationServiceMock.locale(any())).thenReturn("Добро пожаловать");
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        String expected = "Добро пожаловать";

        String actual = messageSender.send(headers);

        assertEquals(expected, actual);
    }

    @Test
    void testSendAmerican() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        when(localizationServiceMock.locale(any())).thenReturn("Welcome");
        when(geoServiceMock.byIp(anyString())).thenReturn(new Location("New York", Country.USA, null, 0));
        String expected = "Welcome";

        String actual = messageSender.send(headers);

        assertEquals(expected, actual);
    }
}