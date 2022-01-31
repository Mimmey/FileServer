package client.clientcommandengine.clientcommandfactory;

import server.helpers.CommandTypes;
import client.clientcommandengine.clientcommands.*;
import server.helpers.Printer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientCommandCreator extends ClientCommandFactory {

    public ClientCommandCreator(BufferedReader reader, Printer printer) {
        super(reader, printer);
    }

    @Override
    public ClientCommand createCommand(int indexOfCommand, DataInputStream input, DataOutputStream output) {
        CommandTypes commandType = CommandTypes.findByIndex(indexOfCommand);

        switch (commandType) {
            case GET:
                return new ClientGetter(input, output, this.reader, this.printer);
            case CREATE:
                return new ClientCreator(input, output, this.reader, this.printer);
            case DELETE:
                return new ClientDeleter(input, output, this.reader, this.printer);
            default:
                return new ClientExitter(input, output, this.reader, this.printer);
        }
    }
}
