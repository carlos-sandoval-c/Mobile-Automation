package com.globant.utils.persistence;

import com.opencsv.CSVWriter;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDataProvider {
    private static final String emailHost = "mail.temp";

    private static final String SAVE_FILE_PATH = "src/test/resources/registeredUsersData.csv";
    private static final File saveFile = new File(UserDataProvider.SAVE_FILE_PATH);
    private static CSVWriter csvWriter;

    private static String generatePassword() {
        return String.format("%s", UUID.randomUUID().toString().substring(0, 10));
    }

    private static String generateUniqueId() {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 6), System.currentTimeMillis() / 1000);
    }

    private static String generateUniqueEmail() {
        return String.format("%s@%s", UserDataProvider.generateUniqueId(), UserDataProvider.emailHost);
    }

    private static void saveUserDataOnFile(String[] data) throws RuntimeException {
        try {
            UserDataProvider.csvWriter = new CSVWriter(new FileWriter(UserDataProvider.saveFile));

            List<String[]> newData = new ArrayList<>();
            newData.add(data);
            UserDataProvider.csvWriter.writeAll(newData);

            UserDataProvider.csvWriter.close();
        } catch (IOException e) {
            System.out.println("AYUDAAAAAAA-------");
            throw new RuntimeException(e);
        }
    }

    @DataProvider(name = "user-signup")
    public Object[][] getSignUpUserData() {
        String email = UserDataProvider.generateUniqueEmail();
        String password = UserDataProvider.generatePassword();

        String[] userData = {email, password};
        UserDataProvider.saveUserDataOnFile(userData);

        return new Object[][]{{email, password}};
    }

}
