package server.servercommandengine.servercommandfactory;

import server.helpers.Printer;
import server.servercommandengine.servercommands.ServerCommand;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class ServerCommandFactory {

    protected Printer printer;
    protected BufferedReader reader;

    ServerCommandFactory(BufferedReader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    abstract ServerCommand createCommand(String request, DataInputStream input, DataOutputStream output);
}
