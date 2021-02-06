import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static base.BasePage.EventsType.PAST;
import static base.BasePage.EventsType.UPCOMNIG;

public class EpamTest extends BaseTest {

    @Test
    @DisplayName("Check number of upcoming events")
    public void CheckNumberOfUpcomingEvents() {

        mainPage.openEvents();

        eventsPage.openUpcomingEvents();

        eventsPage.checkIfCountersMatch(UPCOMNIG);

    }

    @Test
    @DisplayName("Check upcoming event card")
    public void CheckUpcomingEventCard() {

        mainPage.openEvents();

        eventsPage.openUpcomingEvents();

    }

    @Test
    @DisplayName("Check past events")
    public void checkPastEvents() {

        mainPage.openEvents();

        eventsPage.openPastEvents();

        eventsPage.adjustFilter();

        eventsPage.checkIfCountersMatch(PAST);

    }

    @Test
    @DisplayName("Video filter test")
    public void videoFilterTest() {

        mainPage.openVideos();

        videoPage.adjustFilter();

        videoPage.checkFoundTalksInfo();

    }

    @Test
    @DisplayName("Video search test")
    public void videoSearchTest() {

        mainPage.openVideos();

        videoPage.typeAndSearch();

        videoPage.checkFoundTalksTitle();

    }

}
