package app.cloudcrm.tech.cloudcrm.misc;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloader {
    private static final int  MEGABYTE = 1024 * 1024;

    public static void downloadFile(String fileUrl, File directory){
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory() + "/testesovai.pdf";
            File outputFile = directory;
            //outputFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream is = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();



//            URL url = new URL(fileUrl);
//            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//            //urlConnection.setRequestMethod("GET");
//            //urlConnection.setDoOutput(true);
//            urlConnection.connect();
//
//            InputStream inputStream = urlConnection.getInputStream();
//            FileOutputStream fileOutputStream = new FileOutputStream(directory);
//            int totalSize = urlConnection.getContentLength();
//
//            byte[] buffer = new byte[MEGABYTE];
//            int bufferLength = 0;
//            while((bufferLength = inputStream.read(buffer))>0 ){
//                fileOutputStream.write(buffer, 0, bufferLength);
//            }
//            fileOutputStream.close();





//            URL url = new URL(fileUrl);//Create Download URl
//            HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
//            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
//            c.connect();//connect the URL Connection
//
//            //If Connection response is not OK then show Logs
//            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                Log.d("UPLOAD", "Server returned HTTP " + c.getResponseCode()
//                        + " " + c.getResponseMessage());
//
//            }
//
//
//            File outputFile = directory;//Create Output file in Main File
//
//            //Create New File if not present
//            if (!outputFile.exists()) {
//                outputFile.createNewFile();
//               // Log.e(TAG, "File Created");
//            }
//
//            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location
//
//            InputStream is = c.getInputStream();//Get InputStream for connection
//
//            byte[] buffer = new byte[1024];//Set buffer type
//            int len1 = 0;//init length
//            while ((len1 = is.read(buffer)) != -1) {
//                fos.write(buffer, 0, len1);//Write new file
//            }
//
//            //Close all connection after doing task
//            fos.close();
//            is.close();



        } catch (FileNotFoundException e) {
            Log.d("UPLOAD",e.getMessage());
        } catch (MalformedURLException e) {
            Log.d("UPLOAD",e.getMessage());
        } catch (IOException e) {
            Log.d("UPLOAD",e.getMessage());
        } catch (Exception e){
            Log.d("UPLOAD",e.getMessage());
        }
    }
}