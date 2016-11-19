package bbu.com.smartoffice;

import com.orhanobut.logger.Logger;

import org.junit.Test;

import java.io.IOException;

import bbu.com.smartoffice.net.HttpRequest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_request() {
        try {
            HttpRequest.getInstance().queryDevice("4069468", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Logger.d(e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Logger.d(response.body().toString());

                }
            });
        }catch (IOException e){
            String a;

        }

    }
}