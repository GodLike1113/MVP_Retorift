package com.transsnet.mvpdemo.http;

/**
 * Author:  zengfeng
 * Time  :  2019/9/3 17:21
 * Des   :
 */
public class CommonOkhttpUtil {
//    private OkHttpClient mOkHttpClient;
//    private static CommonOkhttpUtil instance;
//    private final MyHandler mHandler;
//    public static final int SUCCESS = 1;
//    public static final int FAILED = 2;
//    private String tag ="XCrossHttp";
//
//    ArrayMap<String,CreditPostResultListener> hashMapCreditPostResultListener;
//    private CommonOkhttpUtil() {
//        mHandler = new MyHandler(Looper.getMainLooper());
//    }
//
//    private OkHttpClient makeClient(Context context) {
//        hashMapCreditPostResultListener = new ArrayMap<>();
//        mOkHttpClient = XCrossOkhtttpUtil.makeHttpClient(context, new Interceptor() {
//            //添加共有Header
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request.Builder builder = chain.request().newBuilder();
//                builder.addHeader("X-TOKEN", GlobalCreditCacheManager.getInstance().getToken());
//                if (GlobalCreditCacheManager.getInstance().getLoginedInfo() != null) {
//                    builder.addHeader("X-UID", GlobalCreditCacheManager.getInstance().getLoginedInfo().getUid());
//                } else {
//                    builder.addHeader("X-UID", "");
//                }
//                if (GlobalCreditCacheManager.getInstance().getmStateInfo() != null) {
//                    builder.addHeader("X-CID", GlobalCreditCacheManager.getInstance().getmStateInfo().getCustId());
//                }else{
//                    builder.addHeader("X-CID", "");
//                }
//                builder.addHeader("X-APP-ID", JsonConstant.X_CROSS);
//                builder.addHeader("csign", GlobalCreditCacheManager.getInstance().getSignSha1());
//                if (TextUtils.isEmpty(chain.request().header("X-REQUEST-ID"))) {
//                    String requestId = UUID.randomUUID().toString();
//                    builder.addHeader("X-REQUEST-ID", requestId);
//                    builder.addHeader(JsonConstant.AF_REQUESTID, requestId);
//                } else {
//                    builder.addHeader(JsonConstant.AF_REQUESTID, chain.request().header("X-REQUEST-ID"));
//                }
//                builder.addHeader("language", Locale.getDefault().toString());
//                builder.addHeader("countryCode", com.trassnet.palmcredit.constans.Contstant.CURCOUNTRY);//国家代码与后端约定
//                builder.addHeader("X-CHANNEL", BuildApkConfig.getChannel_Id());
//                try {
//                    builder.addHeader(JsonConstant.XAGENTID, BuildApkConfig.getX_agent_id());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                builder.addHeader(JsonConstant.XAPPVERNAME, GlobalCreditCacheManager.getInstance().getVersionName());
//                builder.addHeader("X-APP-VERSION", GlobalCreditCacheManager.getInstance().getVersionCode());
//                builder.addHeader("reqChannel", com.trassnet.palmcredit.constans.Contstant.REQUEST_CHANNEL);
//                return chain.proceed(builder.build());
//            }
//        });
//        return mOkHttpClient;
//    }
//
//    public synchronized static CommonOkhttpUtil getInstance() {
//        if (instance == null) {
//            synchronized (CommonOkhttpUtil.class) {
//                if (instance == null) {
//                    instance = new CommonOkhttpUtil();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public void addPost(final Context context, String requestJson, String url, final CreditPostResultListener listener) {
//        LogUtils.d("url:" + url);
//        com.tencent.mars.xlog.Log.i(tag, "url : " + url);
//        LogUtils.v("request json:" + requestJson);
//        com.tencent.mars.xlog.Log.i(tag, "request json-->"+requestJson);
//        if (mOkHttpClient == null) {
//            makeClient(context);
//        }
//        String reqestId = UUID.randomUUID().toString();
//        hashMapCreditPostResultListener.put(reqestId,listener);
//
//        RequestBody body = RequestBody.create(XCrossOkhtttpUtil.getMediaType(), requestJson);
//        Request request = new Request.Builder()//创建Request 对象。
//                .url(url)
//                .post(body)//传递请求体
//                .tag(reqestId)
//                .build();
//        mOkHttpClient.newCall(request).enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                LogUtils.e("请求响应失败： " + e.getMessage());
//                try {
//                    Request requestRepose = call.request();
//                    com.tencent.mars.xlog.Log.i(tag, "请求响应失败-->"+e.getMessage()+"------------url ="+requestRepose.url().toString());
//                    Message message = Message.obtain();
//                    message.what = FAILED;
//                    message.obj = context.getString(R.string.net_connect_toast);
//                    String tag = String.valueOf(requestRepose.tag());
//                    Bundle bundle = new Bundle();
//                    bundle.putString("tag", tag);
//                    message.setData(bundle);
//                    mHandler.sendMessage(message);
//                } catch (Exception e1) {
//                    e1.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    int code = response.code();
//                    String result = response.body().string();
//                    Request requestRepose = call.request();
//                    LogUtils.v("请求成功返回： " + result);
//                    com.tencent.mars.xlog.Log.i(tag,"请求成功返回-->"+result+"----------url ="+requestRepose.url().toString());
//                    Message message = Message.obtain();
//                    message.what = SUCCESS;
//                    message.obj = new ResponseBean(code, result);
//                    String tag = String.valueOf(requestRepose.tag());
//                    Bundle bundle = new Bundle();
//                    bundle.putString("tag", tag);
//                    message.setData(bundle);
//                    mHandler.sendMessage(message);
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private CreditPostResultListener getCreditPostResultListener(String tag) {
//        CreditPostResultListener creditPostResultListener = null;
//        if(!TextUtils.isEmpty(tag)) {
//            creditPostResultListener = hashMapCreditPostResultListener.get(tag);
//            hashMapCreditPostResultListener.remove(tag);
//        }
//        return creditPostResultListener;
//    }
//
//    public interface CreditPostResultListener {
//        void httpPostRecieveResultSuccess(int code, String result);
//
//        void httpPostRecieveResultFailed(String errMsg);
//    }
//
//    class MyHandler extends android.os.Handler {
//        public MyHandler(Looper looper){
//            super(looper);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case SUCCESS:
//                    ResponseBean bean = (ResponseBean) msg.obj;
//                    Bundle bundle = msg.getData();
//                    if(bundle != null) {
//                        String tag = bundle.getString("tag");
//                        CreditPostResultListener listener = getCreditPostResultListener(tag);
//                        if (listener != null && bean != null) {
//                            listener.httpPostRecieveResultSuccess(bean.getCode(), bean.getResponseString());
//                        }
//                    }
//                    break;
//                case FAILED:
//                    Bundle bundle1 = msg.getData();
//                    if(bundle1 != null) {
//                        String tag = bundle1.getString("tag");
//                        CreditPostResultListener listener = getCreditPostResultListener(tag);
//                        if (listener != null) {
//                            listener.httpPostRecieveResultFailed((String) msg.obj);
//                        }
//                    }
//                    break;
//            }
//        }
//    }
//
//    static class ResponseBean implements Serializable {
//        private int code;
//        private String responseString;
//
//        public ResponseBean(int code, String responseString) {
//            this.code = code;
//            this.responseString = responseString;
//        }
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public String getResponseString() {
//            return responseString;
//        }
//
//        public void setResponseString(String responseString) {
//            this.responseString = responseString;
//        }
//    }

}
