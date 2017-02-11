package pl.grzegorz2047.maybeagame.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Taki tam test wczytywania prostych rozszerzeń
 */
public class ExtensionLoader {

    public boolean loadExtensions() throws MalformedURLException {
        File extensionsDirectory = new File("extensions");
        if (!extensionsDirectory.isDirectory()) {
            return extensionsDirectory.mkdir();
        }
        File[] jars = extensionsDirectory.listFiles();
        if (jars == null || jars.length == 0) return false;
        for (File jar : jars) {
            if (!jar.getName().endsWith(".jar")) continue;
            try {
                ZipInputStream zip = new ZipInputStream(new FileInputStream(jar.getPath()));
                JarFile jarFile = new JarFile(jar);
                System.out.println("PATH: " + jar.getPath());
                System.out.println("URI: " + jar.toURI());
                System.out.println("URL: " + jar.toURI().toURL());
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                    if (!entry.isDirectory() && entry.getName().equals("extension.info")) {
                        Scanner scanner = new Scanner(jarFile.getInputStream(entry));
                        if(!scanner.hasNextLine()){
                            Logger.getLogger("MaybeAGame").log(Level.SEVERE, "Jar file named " + jar.getName() + " doesnt have extension.info");
                            break;
                        }
                        String line = scanner.nextLine();
                        String[] partsOfLine = line.split(":");
                        String classPath = "";
                        if(partsOfLine.length == 2){
                            if(partsOfLine[0].equals("main")){
                                classPath = partsOfLine[1];
                            }
                        }else {
                            break;
                        }
                        System.out.println("Znalazlem plik!");
                        URL url = jar.toURI().toURL();

                        Class[] parameters = new Class[]{URL.class};

                        URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                        Class<URLClassLoader> sysClass = URLClassLoader.class;
                        try {
                            Method method = sysClass.getDeclaredMethod("addURL", parameters);
                            method.setAccessible(true);
                            method.invoke(sysLoader, url);

                            Constructor cs = ClassLoader.getSystemClassLoader().loadClass(classPath).getConstructor();
                            Extension instance = (Extension) cs.newInstance();
                            instance.onEnable();
                        } catch (Exception ex) {
                            System.err.println("Nie udalo sie wczytac " + jar.getName() + "! Sprawdz poprawnosc sciezki main! " + ex.getMessage());
                            break;
                        }
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
