package com.groceryfinder.android.groceryfinder;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaiMacBookAir on 4/27/17.
 */

public class GroceryFetcher {

    private static final String TAG = "GroceryFetcher";
    private static final String API_KEY = "7c1fa44418";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw  new IOException(connection.getResponseMessage() +
                        ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<GroceryStore> fetchStores(String zip) {
        List<GroceryStore> stores = new ArrayList<>();
        try {
            String url = Uri.parse("http://www.SupermarketAPI.com/api.asmx/StoresByZip")
                    .buildUpon()
                    .appendQueryParameter("APIKEY", API_KEY)
                    .appendQueryParameter("ZipCode", zip)
                    .build().toString();
            String xml = getUrlString(url);
            Log.i(TAG, "Recieved JSON: " + xml);

            JSONObject jsonBody = null;
            try {
                jsonBody = XML.toJSONObject(xml);
            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }

            Log.d("XML", xml);

            Log.d("JSON", jsonBody.toString());

            //JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(stores, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return stores;
    }

    private void parseItems(List<GroceryStore> items, JSONObject jsonBody) throws IOException, JSONException {
        JSONObject storeJsonObject = jsonBody.getJSONObject("ArrayOfStore");
        JSONArray storeJsonObjectJSONArray = storeJsonObject.getJSONArray("Store");

        Gson gson = new Gson();
        for (int i = 0; i < storeJsonObjectJSONArray.length(); i++) {
            JSONObject photoJsonObject = storeJsonObjectJSONArray.getJSONObject(i);
            GroceryStore store = gson.fromJson(photoJsonObject.toString(), GroceryStore.class);
            items.add(store);
        }
    }
}
