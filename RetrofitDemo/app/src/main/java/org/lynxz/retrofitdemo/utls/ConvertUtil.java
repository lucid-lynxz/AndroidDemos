package org.lynxz.retrofitdemo.utls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zxz on 2016/5/25.
 */
public class ConvertUtil {
    private static final int BUFFER_SIZE = 1000;

    public static String inputStreamToString(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, 1000)) != -1) {
            outStream.write(data, 0, count);
        }
        data = null;
        return new String(outStream.toByteArray(), "utf-8");
    }
}
