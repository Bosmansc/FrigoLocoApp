package com.example.sheettutorial;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class ListAllGames extends AppCompatActivity {


    ListView listView;
    ListAdapter adapter;
    ProgressDialog loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_games);

        listView = findViewById(R.id.lv_games);

        getGames();

    }

    private void getGames() {

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.googleusercontent.com/macros/echo?user_content_key=WQEyx901HPt0NtfCWH_04YBrWS53RQxr5X1FnFtkK4IDv92Tg_oSQ-QjKbntrTk9K_3CybswKKR-W6a0ozfEr64k9thURt_Ym5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnMlkmrRl8uYlMsV6xgGdX7JaB5IxfeuALmEZDQyIqXkY0u18FaTYgjnYNPJua7JBkoc_azeePWaZYjHAbtibbXFiPFzang9C0g&lib=MwcRTHbOaXAOcLe4U_UtIkfgevZnNMH3F",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }

    private void parseItems(String jsonResposnce) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("games");

            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);
                String gameDate = jo.getString("Date");
                String ploeg = jo.getString("Ploeg");
                String plaats = jo.getString("Plaats");

                // String to date and add 2 hours
                SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date convertedDate = sourceFormat.parse(gameDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(convertedDate);
                cal.add(Calendar.HOUR, 2);
                convertedDate = cal.getTime();
                String gameConvDate = destFormat.format(convertedDate);

                // add items to hashMap
                HashMap<String, String> item = new HashMap<>();
                item.put("Date", gameConvDate);
                item.put("Ploeg", ploeg);
                item.put("Plaats",plaats);

                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        adapter = new SimpleAdapter(this,list,R.layout.list_game_row,
                new String[]{"Ploeg","Plaats","Date"},new int[]{R.id.tv_ploeg,R.id.tv_plaats,R.id.tv_date});

        listView.setAdapter(adapter);
        loading.dismiss();
    }


}