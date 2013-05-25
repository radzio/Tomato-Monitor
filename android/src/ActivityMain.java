import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.SherlockActivity;
import net.droidlabs.tomatomonitor.R;
import net.droidlabs.tomatomonitor.api.TomatoApi;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityMain extends SherlockActivity implements OnItemClickListener
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
                   TomatoApi tomatoApi = new TomatoApi("admin", "8600MGS", "http://10.89.9.1");
                   Log.e("TEST",  "A " + tomatoApi.getDevices());
                   Log.e("TEST",  "A " + tomatoApi.getStatusData());
                   Log.e("TEST",  "A " + tomatoApi.getNetDev());
                   Log.e("TEST",  "A " + tomatoApi.getIpBw());
                try
                {
                    JSONObject js =  new JSONObject(tomatoApi.getStatusData());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        }.start();






    }
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

    }

    static class LauncherIcon {
        final String text;
        final int imgId;
        final String map;

        public LauncherIcon(int imgId, String text, String map) {
            super();
            this.imgId = imgId;
            this.text = text;
            this.map = map;
        }

    }





}
