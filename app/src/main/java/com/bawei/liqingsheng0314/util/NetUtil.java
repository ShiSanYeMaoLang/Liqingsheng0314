package com.bawei.liqingsheng0314.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {

    Handler handler = new Handler();

    // TODO: 2020/3/11 写一个单例
    //1、私有构造器
    private NetUtil() {

    }

    //2、公共的 geti
    public static NetUtil getInstance() {
        return NET_UTIL;
    }

    //3、在成员变量中创建NetUtil对象
    private static final NetUtil NET_UTIL = new NetUtil();


    // TODO: 2020/3/11 io2String
    private String io2String(InputStream inputStream) throws IOException {
        //三件套
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        //边读边写  read是一个参数，write是三个参数
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        //循环外，将输出流转换成byte数组
        byte[] bytes1 = byteArrayOutputStream.toByteArray();
        //byte数组转换成字符串
        String json = new String(bytes1);
        return json;
    }

    // TODO: 2020/3/11 io2Bitmap
    private Bitmap io2Bitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }


    // TODO: 2020/3/11 接口
    public interface MyCallback {
        //成功
        void onDoGetSuccess(String json);

        //失败
        void onDoGetError();
    }

    // TODO: 2020/3/11 doGet
    public void doGet(final String httpUrl, final MyCallback myCallback) {
        //1、在子线程中联网
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    //2、构造URL对象
                    URL url = new URL(httpUrl);
                    //3、根据url拿到连接对象
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //4、设置请求方式和超时时间
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //5、开启连接
                    httpURLConnection.connect();
                    //6、判断是否开启成功了？
                    if (httpURLConnection.getResponseCode() == 200) {
                        //7、成功获取到流
                        inputStream = httpURLConnection.getInputStream();
                        //8、流转字符串
                        final String json = io2String(inputStream);
                        //9、切换线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //10、打印日志、通知外界
                                Log.e("TAG", "请求成功" + json);
                                myCallback.onDoGetSuccess(json);
                            }
                        });
                    } else {
                        //11、失败, 切换线程、打印日志、通知外界
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //10、
                                Log.e("TAG", "请求失败");
                                myCallback.onDoGetError();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //11、失败, 切换线程、打印日志、通知外界
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //10、
                            Log.e("TAG", "请求失败");
                            myCallback.onDoGetError();
                        }
                    });
                } finally {
                    //12、关流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    // TODO: 2020/3/11 doGetPhoto
    public void doGetPhoto(final String httpUrl, final ImageView imageView) {
        //1、在子线程中联网
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                try {
                    //2、构造URL对象
                    URL url = new URL(httpUrl);
                    //3、根据url拿到连接对象
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //4、设置请求方式和超时时间
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    //5、开启连接
                    httpURLConnection.connect();
                    //6、判断是否开启成功了？
                    if (httpURLConnection.getResponseCode() == 200) {
                        //7、成功获取到流
                        inputStream = httpURLConnection.getInputStream();
                        //8、流转图片
                        final Bitmap bitmap = io2Bitmap(inputStream);
                        //9、切换线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //10、打印日志、将bitmap赋值给imageview
                                Log.e("TAG", "请求图片成功");
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    } else {
                        //11、失败, 切换线程、打印日志
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG", "请求图片失败");
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    //11、失败, 切换线程、打印日志、通知外界
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("TAG", "请求图片失败");
                        }
                    });
                } finally {
                    //12、关流
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    // TODO: 2020/3/11 hasNet是否有网
    public boolean hasNet(Context context) {
        //1、通过conn拿到 连接管理者
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2、geta 拿到网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //3、如果不为空，并且可用 isA，说明有网
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 2020/3/11 hasNet是否是wifi
    public boolean isWifi(Context context) {
        //1、通过conn拿到 连接管理者
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2、geta 拿到网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //3、如果不为空，并且可用 isA，说明有网
        // 4、 getType是否是 WIFI
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 2020/3/11 hasNet是否是移动网络
    public boolean isMobile(Context context) {
        //1、通过conn拿到 连接管理者
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2、geta 拿到网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //3、如果不为空，并且可用 isA，说明有网
        // 4、 getType是否是 WIFI
        if (activeNetworkInfo != null
                && activeNetworkInfo.isAvailable()
                &&activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }
}

