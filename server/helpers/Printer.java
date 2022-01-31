package server.helpers;

import java.io.BufferedWriter;
import java.io.IOException;

public class Printer implements AutoCloseable {
    private BufferedWriter writer;

    public Printer(BufferedWriter writer) {
        this.writer = writer;
    }

    public void close() throws IOException {
        writer.close();
    }

    public void printDirCreated() throws IOException {
        writer.write("The directory was successfully created.\n");
        writer.flush();
    }

    public void printDirNotCreated() throws IOException {
        writer.write("The directory was not created.\n");
        writer.flush();
    }

    public void printGetByNameOrById() throws IOException {
        writer.write("Do you want to get the file by name or by id (1 - name, 2 - id): ");
        writer.flush();
    }

    public void printDeleteByNameOrById() throws IOException {
        writer.write("Do you want to delete the file by name or by id (1 - name, 2 - id): ");
        writer.flush();
    }

    public void printEnterAction() throws IOException {
        writer.write("Enter action (1 - get a file, 2 - save a file, 3 - delete a file): ");
        writer.flush();
    }

    public void printEnterFilename() throws IOException {
        writer.write("Enter name of the file: ");
        writer.flush();
    }

    public void printEnterFilenameOnServer() throws IOException {
        writer.write("Enter name of the file to be saved on server: ");
        writer.flush();
    }

    public void printEnterFileContent() throws IOException {
        writer.write("Enter file content: ");
        writer.flush();
    }

    public void printEnterId() throws IOException {
        writer.write("Enter id: ");
        writer.flush();
    }

    public void printRequestSent() throws IOException {
        writer.write("The request was sent.\n");
        writer.flush();
    }

    public void printResponseAlreadyExists() throws IOException {
        writer.write("The response says that creating the file was forbidden!\n");
        writer.flush();
    }

    public void printResponseContentIs(String content) throws IOException {
        writer.write("The content of the file is: " + content + "\n");
        writer.flush();
    }

    public void printResponseFileSavedOnServer(int id) throws IOException {
        writer.write("Response says that file was created! ID = " + id + "\n");
        writer.flush();
    }

    public void printResponseFileSavedOnClient() throws IOException {
        writer.write("File saved on the hard drive!\n");
        writer.flush();
    }

    public void printResponseFileDeleted() throws IOException {
        writer.write("The response says that the file was successfully deleted!\n");
        writer.flush();
    }

    public void printResponseFileNotFound() throws IOException {
        writer.write("The response says that the file was not found!\n");
        writer.flush();
    }

    public void printResponseFileWasDownloaded() throws IOException {
        writer.write("The file was downloaded! Specify a name for it: ");
        writer.flush();
    }

    public void printResponseError() throws IOException {
        writer.write("Error\n");
        writer.flush();
    }

    public void printServerStarted() throws IOException {
        writer.write("Server started!\n");
        writer.flush();
    }
}
