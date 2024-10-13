package netology.ru.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImpTest {
    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @ParameterizedTest
    @EnumSource(Country.class)
    void testMessageByLocate(Country country) {
        String resul = localizationService.locale(country);

        if (country == Country.RUSSIA) {
            Assertions.assertEquals("Добро пожаловать", resul);
        } else {
            Assertions.assertEquals("Welcome", resul);
        }
    }
}
