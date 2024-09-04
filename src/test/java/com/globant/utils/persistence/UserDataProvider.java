package com.globant.utils.persistence;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserDataProvider {
    private static final String emailHost = "mail.temp";

    private static final String SAVE_FILE_PATH = "src/test/resources/registeredUsersData.csv";
    private static final File saveFile = new File(UserDataProvider.SAVE_FILE_PATH);
    private static CSVWriter csvWriter;
    private static CSVReader csvReader;

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
            throw new RuntimeException(e);
        }
    }

    private static List<String[]> getUserDataOnFile() throws RuntimeException {
        try {
            UserDataProvider.csvReader = new CSVReader(new FileReader(UserDataProvider.saveFile));
            List<String[]> data = UserDataProvider.csvReader.readAll();
            UserDataProvider.csvReader.close();

            return data;
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getSignUpUserDataAsList() {
        String email = UserDataProvider.generateUniqueEmail();
        String password = UserDataProvider.generatePassword();

        String[] userData = {email, password};
        UserDataProvider.saveUserDataOnFile(userData);

        return Collections.singletonList(userData);
    }

    public List<String[]> getLoginUserDataAsList() {
        return UserDataProvider.getUserDataOnFile();
    }

    @DataProvider(name = "user-signup")
    public Object[][] getSignUpUserData() {
        List<String[]> data = this.getSignUpUserDataAsList();
        String email = data.get(0)[0];
        String password = data.get(0)[1];

        return new Object[][]{{email, password}};
    }

    @DataProvider(name = "user-login")
    public Object[][] getLoginUserData() {
        List<String[]> data = this.getLoginUserDataAsList();
        if (data.isEmpty() || data.get(0).length < 2)
            throw new RuntimeException("DataProvider - Login: Invalid data");

        String[] userData = data.get(0);
        return new Object[][]{{userData[0], userData[1]}};
    }

}
