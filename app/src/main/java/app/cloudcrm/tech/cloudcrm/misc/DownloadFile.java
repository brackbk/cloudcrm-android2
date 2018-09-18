package app.cloudcrm.tech.cloudcrm.misc;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class DownloadFile extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... strings) {
        String fileUrl = strings[0].replace("/var/www/", "");;   // -> http:/cloud.cloudcrm.tech/tmp/nome_pdf.pdf
        String fileName = strings[1].replace("/var/www/tmp/", "");  // -> nome_pdf.pdf
        String dirSV = strings[2];// diretorio para salvar pdf
        Log.d("UPLOAD", fileUrl);
        Log.d("UPLOAD", fileName);
        Log.d("UPLOAD", dirSV);

        String extStorageDirectory = Environment.getDataDirectory().toString();
        try{
            File directory = new File(dirSV);
            //File directory =  ctx.getDir("my_sub_dir", Context.MODE_PRIVATE);
            //File folder = new File(extStorageDirectory, "cloudCRM_pdfs");
            //folder.mkdir();
            if (!directory.exists()) {
                directory.mkdir();
            }

            File pdfFile = new File(directory, fileName);
            pdfFile.createNewFile();
            FileDownloader.downloadFile(fileUrl, pdfFile);

        }catch (IOException e){
            Log.d("UPLOAD", e.getMessage());
            Log.d("UPLOAD", extStorageDirectory);
        }
        return null;
    }
}
