import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class WebServer implements Runnable {

    static final File WEB_ROOT = new File(".");
    static final File HTML_FILE = new File("src/main/java/WebFiles/index.html");
    static final File JSON_FILE = new File("src/main/java/WebFiles/student.json");
    static  int PORT = 7070;
    static final boolean verbose = true;
    private final Socket connect;

    public WebServer(Socket c) {
        connect = c;
    }

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket connectToServer = new ServerSocket(PORT);
            System.out.println("Web Server Started. Listening for Connection on Port " + PORT + "...");
            while(true){
                WebServer myServer = new WebServer(connectToServer.accept());
                if(verbose) {
                    System.out.println("Connection is Opened.(" + new Date() +")");
                }

                //Dedicated Thread to Manage the Client Connection
                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection Error: " + e.getMessage());
        }

    }

    @Override
    public void run(){
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileRequested = null;
        try {
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            String input = in.readLine();
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase();
            fileRequested = parse.nextToken().toLowerCase();

            if(!method.equals("GET") && !method.equals("HEAD")){
                if(verbose) {
                    System.out.println("Not Supported: " + method + "method.");
                }
                }
                    else {

                    if(fileRequested.endsWith("/")){
                        fileRequested += HTML_FILE;
                    }

                    else if (fileRequested.endsWith("/json/")){
                        fileRequested += JSON_FILE;
                    }



                    File file = new File(WEB_ROOT, fileRequested);
                    int fileLength = (int) file.length();
                    String content = getContentType(fileRequested);

                    if(method.equals("GET")) {
                        byte[] fileData = readFileData(file, fileLength);
                        out.println("HTTP/1.1 200 OKAY");
                        out.println("Server: Web Server");
                        out.println("Date: " + new Date());
                        out.println("Content-type: " + content);
                        out.println("Content-length: " + fileLength);
                        out.println();
                        out.flush();

                        dataOut.write(fileData, 0, fileLength);
                        dataOut.flush();

                }

                    if(verbose) {
                        System.out.println("\nStatus: Working\n");
                        System.out.println("HTTP/1.1 200 OKAY");
                        System.out.println("Server: Web Server");
                        System.out.println("Date: " + new Date());
                        System.out.println("File: " + fileRequested );
                        System.out.println("Content-Type: " + content);
                        System.out.println("Content-Length: " + fileLength);

            } }
        }
        catch (FileNotFoundException fileNotFoundException) {
            try {
                fileNotFound(fileRequested);
            } catch (IOException ioe) {
                System.out.println("Error with File not Found exception: " + ioe.getMessage());
            }
        } catch (IOException ioe) {
                System.out.println("server error" + ioe );
            } finally {
                try {
                    in.close();
                    out.close();
                    dataOut.close();
                    connect.close();

                } catch (Exception e) {
                    System.out.println("Error Closing stream" + e.getMessage());
                }

                if (verbose) {
                    System.out.println("\nConnection Closed");
                }
            }
        }

        private byte[] readFileData (File file, int fileLength) throws IOException {
            FileInputStream fileIn = null;
            byte[] fileData = new byte[fileLength];
            try {
                fileIn = new FileInputStream(file);
                fileIn.read(fileData);

            } finally {
                if (fileIn != null)
                    fileIn.close();
            }

            return fileData;
        }



            private String getContentType(String fileRequested){
            if(fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
                return "text/html";
            else if(fileRequested.endsWith(".css"))
                return "text/css";
            else if(fileRequested.endsWith(".json"))
                return "application/json";
            else if(fileRequested.endsWith(".js"))
                return "text/javascript";
            else return "text/plain";
            }


            private void fileNotFound (String fileRequested) throws IOException {
                System.out.println("404 Not Found");
                if(verbose) {
                    System.out.println("File: " + fileRequested + " not Found");
                }

        }
    }