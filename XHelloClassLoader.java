package weather.sunny.geek;

import com.google.common.primitives.Bytes;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XHelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception{
        Class<?> helloClass = new XHelloClassLoader().findClass("Hello");
        Object hello = helloClass.newInstance();
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        helloMethod.invoke(hello);
    }

    private static byte[] coversionFile() {
        String filePath = "C:\\Users\\LJC\\Desktop\\Hello.xlass";
        try {
            InputStream in = new FileInputStream(filePath);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] beforeBytes = coversionFile();
        List<Byte> afterList = new ArrayList<>();
        for(byte b : beforeBytes) {
            afterList.add((byte)(255 - b));
        }
        byte[] bytes = Bytes.toArray(afterList);
        return defineClass(name,bytes,0,bytes.length);
    }
    
}
