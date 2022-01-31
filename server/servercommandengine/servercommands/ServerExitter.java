package server.servercommandengine.servercommands;

import server.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerExitter extends ServerCommand {

    public ServerExitter(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(data, input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        return false;
    }
}
