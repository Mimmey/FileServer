package client.clientcommandengine.clientcommands;
import server.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientExitter extends ClientCommand {

    public ClientExitter(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        printer.printRequestSent();
        output.writeUTF("EXIT");
        return false;
    }
}
