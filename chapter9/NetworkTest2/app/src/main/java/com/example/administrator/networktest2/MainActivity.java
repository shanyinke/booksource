package com.example.administrator.networktest2;

import android.net.UrlQuerySanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        Button sendRequestok = (Button) findViewById(R.id.send_request_okHttp) ;
        Button sendRequestHttpUtil =(Button) findViewById(R.id.send_request_httpUtil) ;
        responseText = (TextView) findViewById(R.id.response_test);
        sendRequest.setOnClickListener(this);
        sendRequestok.setOnClickListener(this);
        sendRequestHttpUtil.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.send_request){

            sendRequestWithHttpURLConnection();

        }else if (view.getId() == R.id.send_request_okHttp){
            sendRequestWithokHttp();
        }else if (view.getId() == R.id.send_request_httpUtil){

            HttpUtil.sendOKHttpRequest("http://www.paojiang.cn/",new okhttp3.Callback(){
                @Override
                public void onResponse(Call call, Response response) throws  IOException{
                    String responseData = response.body().string();
                    showResponse(responseData);
                }
                @Override
                public void onFailure(Call call,IOException e){
                    e.printStackTrace();
                }
            });
        }
    }
    private void  sendRequestWithokHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request= new Request.Builder()
                            //指定访问的服务器地址
                            .url("http://www.2605h.cn/usercenter/cp_data1.php")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseDada=  response.body().string();
                   // showResponse(responseDada);
                    //parseXMLWithPull(responseDada);
                   // parseXMlWithSAX(responseDatda);
                    //parseJSONwithJSONObject(responseDada);
                    parseJSONwithGSON2(responseDada);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONwithGSON(String jsonData){
      Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d("MainActivity","id is "+ app.getId());
            Log.d("MainActivity","name is "+ app.getName());
            Log.d("MainActivity","getVersion is "+ app.getVersion());
        }
    }

    private void parseJSONwithGSON2(String jsonData){
        Gson gson = new Gson();
        List<Cpdata> appList = gson.fromJson(jsonData,new TypeToken<List<Cpdata>>(){}.getType());
        for(Cpdata cpdata:appList){
            Log.d("MainActivity","cpid is "+ cpdata.getCpid());
            Log.d("MainActivity","name is "+ cpdata.getName());
            Log.d("MainActivity","birthday is "+ cpdata.getBirthday());
            Log.d("MainActivity","creditrating is "+cpdata.getCreditrating());
        }
    }
    private void parseJSONwithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity","id is "+ id);
                Log.d("MainActivity","version is "+ version);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void parseXMlWithSAX(String xmlData){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            //将
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser =factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int evenType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (evenType != xmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (evenType){
                    //开始解析某个节点
                    case  XmlPullParser.START_TAG:{
                        if("id".equals(nodeName)){
                            id = xmlPullParser.nextText();
                        }else if("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if ("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }




                    //完成解析某个节点
                    case XmlPullParser.END_TAG:{
                        if("app".equals(nodeName)){

                            //responseText.setText(responseText.getText()+id+name+version);

                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                           // Toast.makeText(MainActivity.this,"aaaaaa",Toast.LENGTH_SHORT).show();

                        }
                        break;
                    }




                    default:
                        break;
                }
                evenType = xmlPullParser.next();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void  sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.paojiang.cn");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText(responseText.getText()+response);
            }
        });
    }
}
