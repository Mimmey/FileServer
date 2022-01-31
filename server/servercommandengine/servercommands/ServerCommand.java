package server.servercommandengine.servercommands;

import server.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ServerCommand {

    protected String data;
    protected DataInputStream input;
    protected DataOutputStream output;
    protected Printer printer;
    protected BufferedReader reader;

    public ServerCommand(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        this.input = input;
        this.data = data;
        this.output = output;
        this.reader = reader;
        this.printer = printer;
    }

    public abstract boolean apply() throws IOException;
}
