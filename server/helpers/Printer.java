package server.helpers;

import java.io.BufferedWriter;
import java.io.IOException;

public class Printer implements AutoCloseable {
    private BufferedWriter writer;

    public Printer(BufferedWriter writer) {
        this.writer = writer;
    }

    public void close() throws IOException {
        writer.close();
    }

    public void printServerStarted() throws IOException {
        writer.write("Server started!\n");
        writer.flush();
    }
}
