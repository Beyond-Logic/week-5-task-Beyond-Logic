import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class WebServerTest {

    String WEB_ROOT = (".");
    String HTML_FILE = ("src/main/java/WebFiles/index.html");
    String JSON_FILE = ("src/main/java/WebFiles/conFig.json");
    int PORT = 8080;
    final boolean verbose = true;
    final Socket connect = new Socket();

       @Test
       void portChecking() {

           assertEquals(8080, PORT);
           assertNotEquals(7070, PORT);
       }

        @Test

        void webRootChecking() {
            assertEquals(WEB_ROOT, ".");
            assertNotEquals("..", WEB_ROOT);
        }

        @Test
        void htmlFileChecking() {
            assertEquals(HTML_FILE, "src/main/java/WebFiles/index.html");
            assertNotEquals("src/main/java/WebFiles/index.html1", HTML_FILE);
        }

        @Test
        void jsonFileChecking() {

            assertEquals(JSON_FILE, "src/main/java/WebFiles/conFig.json");
            assertNotEquals("src/main/java/WebFiles.conFigs.json", JSON_FILE);
        }

        @Test
        void socketChecking() {

        assertEquals(connect, connect);

        assertTrue(verbose, "is True");


    }

}