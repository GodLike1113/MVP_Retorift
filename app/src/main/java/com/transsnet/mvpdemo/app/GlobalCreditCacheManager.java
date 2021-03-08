//
//package app;
//
//import android.text.TextUtils;
//
//import com.blankj.utilcode.util.AppUtils;
//import com.google.gson.Gson;
//import com.quality.apm.core.Manager;
//import com.transsnet.commonbase.model.bean.CheckUpgradeBean;
//import com.transsnet.commonbase.model.bean.CustFreezeStatusBean;
//import com.transsnet.commonbase.model.bean.LoginedInfo;
//import com.transsnet.commonbase.model.bean.StateInfo;
//import com.transsnet.commonbase.model.bean.VisaBankCardInfo;
//import com.transsnet.commonbase.utils.ACache;
//import com.transsnet.commonbase.utils.AppUtil;
//import com.transsnet.commonbase.utils.PalmEncryptUtils;
//
///**
// * 全局数据存储类
// * Created by zhh on 2016/11/1.
// */
//
//public class GlobalCreditCacheManager {
//    private volatile static GlobalCreditCacheManager globalCacheManager;
//    private final static String  LOGINEDINFO = "logined_info_new";
//    private volatile LoginedInfo mLoginedInfo;
//    private volatile StateInfo mStateInfo;
//    private String mSignSha1;
//    private String flag;//跳转标记
//    private VisaBankCardInfo visaBankCardInfo;
//    private String bankCardNo;//银行卡
//    private String bankName;//银行
//    private String cvv;//cvv
//    private String dueDate;//银行卡有效日期
//    private String versionName;
//    private String versionCode;
//    private CheckUpgradeBean mCheckUpgradeBean;
//    private CustFreezeStatusBean custFreezeStatusBean;
//
//
//    private GlobalCreditCacheManager() {
//    }
//
//    public static GlobalCreditCacheManager getInstance() {
//        if (null == globalCacheManager) {
//            synchronized (GlobalCreditCacheManager.class) { //双重检验锁
//                if (null == globalCacheManager) {
//                    globalCacheManager = new GlobalCreditCacheManager();
//                }
//            }
//        }
//        return globalCacheManager;
//    }
//
//    public void setLoginedInfo(LoginedInfo loginedInfo){
//        mLoginedInfo = loginedInfo;
//        Gson gson = new Gson();
//        String loginedString = gson.toJson(mLoginedInfo);
//        ACache aCache = ACache.get(AppInit.getApplication());
//        aCache.put(LOGINEDINFO, PalmEncryptUtils.encryptText(loginedString,getSignSha1()));
//        Manager.getInstance().getConfig().account=mLoginedInfo.getPhone();
//        Manager.getInstance().getConfig().uid=mLoginedInfo.getUid();
//    }
//
//    public LoginedInfo getLoginedInfo(){
//        if(mLoginedInfo==null){
//            ACache aCache = ACache.get(AppInit.getApplication());
//            Gson gson = new Gson();
//            String encryptString = aCache.getAsString(LOGINEDINFO);
//            if(!TextUtils.isEmpty(encryptString)){
//                String loginedInfo = PalmEncryptUtils.decryptText(encryptString,getSignSha1());
//                if(!TextUtils.isEmpty(loginedInfo)){
//                    try{
//                        mLoginedInfo = gson.fromJson(loginedInfo,LoginedInfo.class);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }
//        return this.mLoginedInfo;
//    }
//
//    public String getUid() {
//        LoginedInfo loginedInfo = getLoginedInfo();
//        if(loginedInfo != null) {
//            return loginedInfo.getUid();
//        }
//        return "";
//    }
//
//    public String getFirstName() {
//        LoginedInfo loginedInfo = getLoginedInfo();
//        if(loginedInfo != null) {
//            return loginedInfo.getFirstName();
//        }
//        return "";
//    }
//
//    public String getPhone() {
//        LoginedInfo loginedInfo = getLoginedInfo();
//        if(loginedInfo != null) {
//            return loginedInfo.getPhone();
//        }
//        return "";
//    }
//
//
//    public void setmStateInfo(StateInfo stateInfo){
//        mStateInfo = stateInfo;
//    }
//
//
//    public StateInfo getmStateInfo(){
//        if(mStateInfo==null){
//            mStateInfo = new StateInfo();
//        }
//        return  mStateInfo;
//    }
//
//    public boolean removeLoginInfo() {
//        ACache aCache = ACache.get(AppInit.getApplication());
//        mLoginedInfo = null;
//        custFreezeStatusBean = null;
//        return aCache.remove(LOGINEDINFO);
//    }
//
//
//    public String getToken(){
//        String token = "";
//        if(mLoginedInfo==null){
//            ACache aCache = ACache.get(AppInit.getApplication());
//            mLoginedInfo = (LoginedInfo)aCache.getAsObject(LOGINEDINFO);
//        }
//        if(mLoginedInfo!=null){
//            token = mLoginedInfo.getToken();
//        }
//        return token;
//    }
//
//    public String getSalt(){
//        String salt = "";
//        if(mLoginedInfo==null){
//            ACache aCache = ACache.get(AppInit.getApplication());
//            mLoginedInfo = (LoginedInfo)aCache.getAsObject(LOGINEDINFO);
//        }
//        if(mLoginedInfo!=null){
//            salt = String.valueOf(mLoginedInfo.getSalt());
//        }
//        return salt;
//    }
//
//    public String getSignSha1(){
//        if(TextUtils.isEmpty(mSignSha1)){
//            mSignSha1 = AppUtils.getAppSignatureSHA1().replace(":","");
//        }
//        return  mSignSha1;
//    }
//
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    public VisaBankCardInfo getVisaBankCardInfo() {
//        return visaBankCardInfo;
//    }
//
//    public void setVisaBankCardInfo(VisaBankCardInfo visaBankCardInfo) {
//        this.visaBankCardInfo = visaBankCardInfo;
//    }
//
//    public String getBankCardNo() {
//        return bankCardNo;
//    }
//
//    public void setBankCardNo(String bankCardNo) {
//        this.bankCardNo = bankCardNo;
//    }
//
//    public String getBankName() {
//        return bankName;
//    }
//
//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }
//
//    public String getCvv() {
//        return cvv;
//    }
//
//    public void setCvv(String cvv) {
//        this.cvv = cvv;
//    }
//
//    public String getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(String dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public String getVersionName(){
//        if(TextUtils.isEmpty(versionName)){
//            versionName = AppUtil.getVersionName(AppInit.getApplication());
//        }
//        return  versionName;
//    }
//
//    public String getVersionCode(){
//        if(TextUtils.isEmpty(versionCode)){
//            versionCode = AppUtil.getVersionCode(AppInit.getApplication());
//        }
//        return  versionCode;
//    }
//
//
//    /**
//     *
//     * @param checkUpgradeBean
//     */
//    public void setCheckUpgradeBean(CheckUpgradeBean checkUpgradeBean){
//        mCheckUpgradeBean = checkUpgradeBean;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public CheckUpgradeBean getCheckUpgradeBean(){
//        return mCheckUpgradeBean;
//    }
//
//    public CustFreezeStatusBean getCustFreezeStatusBean() {
//        return custFreezeStatusBean;
//    }
//
//    public void setCustFreezeStatusBean(CustFreezeStatusBean custFreezeStatusBean) {
//        this.custFreezeStatusBean = custFreezeStatusBean;
//    }
//}
