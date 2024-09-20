package com.project.interview;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6666);
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("Hello Server!");
        socket.close();

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + File.separator + "file.txt";
        // 写入文件
        try (FileWriter file = new FileWriter(filePath)) {
            file.write("Hello from Process A");
        } catch (IOException e) {
            e.printStackTrace();
        }

// 读取文件
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}