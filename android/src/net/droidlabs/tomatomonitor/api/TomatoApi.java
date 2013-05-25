package api;

import android.util.Log;
import net.droidlabs.tomatomonitor.Base64;
import net.droidlabs.tomatomonitor.model.Bandwidth;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Radek Piekarz
 * Date: 07.07.12
 * Time: 11:20
 */
public class TomatoApi
{
    private String credientials;
    private String address;
    private String httpId;
    /**
     *
     * @param login
     * @param pass
     */
    public TomatoApi(String login, String pass, String address)
    {
        this.credientials = Base64.encodeBytes(String.format("%s:%s", login, pass).getBytes());
        this.address = address;
        initialize();
    }

    private void initialize()
    {
         this.httpId = "&_http_id=" + TomatoIdGetter.getId(this.makeRequest(this.address, null, "GET"));
    }

    private String makeRequest(String address, String data, String method)
    {
        try
        {
            URL url = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", "Basic " + this.credientials);
            connection.setDoOutput(true);
            if(method.equals("POST"))
            {
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                if(data != null)
                {
                    data = data + this.httpId;
                    wr.write(data);
                }
                else
                {
                    wr.write(this.httpId);
                }
                wr.flush();
            }


            InputStream in = connection.getInputStream();

            BufferedReader ina   =
                    new BufferedReader (new InputStreamReader(in));
            String line;
            StringBuffer buff = new StringBuffer();
            while ((line = ina.readLine()) != null) {
                buff.append(line);
            }
            in.close();
            return buff.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private String makeGETRequest(String address, String data)
    {
        return this.makeRequest(address + "?_http_id=" + this.httpId, data, "GET");
    }

    private String makePOSTRequest(String address, String data)
    {
        return this.makeRequest(address, data, "POST");
    }

    public HashMap<String, Bandwidth> getNetDev()
    {
        try
        {
            String result = this.makePOSTRequest(this.address + "/" + "update.cgi", "&exec=netdev")
                    .replace("'", "\"")
                    .replace("netdev=", "")
                    .replace(";", "");

            Log.e("TomatoApi", "Result = " + result);

            JSONObject interfaces = new JSONObject(result);
            JSONArray intefaceNames = interfaces.names();
            for(int i = 0; i<intefaceNames.length(); i++)
            {
                String interfaceName = intefaceNames.getString(i);
                JSONObject tomatoInterface = interfaces.getJSONObject(interfaceName);
                String rx = tomatoInterface.getString("rx");
                String tx = tomatoInterface.getString("tx");
                Log.e("TomatoApi", "RX = " + rx);
                Log.e("TomatoApi", "TX = " + rx);
                Bandwidth bandWidth = new Bandwidth();
                bandWidth.setTomatoInterface(interfaceName);
                bandWidth.setRx(rx);
                bandWidth.setTx(tx);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String getDevices()
    {
        return this.makePOSTRequest(this.address + "/" + "update.cgi", "&exec=devlist")
                .split(";")[0]
                .replace("'", "\"")
                .replace(";", "");
    }

    public String getIpBw()
    {
        return this.makePOSTRequest(this.address + "/" + "update.cgi", "&exec=iptmon").split(";")[0].replace("'", "\"")
                .replace(";", "")
                .replace("rx:", "\"rx\":\"")
                .replace(",tx:","\",\"tx\":\"" )
                .replace("},",  "\"},")
                .replace("}}", "\"}}");
    }



    public String getStatusData()
    {
        String a =  this.makePOSTRequest(this.address + "/" + "status-data.jsx", null);
        return a.split("//")[1]
                .replace("nvram =", "")
                .replace("'", "\"")
                .replace(";", "");
    }


}
