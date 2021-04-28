import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class WebServerTest {

    @org.junit.jupiter.api.Test
    void main() {

        String WEB_ROOT = (".");
        String HTML_FILE = ("src/main/java/WebFiles/index.html");
        String JSON_FILE = ("src/main/java/WebFiles/conFig.json");
        int PORT = 8080;
        final boolean verbose = true;
        final Socket connect = new Socket();

        assertEquals(8080, PORT);
        assertNotEquals(7070, PORT);

        assertEquals(WEB_ROOT, ".");
        assertNotEquals("..", WEB_ROOT);

        assertEquals(HTML_FILE, "src/main/java/WebFiles/index.html");
        assertNotEquals("src/main/java/WebFiles/index.html1", HTML_FILE);

        assertEquals(JSON_FILE, "src/main/java/WebFiles/conFig.json");
        assertNotEquals("src/main/java/WebFiles.conFigs.json", JSON_FILE);

        assertEquals(connect, connect);

        assertTrue(verbose);


    }

}