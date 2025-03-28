package javabasic.io.basic.diffstream;

import javabasic.io.basic.PathManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author lee
 * @since 2020-08-28
 */
public class FileDownload {
    public static void main(String[] args) {
        FileDownload fd = new FileDownload();
        PathManager.checkOutputDir();
        fd.download("https://www.baidu.com", PathManager.OUTPUT_DIR);
    }

    void download(String s, String downloadPath) {

        try {
            URL u = new URL(s);
            URLConnection uc = u.openConnection();
            String fileType = uc.getContentType().split(";")[0];

            //bos无需显式flush，close方法会调用flush，保证缓冲区的内容都写入到了文件
            try (BufferedInputStream bis = new BufferedInputStream(uc.getInputStream());
                 BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(
                         Paths.get(downloadPath + stitchFileName("baidu.", fileType))
                 ))) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static String stitchFileName(String prefix, String fileType) {
        return prefix + fileType.substring(fileType.indexOf("/") + 1);
    }
}


