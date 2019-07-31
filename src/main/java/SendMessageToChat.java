import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SendMessageToChat {
    public static void sendToTelegram() {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token (given Token is fake)
        String apiToken = "712444289:AAGEHKqRVapP8TtUTCaCHTpuuALswIogp64";

        //Add chatId (given chatId is fake)
        //String chatId = "243053893";
        String chatIdBahrom = "433876865";
        String chatId = "243053893";
        String text = "Hello world!";

        urlString = String.format(urlString, apiToken, chatIdBahrom, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}