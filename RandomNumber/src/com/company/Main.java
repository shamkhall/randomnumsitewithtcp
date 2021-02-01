package com.company;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
            message();
    }



    public static void message() throws Exception{
        ServerSocket ss = new ServerSocket(8000);
        while(true){
            System.out.println("Server gozleyir...");
            Socket connect = ss.accept();

            DataInputStream dt = new DataInputStream(connect.getInputStream());


            OutputStream outputStream = connect.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String content = readFile("index.html");
            String pattern = "\\{id\\}";

            String id = regex(content, pattern); //

            String s=String.valueOf(random());
            content = content.replace(id, s);

            writeResponse(dataOutputStream, content );
            System.out.println();
            connect.close();
        }
    }

    // this method send response
    private static void writeResponse (OutputStream out, String s) throws Exception{
        String response = "HTTP/1.1 200 OK\r\n"
                + "Server: YarServer/2009-09-09\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + s.length() + "\r\n"
                + "Connection: close\r\n\r\n";
        String result  = response + s;
        out.write(result.getBytes());
        out.flush();

    }


    // this method read html file
    public static String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String content = contentBuilder.toString();
        return content;
    }

    public static String regex (String content, String pattern){

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(content);
        if (m.find( )) {
            return m.group(0);

        }else {
            return "0";
        }
    }

    public static int random (){
        Random rand = new Random();

        int randNumber = rand.nextInt();
        if (randNumber > 0)
            return randNumber;
        else
            return -randNumber;


    }




}
