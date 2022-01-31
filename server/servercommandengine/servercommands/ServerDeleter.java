package server.servercommandengine.servercommands;

import server.helpers.HttpStatuses;
import server.helpers.Parsers;
import server.helpers.Printer;
import server.helpers.idMap.IdMap;

import java.io.*;
import java.util.Optional;

public class ServerDeleter extends ServerCommand {

    public ServerDeleter(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(data, input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        try {
            boolean isName = Integer.parseInt(Parsers.getFirstWord(data)) == 1;
            Optional<String> filename = null;
            boolean deletedByName = false;
            boolean deletedByFilename = false;

            if (isName) {
                filename = Optional.of(Parsers.getStringWithoutFirstWord(data));
                deletedByName = IdMap.deleteByFilename(filename.get());
            } else {
                int id = Integer.parseInt(Parsers.getStringWithoutFirstWord(data));
                try {
                    filename = IdMap.deleteById(id);
                    deletedByFilename = filename.isPresent();
                } catch (NullPointerException e) {
                    deletedByFilename = false;
                }
            }

            if ((deletedByFilename || deletedByName) && (new File(Parsers.getServerPath(filename.get()))).delete()) {
                output.writeUTF(Integer.toString(HttpStatuses.OK.getCode()));
            } else {
                output.writeUTF(Integer.toString(HttpStatuses.NOT_FOUND.getCode()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
