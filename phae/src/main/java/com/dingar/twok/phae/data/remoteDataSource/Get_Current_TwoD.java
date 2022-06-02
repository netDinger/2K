package com.dingar.twok.phae.data.remoteDataSource;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * Get the current Thai two D result in realtime form official website
 */
public class Get_Current_TwoD {
    private final String TAG = "Get_Current_TwoD";

    //Singleton
    private Get_Current_TwoD(){}
    private static Get_Current_TwoD instance;
    public static Get_Current_TwoD getInstance(){
        if (instance == null)
            instance = new Get_Current_TwoD();
        return instance;
    }

    /**
     * @return Single observer for combined 2D result
     */
    public Single<String> getResult(){
        return Single.create(emitter -> {
            //official website url
            final String url = "https://classic.set.or.th/mkt/marketsummary.do?language=en&country=US";

            ArrayList<String> arrayList = new ArrayList<>();
            try{ //get the url web page source as document
                Document document = Jsoup.connect(url).timeout(5000).get();
                //get html table as elements 'table'
                Elements table = document.getElementsByClass("table-info");
                Elements tr = table.get(0).select("tr");
                Elements td = tr.get(1).select("td");
                Log.e(TAG,String.valueOf(td));
                for(Element cell : td)
                    arrayList.add(cell.text());

                if (arrayList.isEmpty())
                    Log.e(TAG,"empty");
                else{
                Log.e(TAG,arrayList.get(0));
               Log.e(arrayList.get(1),arrayList.get(7));
                emitter.onSuccess(getFirst(arrayList.get(1))+getLast(arrayList.get(7)));}

         }catch(Exception exception){
                exception.printStackTrace();
                Log.e(TAG,""+exception.getMessage());
                emitter.onError(exception);
            }
        });
    }

    /**
     *
     * @param prefix first set value to get the first digit
     * @return first digit for the TwoD
     */
    private String getFirst(String prefix) {
        String firstDigit = prefix.replaceAll(",","");
        int index = firstDigit.indexOf('.');
        int strlen = index - 1;
        return firstDigit.substring(strlen,index);

    }

    /**
     * @param last second set value to get second digit
     * @return second digit for the TwoD
     */
    private String getLast(String last){
        String lastDigit = last.replaceAll(",","");
        int lastIndex = lastDigit.length()-1;
        int strlen = lastDigit.length();
        return lastDigit.substring(lastIndex,strlen);
    }

}
