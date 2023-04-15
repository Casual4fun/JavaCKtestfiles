import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    private static final String strURL = "https://api.myip.com/";

    public static void main(String[] args) throws MalformedURLException, IOException
    {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        URL url = new URL(strURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int responceCode = httpURLConnection.getResponseCode();
        if (responceCode == HttpURLConnection.HTTP_OK)
        {
            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            Gson gson = new Gson();

            String ipinfo = bufferedReader.readLine();
            GetIPinfo getinfo = gson.fromJson(ipinfo,GetIPinfo.class);
            System.out.println("IP Адрес: "+ getinfo.getIp()+"\n"+
                                "Страна: "+ getinfo.getCountry()+"\n"+
                                "CC страны: "+ getinfo.getCc());
        }
        else
            System.out.println("Connection lost");
    }
}

class GetIPinfo
{
    String ip, country, cc;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public GetIPinfo(String ip, String country, String cc) {
        this.ip = ip;
        this.country = country;
        this.cc = cc;
    }
}
