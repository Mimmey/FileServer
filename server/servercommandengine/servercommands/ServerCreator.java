package server.servercommandengine.servercommands;

import server.helpers.idMap.IdMap;
import server.helpers.HttpStatuses;
import server.helpers.StringAndPathWorkers;
import server.helpers.Printer;

import java.io.*;

public class ServerCreator extends ServerCommand {

    public ServerCreator(String data, DataInputStream input, DataOutputStream output, BufferedReader reader, Printer printer) {
        super(data, input, output, reader, printer);
    }

    @Override
    public boolean apply() throws IOException {
        String filename = StringAndPathWorkers.getFirstWord(this.data);
        File file = new File(StringAndPathWorkers.getServerPath(filename));

        if (file.exists()) {
            output.writeUTF(Integer.toString(HttpStatuses.FORBIDDEN.getCode()));
            return true;
        }

        file.createNewFile();
        int id = IdMap.put(filename);

        String response = HttpStatuses.OK.getCode() + " " + id;
        output.writeUTF(response);

        return true;
    }
}
