package client.clientcommandengine.clientcommands;
import client.clientcommandengine.helpers.ReadReadyStatuses;
import server.helpers.HttpStatuses;
import server.helpers.Parsers;
import server.helpers.Printer;
import java.io.*;
import java.nio.file.Files;
import java.rmi.ServerException;

public class ClientCreator extends ClientCommand {

    public ClientCreator(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        StringBuilder request = new StringBuilder("PUT ");

        printer.printEnterFilename();
        String filename = reader.readLine();

        printer.printEnterFilenameOnServer();
        String serverFilename = reader.readLine();
        if (serverFilename.isEmpty()) {
            serverFilename = Parsers.generateFilename();
        }

        request.append(serverFilename).append(" ");

        output.writeUTF(request.toString());
        printer.printRequestSent();
        /*
        int ready = Integer.parseInt(input.readUTF());
        if (ready == ReadReadyStatuses.READY.getCode()) {
            try {
                File file = new File(Parsers.getClientPath(filename));
                byte[] message = Files.readAllBytes(file.toPath());
                output.writeInt(message.length);
                output.write(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new ServerException("Cannot send file");
        }
        */

        String response = input.readUTF();

        HttpStatuses status = HttpStatuses.FORBIDDEN;

        try {
            int statusCode = Integer.parseInt(Parsers.getFirstWord(response));
            status = HttpStatuses.findByCode(statusCode);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        switch (status) {
            case OK:
                int id = Integer.parseInt(Parsers.getStringWithoutFirstWord(response));
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
