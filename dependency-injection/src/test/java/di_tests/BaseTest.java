package di_tests;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;
import ru.nmt.client.di_client.ApiClient;
import ru.nmt.client.di_client.PetDiApiClient;
import ru.nmt.client.di_client.RestAssuredApiClient;
import ru.nmt.client.di_client.StoreDiApiClient;

import static ru.nmt.constants.Endpoints.BASE_URI;

public abstract class BaseTest {

    protected static MutablePicoContainer container;

    static {
        // Создаем DI контейнер и при помощи new Caching() указываем что необходимо кешировать созданные объекты, чтобы они создавались 1
        // раз и переиспользовались везде
        container = new DefaultPicoContainer(new Caching());

//     ✅ Централизованное управление конфигурацией зависимостей (например, можно менять реализацию ApiClient на мок).
//     ✅ Автоматическое внедрение зависимостей. Не надо писать:
//        private static final ApiClient restAssuredClient = new RestAssuredApiClient(BASE_URI);
//        private static final PetApiClient PET_CLIENT = new PetApiClient(restAssuredClient);
//     ✅ Один и тот же экземпляр объекта (например, ApiClient) создаётся один раз и затем используется (внедряется) во все классы,
//     которым он нужен
//     ✅ Можно внедрять разные сервисы в зависимости от окружения (dev/stage/prod).
        container.addComponent(ApiClient.class, new RestAssuredApiClient(BASE_URI));
        container.addComponent(PetDiApiClient.class);
        container.addComponent(StoreDiApiClient.class);
    }
}
