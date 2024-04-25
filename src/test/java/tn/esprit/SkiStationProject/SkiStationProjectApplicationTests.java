import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.jupiter.MockitoExtension; // Assurez-vous que cette importation est présente

import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.entities.Subscription;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.SkierRepository;
import tn.esprit.SkiStationProject.repositories.SubscriptionRepository;
import tn.esprit.SkiStationProject.services.SubscriptionServicesImpl;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.SkiStationProjectApplication;

@SpringBootTest(classes=SkiStationProjectApplication.class)
public class SkiStationProjectApplicationTests {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private SkierRepository skierRepository;

    @Test
    void contextLoads() {
        // Votre code de test pour le chargement du contexte Spring
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.of(2024, 1, 1));

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);

        assertEquals(TypeSubscription.ANNUAL, savedSubscription.getTypeSub());
        assertEquals(LocalDate.of(2025, 1, 1), savedSubscription.getEndDate());
    }
}

