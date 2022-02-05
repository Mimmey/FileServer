package client.clientcommandengine.clientcommands;
import client.clientcommandengine.helpers.*;

import java.io.*;
import java.rmi.ServerException;

public class ClientGetter extends ClientCommand {

    public ClientGetter(DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(input, output, reader, printer);
    }

    public HttpStatuses renameFile(File file) throws IOException {
        printer.printResponseFileWasDownloaded();
        String newFilename = reader.readLine();

        if (newFilename.isEmpty()) {
            newFilename = StringAndPathWorkers.generateFilename();
        }

        File newFile = new File(StringAndPathWorkers.getClientPath(newFilename));

        if (newFile.exists())
            return HttpStatuses.FORBIDDEN;

        boolean success = file.renameTo(newFile);

        if (success) {
            return HttpStatuses.OK;
        } else {
            return HttpStatuses.ERROR;
        }
    }

    @Override
    public boolean apply() throws IOException {
        StringBuilder request = new StringBuilder("GET ");

        printer.printGetByNameOrById();
        int type = Integer.parseInt(reader.readLine());
        String subject;

        if (type == IdOrNameStatuses.NAME.getCode()) {
            printer.printEnterFilename();
        } else {
            printer.printEnterId();
        }

        subject = reader.readLine();
        request.append(type).append(" ").append(subject);

        output.writeUTF(request.toString());
        printer.printRequestSent();

        File file = new File(StringAndPathWorkers.getClientPath(StringAndPathWorkers.generateFilename()));
        HttpStatuses status = HttpStatuses.NOT_FOUND;

        if (file.exists()) {
            status = HttpStatuses.FORBIDDEN;
        } else {
            int ready = Integer.parseInt(input.readUTF());
            String response = Integer.toString(HttpStatuses.NOT_FOUND.getCode());

            if (ready == ReadReadyStatuses.READY.getCode()) {
                try (OutputStream os = new FileOutputStream(file)) {
                    int messageLength = input.readInt();
                    byte[] message = new byte[messageLength];
                    input.readFully(message, 0, message.length);
                    output.writeUTF(Integer.toString(ReadReadyStatuses.READY.getCode()));
                    os.write(message);
                    response = Integer.toString(input.readInt());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                status = HttpStatuses.NOT_FOUND;
            }

            try {
                int statusCode = Integer.parseInt(StringAndPathWorkers.getFirstWord(response));
                status = HttpStatuses.findByCode(statusCode);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            if (status == HttpStatuses.OK) {
                status = renameFile(file);
            }
        }

        switch (status) {
            case OK:
                printer.printResponseFileSavedOnClient();
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
