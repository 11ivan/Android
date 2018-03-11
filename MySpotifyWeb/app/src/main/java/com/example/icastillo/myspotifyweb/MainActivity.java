package com.example.icastillo.myspotifyweb;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPassword;
    String loginUrl="http://accounts.spotify.com/es/login";
    String url="http://open.spotify.com/browse/featured";
    String urlPlayList="http://open.spotify.com/user/ivancascal/playlist/0NOXtDPIlxpsHc5enZGMUQ";//Intentar acceder a playlist publica de la cuenta de spotify de escritorio
    String myTracksUrl="http://open.spotify.com/collection/tracks";
    String urlTrack="/album/74D2cU8F6KbBqva3mnzrqI?";
    Login login=new Login();
    Document document;
    Map<String, String> loginCookies;
    GoToMyTracks goToMyTracks=new GoToMyTracks();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser=(EditText) findViewById(R.id.textNameUser);
        txtPassword=(EditText) findViewById(R.id.textPassUser);

        txtUser.setText("07.qweasd123@gmail.com");//!!!!!
        txtPassword.setText("estamisma");//!!!!!!!!


        login.execute();

        //goToMyTracks.execute();

    }

    //Clase para realizar login
    private class Login extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //document= Jsoup.connect(loginUrl).get();

                //First login. Take the cookies
                Connection.Response res = Jsoup     //Login Correct!! XD
                        .connect(loginUrl)
                        .data("login-username", txtUser.getText().toString())
                        .data("login-password", txtPassword.getText().toString())
                        .referrer("http://www.google.com")
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .method(Connection.Method.POST).timeout(0).execute();

                Map<String, String> loginCookies = res.cookies();

                //Now you can parse any page you want, as long as you pass the cookies
                document = Jsoup
                        .connect(url)
                        .timeout(0)
                        .cookies(loginCookies)
                        .referrer("http://www.google.com")
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Elements elements = document.getAllElements();
            /*Elements body = document.getElementsByTag("body");
            for (Element element:body){
                element.attributes();
            }*/
            goToMyTracks.execute();
        }
    }


    private class GoToMyTracks extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //document= Jsoup.connect(myTracksUrl).get();
                document = Jsoup
                        .connect(urlPlayList)
                        .timeout(0)
                        //.cookies(loginCookies)
                        .referrer("http://www.google.com")
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Elements body=document.getElementsByTag("body");


        }
    }












}
