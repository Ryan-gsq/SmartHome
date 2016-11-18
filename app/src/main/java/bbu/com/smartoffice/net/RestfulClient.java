package bbu.com.smartoffice.net;

import android.os.Handler;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestfulClient implements Runnable {

    public static final int DID_SUCCEED = 1;
    private static final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            if (message.what == DID_SUCCEED) {
                HandleData handleData = (HandleData) message.obj;
                if (handleData != null) {
                    handleData.listener.resultListener(
                            handleData.result
                            , handleData.code
                            , handleData.header);
                }
            }
        }
    };
    private String body;
    private String url;
    private String method;
    private OnServiceResultListener listener;
    private Map<String, String> urlParams = null;
    private Map<String, String> headerParams = null;

    public static String byteToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Type getInterfacetype(Class<?> x) {
        Type[] genericInterfaces = x.getGenericInterfaces();
        if (genericInterfaces != null && genericInterfaces.length > 0) {
            if (genericInterfaces[0] instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    return actualTypeArguments[0];
                }
            }
        }
        return null;
    }

    public void create(String method, String url, OnServiceResultListener listener) {
        this.method = method;
        this.url = url;
        this.listener = listener;
        ConnectionManager.getInstance().push(this);
    }

    public RestfulClient addBody(String body) {
        this.body = body;
        return this;
    }

    public RestfulClient addUrlParams(String key, String value) {
        if (urlParams == null) {
            urlParams = new HashMap<>();
        }
        if (value != null && !value.equals("") && !value.equals("0"))
            urlParams.put(key, value);
        return this;
    }

    public RestfulClient addHeaderParams(String key, String value) {
        if (headerParams == null) {
            headerParams = new HashMap<>();
        }
        if (value != null && !value.equals("") && !value.equals("0"))
            headerParams.put(key, value);
        return this;
    }

    @Override
    public void run() {
        HttpURLConnection httpClient = null;
        InputStream result = null;
        Map<String, List<String>> headerFields = null;
        int responseCode = -1;
        try {
            httpClient = getHttpClient();
            responseCode = httpClient.getResponseCode();
            headerFields = httpClient.getHeaderFields();
            if (responseCode != 200) {
                result = httpClient.getErrorStream();
            } else {
                result = httpClient.getInputStream();
            }
        } catch (Exception e) {
            ConnectionManager.getInstance().didComplete(this);
            this.sendMessage(null, -1, headerFields);
            if (httpClient != null)
                httpClient.disconnect();
            return;
        }


        final int BUFFER_SIZE = 1024;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count;
        if (result != null) {
            try {
                while ((count = result.read(data, 0, BUFFER_SIZE)) != -1)
                    outStream.write(data, 0, count);
            } catch (IOException e) {
            }
        }

        sendMessage(outStream.toByteArray(), responseCode, headerFields);
        ConnectionManager.getInstance().didComplete(this);

        try {
            outStream.close();
            if (result != null)
                result.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (httpClient != null)
            httpClient.disconnect();
    }

    private void sendMessage(byte[] result, int code, Map<String, List<String>> header) {
        HandleData handleData = new HandleData();
        handleData.code = code;
        handleData.result = result;
        handleData.listener = listener;
        handleData.header = header;

        Message message = Message.obtain(handler, DID_SUCCEED, handleData);
        handler.sendMessage(message);
    }

    private HttpURLConnection getHttpClient() throws IOException {
        HttpURLConnection httpURLConnection = null;
        //除去结尾的/
        if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.lastIndexOf("/"));
        }
        //设置url参数
        if (urlParams != null && urlParams.size() > 0) {
            int urlParamsSize = urlParams.size();
            StringBuilder params = new StringBuilder();
            if (url.contains("?"))
                params.append("&");
            else
                params.append("?");

            int count = 0;
            for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                params.append(key);
                params.append("=");
                params.append(value);
                if (count < urlParamsSize - 1) {
                    params.append("&");
                }
                count++;
            }
            url += params.toString();
        }

        httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        //设置header参数
        if (headerParams != null && headerParams.size() > 0) {
            for (Map.Entry<String, String> entry : headerParams.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        httpURLConnection.setRequestMethod(method);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(10000);

        if (body != null && !body.equals("")) {
            httpURLConnection.setRequestProperty("content-type", "application/json");
            httpURLConnection.setDoOutput(true);
            OutputStream output = httpURLConnection.getOutputStream();
            OutputStreamWriter outr = new OutputStreamWriter(output);
            // 写入请求参数
            outr.write(body.toCharArray(), 0, body.length());
            outr.flush();
            outr.close();
        }

        return httpURLConnection;
    }

    public interface OnServiceResultListener {
        void resultListener(byte[] result, int code, Map<String, List<String>> header);
    }

    private class HandleData {
        public OnServiceResultListener listener;
        public Map<String, List<String>> header;
        public int code;
        public byte[] result;
    }

}