
import okhttp3.*;
import java.io.IOException;

public class Fast2SMS {
    private static final String API_URL = System.getenv("FAST2SMS_URL");
    private static final String API_KEY = System.getenv("FAST2SMS_API_KEY");

    public static void sendSMS(String message, String phoneNumber) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("sender_id", "FSTSMS")
                .add("message", message)
                .add("language", "english")
                .add("route", "p")
                .add("numbers", phoneNumber)
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("authorization", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Failed to send message: " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
