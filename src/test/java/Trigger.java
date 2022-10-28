import org.apache.commons.io.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Trigger {

    @Test
    public void test(){
        get();
    }


    public static void get(){
        String fileName = "";
        String sourceFilePath = "";

        String sourceFolderPath = "C:/Users/akifm/Downloads/";
        String targetFolderPath = "C:/Users/akifm/.jenkins/workspace/Test_Trigger_Build_Job_Demo/target/app/";

        //Check the source folder if it has the required file name
        File folder = new File(sourceFolderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().contains("emu")){
                fileName = file.getName();
                sourceFilePath = file.getAbsolutePath();
                break;
            }
        }

        //Define target file path
        String targetFilePath = targetFolderPath + fileName;

        System.out.println("The apk file to get : " + fileName);
        System.out.println("sourceFilePath = " + sourceFilePath);
        System.out.println("targetFolderPath = " + targetFolderPath);
        System.out.println("targetFilePath = " + targetFilePath);

        //Delete all the files inside the target folder except the target file(if already exists)
        File targetFolder = new File(targetFolderPath);
        File[] targetFolderFiles = targetFolder.listFiles();
        if (targetFolderFiles != null) {
            for (File file : targetFolderFiles) {
                if (!file.getName().equalsIgnoreCase(fileName)){
                    file.delete();
                }
            }
        }

        //Copy the source file from the source into the target. If already exists, skip the operation
        File source = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
        System.out.println("targetFile.getName() = " + targetFile.getName());

        System.out.println("targetFile.exists() = " + targetFile.exists());
        if (targetFile.exists()){
            System.out.println("Apk file already exists.");
        }else {
            try {
                FileUtils.copyFile(source, targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New apk file copied.");
        }
    }
}
