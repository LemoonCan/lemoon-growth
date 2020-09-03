package main.javabasic.io;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lee
 * @date 2020-08-28
 */
public class FileDownload {
    public static void main(String[] args) {
        FileDownload fd = new FileDownload();
        fd.download("https://www.baidu.com");
    }

    void download(String s) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL u = new URL(s);
            URLConnection uc = u.openConnection();
            is = uc.getInputStream();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            String contentType = uc.getContentType();
            String fileType = contentType.split(";")[0];
            fos = new FileOutputStream("Downloads/test." + fileType.substring(fileType.indexOf("/") + 1));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = bufferedInputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
        } catch (Exception e) {
        } finally {
            try {
                if (is != null) {

                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
