package externalData;

import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataLoader{

    private static final int NUMBER_OF_THREADS = 8;
//    public static final ExecutorService executor =
//            Executors.newScheduledThreadPool(NUMBER_OF_THREADS);

    public DataLoader() {

    }

//    public MutableLiveData<String> LoadData(){
//
//        MutableLiveData<String> characters = new MutableLiveData<>();
//
//        executor.execute(() ->
//        {
//            try {
//                characters.postValue(_loadData());
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        });
//
//        return characters;
//    }

    public String _loadData() throws Exception{

        URL obj = new URL("https://hp-api.herokuapp.com/api/characters");
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){

        }
        else {

        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        Log.i("myLogs", String.valueOf(response));

        return String.valueOf(response);
    }

//    public MutableLiveData<ArrayList<Drawable>> LoadImages(ArrayList<models.Character> characters){
//
//        MutableLiveData<ArrayList<Drawable>> images = new MutableLiveData<>();
//
//        executor.execute(() ->
//        {
//            try {
//                images.postValue(_loadImages(characters));
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        });
//
//        return images;
//    }

    public Map<String, Drawable> _loadImages(ArrayList<models.Character> characters) throws Exception{
        Map<String, Drawable> charactersImages = new HashMap<String, Drawable>();

        for (models.Character character : characters){
            String url = character.image;
            String first = "http";
            String second = "https";

            url = url.replace(first, second);

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "https://hp-api.herokuapp.com/images/harry.jpg");
                charactersImages.put(character.name,d);
            } catch (Exception e) {
            }
        }

        return charactersImages;
    }

    public Drawable _loadLoadingImage() throws Exception{
        String url = "http://hp-api.herokuapp.com/images/norris.JPG";
        String first = "http";
        String second = "https";

        url = url.replace(first, second);

        Drawable d = null;

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            d = Drawable.createFromStream(is, "https://hp-api.herokuapp.com/images/harry.jpg");
        } catch (Exception e) {
        }

        return d;
    }
}
