package client.clientcommandengine.clientcommands;

import client.clientcommandengine.helpers.HttpStatuses;
import client.clientcommandengine.helpers.StringAndPathWorkers;
import client.clientcommandengine.helpers.Printer;

import java.io.*;
import java.rmi.ServerException;

public class ClientCreator extends ClientCommand {

    public ClientCreator(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        StringBuilder request = new StringBuilder("PUT ");

        printer.printEnterFilename();

        printer.printEnterFilenameOnServer();
        String serverFilename = reader.readLine();
        if (serverFilename.isEmpty()) {
            serverFilename = StringAndPathWorkers.generateFilename();
        }

        request.append(serverFilename).append(" ");

        output.writeUTF(request.toString());
        printer.printRequestSent();

        String response = input.readUTF();

        HttpStatuses status = HttpStatuses.FORBIDDEN;

        try {
            int statusCode = Integer.parseInt(StringAndPathWorkers.getFirstWord(response));
            status = HttpStatuses.findByCode(statusCode);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        switch (status) {
            case OK:
                int id = Integer.parseInt(StringAndPathWorkers.getStringWithoutFirstWord(response));
                printer.printResponseFileSavedOnServer(id);
                break;
            case FORBIDDEN:
                printer.printResponseAlreadyExists();
                break;
            case NOT_FOUND:
                printer.printResponseFileNotFound();
                break;
            default:
                throw new ServerException("");
        }

        return true;
    }
}
