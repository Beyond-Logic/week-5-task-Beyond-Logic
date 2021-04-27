import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class WebServerTest {

    @org.junit.jupiter.api.Test
    void main() {

        final File WEB_ROOT = new File(".");
        final File HTML_FILE = new File("src/main/java/WebFiles/index.html");
        final File JSON_FILE = new File("src/main/java/WebFiles/conFig.json");
        int PORT = 8080;
        final boolean verbose = true;
        final Socket connect = new Socket();

        assertEquals(8080, PORT);
        assertNotEquals(7070, PORT);

        assertEquals(WEB_ROOT, WEB_ROOT);
        assertNotEquals(HTML_FILE, WEB_ROOT);

        assertEquals(JSON_FILE, JSON_FILE);
        assertNotEquals(HTML_FILE, JSON_FILE);

        assertEquals(connect, connect);

        assertTrue(verbose);


    }

}