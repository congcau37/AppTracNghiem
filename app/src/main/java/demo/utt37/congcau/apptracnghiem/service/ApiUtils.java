package demo.utt37.congcau.apptracnghiem.service;

public class ApiUtils {
    public static final String BASE_URL = "http://10.0.3.2/test/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
