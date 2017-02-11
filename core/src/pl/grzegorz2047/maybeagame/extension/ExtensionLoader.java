package pl.grzegorz2047.maybeagame.extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        File extensionsDirectory = createExtensionsFileIfNotExists();
        File[] jars = extensionsDirectory.listFiles();
        if (isExtensionsDirectoryEmpty(jars)) return false;
        for (File jar : jars) {
            if (!hasJarExtension(jar)) continue;
            try {
                ZipInputStream zip = new ZipInputStream(new FileInputStream(jar.getPath()));
                JarFile jarFile = new JarFile(jar);
                getContentOfJarAndLoadIt(jar, zip, jarFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void getContentOfJarAndLoadIt(File jar, ZipInputStream zip, JarFile jarFile) throws IOException {
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (isItExtentionSettingFile(entry)) {
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
                    Extension instance = getInstanceOfExtension(classPath, url, parameters, sysLoader, sysClass);
                    instance.onEnable();
                } catch (Exception ex) {
                    System.err.println("Nie udalo sie wczytac " + jar.getName() + "! Sprawdz poprawnosc sciezki main! " + ex.getMessage());
                    break;
                }
                break;
            }
        }
    }

    private Extension getInstanceOfExtension(String classPath, URL url, Class[] parameters, URLClassLoader sysLoader, Class<URLClassLoader> sysClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Method method = sysClass.getDeclaredMethod("addURL", parameters);
        method.setAccessible(true);
        method.invoke(sysLoader, url);

        Constructor cs = ClassLoader.getSystemClassLoader().loadClass(classPath).getConstructor();
        return (Extension) cs.newInstance();
    }

    private boolean isItExtentionSettingFile(ZipEntry entry) {
        return !entry.isDirectory() && entry.getName().equals("extension.info");
    }

    private boolean hasJarExtension(File jar) {
        return jar.getName().endsWith(".jar");
    }

    private boolean isExtensionsDirectoryEmpty(File[] jars) {
        return jars == null || jars.length == 0;
    }

    private File createExtensionsFileIfNotExists() {
        File extensionsDirectory = new File("extensions");
        if (!extensionsDirectory.isDirectory()) {
            extensionsDirectory.mkdir();
        }
        return extensionsDirectory;
    }
}
