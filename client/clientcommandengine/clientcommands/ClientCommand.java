package client.clientcommandengine.clientcommands;
import server.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ClientCommand {

    protected DataInputStream input;
    protected DataOutputStream output;
    protected Printer printer;
    protected BufferedReader reader;

    public ClientCommand(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        this.input = input;
        this.output = output;
        this.reader = reader;
        this.printer = printer;
    }

    public abstract boolean apply() throws IOException;
}
