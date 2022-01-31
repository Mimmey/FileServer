package server.servercommandengine.servercommandfactory;

import server.helpers.CommandTypes;
import server.helpers.Parsers;
import server.helpers.Printer;
import server.servercommandengine.servercommands.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ServerCommandCreator extends ServerCommandFactory {

    public ServerCommandCreator(BufferedReader reader, Printer printer) {
        super(reader, printer);
    }

    private CommandTypes getCommandType(String request) {
        String method = Parsers.getFirstWord(request);

        switch (method) {
            case "PUT":
                return CommandTypes.CREATE;
            case "GET":
                return CommandTypes.GET;
            case "DELETE":
                return CommandTypes.DELETE;
            default:
                return CommandTypes.EXIT;
        }
    }

    @Override
    public ServerCommand createCommand(String request, DataInputStream input, DataOutputStream output) {
        CommandTypes commandType = getCommandType(request);
        String data = Parsers.getStringWithoutFirstWord(request);

        switch (commandType) {
            case GET:
                return new ServerGetter(data, input, output, this.reader, this.printer);
            case CREATE:
                return new ServerCreator(data, input, output, this.reader, this.printer);
            case DELETE:
                return new ServerDeleter(data, input, output, this.reader, this.printer);
            default:
                return new ServerExitter(data, input, output, this.reader, this.printer);
        }
    }
}
