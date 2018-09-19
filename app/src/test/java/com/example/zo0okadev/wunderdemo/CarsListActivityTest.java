package com.example.zo0okadev.wunderdemo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertTrue;

public class CarsListActivityTest {
    private static final String ASSET_BASE_PATH = "../app/src/main/assets/";

    @Test
    public void jsonFileIsValid() {
        try {
            JSONObject jsonObject = new JSONObject(readJsonFile("locations.json"));
            JSONArray placemarks = jsonObject.getJSONArray("placemarks");
            assertTrue(placemarks.length() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readJsonFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ASSET_BASE_PATH + filename)));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    }
}
