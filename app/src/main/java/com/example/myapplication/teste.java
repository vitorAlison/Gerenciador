package com.example.myapplication;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class teste {

    public static String getJSONFromaAPI(String url){
        String retorno = "";
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL apiEnd = new URL (url);
                    int codigoResposta;
                    HttpURLConnection conexao;
                    InputStream is;

                    conexao = (HttpURLConnection) apiEnd.openConnection();
                    conexao.setDoInput(true);
                    conexao.setDoOutput(true);
                    conexao.setRequestMethod("GET");
                    conexao.setReadTimeout(15000);
                    conexao.setConnectTimeout(15000);
                    conexao.connect();

                    Log.d("TAG", conexao.toString());
                    codigoResposta = conexao.getResponseCode();
                    if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                        is = conexao.getInputStream();
                    }else{
                        is = conexao.getErrorStream();
                    }

                    Log.d("TAG", converterInputStreamToString(is));

                    //retorno = converterInputStreamToString(is);
                    is.close();
                    conexao.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

        return retorno;
    }

    private static String converterInputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }
}