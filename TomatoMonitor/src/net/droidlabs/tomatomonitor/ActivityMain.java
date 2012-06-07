package net.droidlabs.tomatomonitor;

import android.app.Activity;
import android.os.Bundle;

import android.text.Html;
import android.util.*;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActivityMain extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new Thread()
        {
            public void run()
            {
                try {
                    // URL url = new URL ("http://ip:port/download_url");
                    URL url = new URL("http://10.89.9.1/update.cgi");
                    String authStr = "";
                    String authEncoded = Base64.encodeBytes(authStr.getBytes());
                    Log.i("TEST", authEncoded);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "Basic " + authEncoded);
                    connection.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    String data = "exec=devlist&_http_id=TIDdf8a6ec71b1b7109";
                    wr.write(data);
                    wr.flush();


                    InputStream in = (InputStream) connection.getInputStream();

                    BufferedReader ina =
                            new BufferedReader (new InputStreamReader (in));
                    String line;
                    while ((line = ina.readLine()) != null) {
                        Log.i("TEST", line);
                    }


                    in.close();
                }
                catch (Exception e) {
                    Log.e("TEST", "ERRRRRRRRRRRRRROR");
                    e.printStackTrace();
                }





            }
        }.start();






    }



}