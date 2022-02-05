package client.clientcommandengine.clientcommandfactory;

import client.clientcommandengine.clientcommands.ClientCommand;
import client.clientcommandengine.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class ClientCommandFactory {

    protected Printer printer;
    protected BufferedReader reader;

    ClientCommandFactory(BufferedReader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    abstract ClientCommand createCommand(int indexOfCommand, DataInputStream input, DataOutputStream output);
}
