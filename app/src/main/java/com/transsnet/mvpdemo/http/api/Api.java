package com.transsnet.mvpdemo.http.api;

import com.transsnet.mvpdemo.BuildConfig;
import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;
import com.transsnet.mvpdemo.contants.Contstant;
import com.transsnet.mvpdemo.http.bean.BaseEntity;
import com.transsnet.mvpdemo.http.bean.BaseNewEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 说明:  <br/>
 * 邮箱: yangbin5052@gmail.com <br/>
 * Create at 2018/3/13--11:08 by Luuren
 */
public interface Api {
    String GIVE_UP_BIND_CARD ="user/bindingBankCardSurvey.do";
    String SPLASH_AD ="query/queryBootPage.do";

//    /**大数据接口*/
//    String CREDIT_BIGDATA_ACTIVATE = BuildConfig.BIGDATA_BASE_URL+"credit";
//    String TRADE_BIGDATA_ACTIVATE = BuildConfig.BIGDATA_BASE_URL+"trade";
//    String DRIVERLICENSE_BIGDATA_ACTIVATE = BuildConfig.BIGDATA_BASE_URL+"driverlicense";
//    String SALES_GPS_MATCH = BuildConfig.BIGDATA_BASE_URL+"sales/gps";
//    String CUSTOMER_MATCH = BuildConfig.BIGDATA_BASE_URL+"user/gps";
//
//    /**Xcross的入口*/
    String SHOW_XCROSS_ENTER = "https://testing-xcross.palmcredit.loans/blc-service/public/queryXcrossOpenStatus";
//    String XCROSS_STAGE_STATUS=BuildConfig.XCROSS_BASE_URL+"ims-service/cust/queryCustInfo";
//    String XCROSS_CUST_LIMIT =BuildConfig.XCROSS_BASE_URL+"cfk-service/customer/queryCustLmtAmt";
//    String XCROSS_LOAN_DETAIL =BuildConfig.XCROSS_BASE_URL+"cfk-service/nonfinancial/queryLoanDetail";
//    String XCROSS_H5 = BuildConfig.XCROSS_BASE_URL+"web/xcross/";
//    String IS_XCROSS_WHITELIST =BuildConfig.XCROSS_BASE_URL+"ims-service/cust/queryWhiteListCust";
//    String XCROSS_DEFAULT_RATE=BuildConfig.XCROSS_BASE_URL+"blc-service/public/queryDefaultProductFee";


    //登出
    @POST
    Observable<BaseEntity<Object>> outLogin(@Url String url);
//
//    /**
//     * 登录
//     * @param params
//     * @return
//     */
//    @POST(LOGIN_NEW_URL)
//    Observable<BaseEntity<LoginedInfo>> login(@Body LoginParams params);
//
//    @POST
//    Observable<BaseEntity<HomeBanner>> getBanner(@Url String url);
//
//    //阶段信息查询
//    @POST
//    Observable<BaseEntity<StateInfo>> queryStageInfo(@Url String url);
//
//    //校验密码
//    @POST
//    Observable<BaseEntity<Object>> bindBankCardCheckPin(@Url String url, @Body Map<String, String> map);
//
//    /**
//     * 绑定银行卡确认
//     * @param
//     * @return
//     */
//    @POST
//    Observable<BaseEntity<Object>> bindBankCardConfirm(@Url String url, @Body Map<String, String> map);
//
//    //查询绑卡结果
//    @POST
//    Observable<BaseEntity<QueryCustBankCard>> queryCustBankCard(@Url String url, @Body Map<String, String> map);
//
//    /**
//     * 查询银行列表
//     * @param
//     * @return
//     */
//    @POST
//    Observable<BaseEntity<List<QuerySupportBankList>>> querySupportBankList(@Url String url);
//
//
//    /**
//     * 删除银行卡
//     */
//    @POST
//    Observable<BaseEntity<Object>> delBankCard(@Url String url, @Body Map<String, String> map);
//
//    /**
//     * 更改默认银行卡
//     */
//    @POST
//    Observable<BaseEntity<Object>> updateDefaultBankCard(@Url String url, @Body Map<String, String> map);
//
//    /**
//     * 银行卡列表
//     */
//    @POST
//    Observable<BaseEntity<BankCard>> queryCustomerBankCardList(@Url String url);
//
//    /**
//     * 银行卡列表(含背景图)
//     */
//    @POST(QUERY_CUSTOMER_BANK_CARD_LIST_NEW)
//    Observable<BaseEntity<BankCardNew>> queryCustomerBankCardListNew();
//
//
//    //获取lemonCode
//    @POST
//    Observable<BaseEntity<LemonCode>> getLemonCode(@Url String url, @Body Map<String, String> map);
//
//    //授信
//    @POST(BIND_BASE_INFO)
//    Observable<BaseEntity<Object>> bindBaseInfo(@HeaderMap Map<String, String> headers, @Body BindBaseInfoBean bindBaseInfoBean);
//
//    //借据列表
//    @POST
//    Observable<BaseEntity<LoanRecord>> queryLoanList(@Url String url, @Body Map<String, String> map);
//
//    //提现充值试算
//    @POST
//    Observable<BaseEntity<LoanCalculate>> loanCalculate(@Url String url, @Body LoanCalculateParams loanCalculateParams);
//
//    //查询产品
//    @POST
//    Observable<BaseEntity<ProductList>> querySimpleProduct(@Url String url);
//
//    //提现充值
//    @POST
//    Observable<BaseEntity<ApplyStatusInfo>> loanApply(@Url String url, @Body LoanApplyParams loanApplyParams);
//
//
//    @POST(LOAN_APPLY)
//    Observable<BaseEntity<ApplyStatusInfo>> loanApply(@HeaderMap Map<String, String> headers, @Body LoanApplyParams loanApplyParams);
//
//    //设置/重置交易密码
//    @POST
//    Observable<BaseEntity<Object>> setTransPassword(@Url String url, @Body Map<String, String> map);
//
//  //借据详情
//    @POST
//    Observable<BaseEntity<LoanDetail>> queryLoanDeatails(@Url String url, @Body Map<String, String> map);
//
//    //还款试算
//    @POST
//    Observable<BaseEntity<ReimbursementTrial>> activityRepayment(@Url String url, @Body ActivityRepaymentParams params);
//
//    //获取合同
//    @POST
//    Observable<BaseEntity<Trial>> getContractPath(@Url String url, @Body Map<String, String> map);
//
//    //获取验证码-BVN
//    @POST
//    Observable<BaseEntity<Object>> getValidCodeBVN(@Url String url, @Body Map<String, String> map);
//
//    //验证短信验证码-BVN
//    @POST
//    Observable<BaseEntity<Object>> checkValidCodeBVN(@Url String url, @Body Map<String, String> map);
//
//    //数据字典
//    @POST
//    Observable<BaseEntity<QueryDictModel>> queryDict(@Url String url, @Body Map<String, String> map);
//
//    //查询额度
//    @POST
//    Observable<BaseEntity<AvailableValueInfo>> queryCustLmt(@Url String url);
//
//    @POST
//    Observable<BaseEntity<RecentNdRepayInfo>> queryLatelyRepayment(@Url String url, @Body Map<String, Integer> map);
//
//    //查询消息列表
//    @POST
//    Observable<BaseEntity<SystemMessageResponse>> querySystemMessageList(@Url String url, @Body InformationListParams params);
//
//    //还款
//    @POST
//    Observable<BaseEntity<RepayStatusInfo>> Repayment(@Url String url, @Body RepaymentParams params);
//
//    //部分还款
//    @POST(PARETIAL_ACTIVE_REPAYMENT)
//    Observable<BaseEntity<PartialRepayStatusInfo>> partialRepayment(@Body PartialRepaymentParams params);
//
//    //积分充值
//    @POST
//    Observable<BaseEntity<Object>> mobileRechargeByPoint(@Url String url, @Body Map<String, String> map);
//
//    //同步设备数据
//    @POST
//    Observable<BaseEntity<Object>> saveDeviceInfo(@Url String url, @Body Map<String, String> map);
//
//    @POST(VERIFY_PERSONAL_INFO)
//    Observable<BaseEntity<Object>> verifyPersonalInfo(@Body PersonalInfoNameParams params);
//
//    @POST(VERIFY_TO_CREATE_NEWCUST)
//    Observable<BaseEntity<Object>> verifyToCreateNewCust(@Body Map<String, String> params);
//
//    @POST(VERIFY_BANK_ACCOUNT)
//    Observable<BaseEntity<BindBankAccountUserInfo>> verifyBankAccount(@Body Map<String, String> params);
//
//    @POST(ADD_BANK_ACCOUNT)
//    Observable<BaseEntity<AddBankAccountBean>> addBankAccount(@Body Map<String, String> params);
//
//    //查询待领取提额列表接口
//    @POST(WEEKLYADJUSTLIMITLIST)
//    Observable<BaseEntity<AdjustLimitList>> weeklyAdjustLimitList();
//
//    //查询是否有待领取额度接口
//    @POST(WAITADJUSTLIMIT)
//    Observable<BaseEntity<WaiAdjustLimitBean>> waitAdjustLimit();
//
//    //额度领取接口
//    @POST(ADJUSTLIMIT)
//    Observable<BaseEntity<PendingChangedLimitBean>> adjustLimit(@Body Map<String, String> params);
//
//    //查询所有提额列表接口
//    @POST(QUERYALLACTIVATEADJUSTLIMITLIST)
//    Observable<BaseEntity<AdjustLimitList>> queryAllAcAdjustLimitList();
//
//    @POST(CHECKUPGRADE)
//    Observable<BaseEntity<CheckUpgradeBean>> checkUpgrade(@Body Map<String, String> params);
//
//    //重新获取个推消息
//    @POST(FEEDBACK)
//    Observable<BaseEntity<Object>> feedback(@Body Map<String, String> params);
//
//    //还款计划提醒摘要
//    @POST(GET_REPAYMENT_SUMMARY)
//    Observable<BaseEntity<RepaymentSummaryInfo>> getRepaymentSummary();
//
//    //下发首页公告
//    @POST(GET_ANNOUNCEMENT)
//    Observable<BaseEntity<AnnouncementListInfo>> getAnnouncement();
//
//    @POST(UPDATE_CHANNELINFO)
//    Observable<BaseEntity<Object>> updateChannelInfo();
//
//    @POST(RATE_SUBMIT)
//    Observable<BaseEntity<Object>> rateSubmit(@Body Map<String, Integer> params);
//
//    //是否可以邀请
//    @POST(QUERY_HAS_INVERT)
//    Observable<BaseEntity<QueryInvertResultBean>> queryHasInvert(@Body Map<String, String> params);
//
//    //绑定firebase token
//    @POST(BINDFIREBASETOKEN)
//    Observable<BaseEntity<Object>> bind_firebasetoken(@Body Map<String, String> params);
//
//    //查询是否被冻结
//    @POST(QUERYCUSTSTATUS)
//    Observable<BaseEntity<CustFreezeStatusBean>> queryCuststatus();
//
//    //发送bvn手机号更新库的验证码
//    @POST(FLWSENDBVNOTP)
//    Observable<BaseEntity<Object>> flwSendBvnOtp(@Body Map<String, String> params);
//
//    //校验新的手机号的验证码
//    @POST(FLWVERIFYBVNOTP)
//    Observable<BaseEntity<Object>> flwVerifyBvnOtp(@Body Map<String, String> params);
//
//    //帮卡费用查询
//    @POST(QUERYBANKCARDBINDFEE)
//    Observable<BaseEntity<BindCardFeeBean>> queryBankCardBindFee();
//
//    //提额补充资料保存接口
//    @POST(SAVESUPPLYINFO)
//    Observable<BaseEntity<Object>> saveSupplyInfo(@Body Map<String, Object> map);
//
//    //查询地区接口
//    @POST(QUERYAREAINFO)
//    Observable<BaseEntity<AreaInfo>> queryAreaInfo(@Body Map<String, String> map);
//
//    @POST(REQUESTSUPPLYMOBILECAPTCHA)
//    Observable<BaseEntity<Object>> requestSupplyMobileCaptcha(@Body Map<String, String> map);
//
//    @POST(QUERYSUPPLYINFOBYTYPE)
//    Observable<BaseEntity<Object>> querySupplyInfoByType(@Body Map<String, String> map);
//
//    @POST(SAVESIGNINFO)
//    Observable<BaseEntity<Object>> saveSignInfo();
//
//    @POST(QUERYSUPPLYMAININFO)
//    Observable<BaseEntity<Object>> querySupplyMainInfo(@Body Map<String, String> map);
//
//    @POST(QUERYCOUPONLIST)
//    Observable<BaseEntity<CouponsList>> queryCouponList(@Body Map<String, String> map);
//
//    @POST(CREDIT_BIGDATA_ACTIVATE)
//    Observable<BaseEntity<Object>> creditBigdataActivate(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);
//
//    @POST(TRADE_BIGDATA_ACTIVATE)
//    Observable<BaseEntity<Object>> tradeBigdataActivate(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);
//
//    @POST(DRIVERLICENSE_BIGDATA_ACTIVATE)
//    Observable<BaseEntity<Object>> driverlicenseBigdataActivate(@HeaderMap Map<String, String> headers, @Body Map<String, String> map);
//
//    //查询是否白名单
//    @POST
//    Observable<BaseEntity<QueryWhiteListRespBean>> queryIsWhiteList(@Url String url, @Body Map<String, String> map);
//
//    //非白名单用户支付查询征信
//    @POST(PAY_NOT_WHITE_LIST_QUERY)
//    Observable<BaseEntity<PayQueryCreditLimitSuccessBean>> payQueryCreditLimit(@Body Map<String, String> params);
//
//    //征信详情
//    @POST(CREDIT_QUERY_DETAILS)
//    Observable<BaseEntity<CreditHistoryDetailsBean>> queryCreditHistoryDetails(@Body Map<String, String> params);
//
//
//    //生成 paystack 网页支付订单链接
//    @POST(GEN_WEB_PAYSTACK_URL)
//    Observable<BaseEntity<BindBankOrUSSDPaystackInfo>> genPaystackWebUrl(@Body PaystackWebPayParams params);
//
//    //生成 palmPay 网页支付订单链接
//    @POST(GEN_WEB_PALMPAY_URL)
//    Observable<BaseEntity<BindBankOrUSSDPaystackInfo>> genPalmpayWebUrl(@Body PaystackWebPayParams params);
//
//    //保存用户的预期金额和产品ID
//    @POST(SAVE_PRODUCT_EXPECTED)
//    Observable<BaseEntity<Object>> saveProductExpected(@Body Map<String, String> map);
//    //查看账户是否冻结
//    @GET(QUERY_CUST_FINANCIAL_STATUS)
//    Observable<BaseEntity<CheckAccountFrozenBean>> checkAccountFrozen();
//
//    //线下还款历史记录
//    @POST(QUERY_OFFLINE_HISTORY)
//    Observable<BaseEntity<OfflineHistoryListBean>> queryOfflineHistory(@Body Map<String, String> map);
//    //线下还款申请
//    @POST(CREATE_OFFLINE_APPLY)
//    Observable<BaseEntity<Object>> createOfflineApply(@Body Map<String, String> map);
//    //查看线下还款申请信息
//    @GET(OFFLINE_REPAY)
//    Observable<BaseEntity<OfflineRepayInfoBean>> queryOfflineRepayInfo();
//
//    //更改银行帐户
//    @POST(CHANGE_BANK_ACCT)
//    Observable<BaseEntity<BindBankAccountUserInfo>> changeBankInfo(@Body Map<String, String> map);
//
//    //调额
//    @POST(APPLY_RISK_PRICE)
//    Observable<BaseEntity<Object>> applyRiskPrice(@Body Map<String, String> map);
//
//    //查询Remita账户状态
//    @POST(BIND_REMITA_INIT)
//    Observable<BaseEntity<BindRemitaInitStatusBean>> bindRemitaInit(@Body Map<String, String> map);
//
//    //发送Remita验证码
//    @POST(SEND_REMITA_OTP)
//    Observable<BaseEntity<RemitaOTPRespBean>> getRemitaOTP(@Body Map<String, String> map);
//
//    //验证Remita验证码
//    @POST(VERIFY_REMITA_OTP)
//    Observable<BaseEntity<VerifyRemitaOTPBean>> verifyRemitaOTP(@Body Map<String, String> map);
//
//    //PayStack的Api绑卡
//    @POST(BIND_BANK_CARD_CHARGE)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardCharge(@Body Map<String, String> map);
//
//    //PayStack的验证PIN
//    @POST(BIND_BANK_CARD_PIN)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardVerifyPin(@Body Map<String, String> map);
//
//    //PayStack的验证OTP
//    @POST(BIND_BANK_CARD_OTP)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardVerifyOTP(@Body Map<String, String> map);
//
//    //PayStack的验证PHONE
//    @POST(BIND_BANK_CARD_PHONE)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardVerifyPhone(@Body Map<String, String> map);
//
//    //PayStack的验证birthday
//    @POST(BIND_BANK_CARD_BIRTHDAY)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardVerifyBirthday(@Body Map<String, String> map);
//
//    //PayStack的验证bankAuth
//    @POST(BIND_BANK_CARD_BANK_AUTH)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardVerifyBankAuth(@Body Map<String, String> map);
//
//    //PayStack的查询状态
//    @POST(BIND_BANK_CARD_QUERY_STATUS)
//    Observable<BaseEntity<BindBankCardChargeBean>> bindBankCardQueryBankStatus(@Body Map<String, String> map);
//
//    //登陆查询手机号是否跟BVN绑定,是否是BVN预留手机号
//    @POST(LOGIN_ISPHONE_BIND_BVN)
//    Observable<BaseEntity<LoginIsPhoneBindBvnBean>> isPhoneBindBvn(@Body Map<String, String> map);
//
//    //上传销售人员的GPS
//    @POST(SALES_GPS_MATCH)
//    Observable<BaseEntity<Object>> uploadSalesGpsInfo(@Body AgentMatchBean agentMatchBean);
//
//    //上传客户人员的GPS
//    @POST(CUSTOMER_MATCH)
//    Observable<BaseEntity<Object>> uploadCustomerGpsInfo(@Body CustomerMatchBean customerMatchBean);
//
//    //验证邀请码是否正确或当前用户是否为业务员
//    @POST(BUSINESS_MEMBER_IS_EXIST)
//    Observable<BaseEntity<BussinessMemberReposeBean>> businessMemberOrSalesIsExists(@Body Map<String, String> map);
//
//    //判断是不是被业务员邀请的用户接口
//    @POST(BUSINESS_IS_INVITEE)
//    Observable<BaseEntity<BussinessIsInviteeReponseBean>> businessIsInvitee(@Body Map<String, String> map);
//
//    //查询未还订单状态信息
//    @POST(QUERY_UNPAY_ORDER_STATUS)
//    Observable<BaseEntity<UnpayOrderStatusBean>> queryUnpayOrderStatusInfo(@Body Map<String, String> map);
//
//    //获取广告配置
//    @POST(GET_AD_CONFIG)
//    Observable<BaseEntity<AdConfigRespBean>> getAdConfig(@Body Map<String, String> map);
//
//    //今日是否已经使用过优惠券
//    @POST(IS_USED_COUPON_TODAY)
//    Observable<BaseEntity<CouponUsedTodayBean>> isUsedCouponUsedToday(@Body Map<String, String> map);
//
//    //提现时是否已经使用过优惠券
//    @POST(IS_USED_COUPON_WITHDRAW)
//    Observable<BaseEntity<CouponWithdrawUsedBean>> isUsedCouponUsedWithdraw(@Body Map<String, List<String>> map);
//
//    //提现时是否已经使用过优惠券
//    @POST(GET_BONUS_INFO)
//    Observable<BaseEntity<BonusInfoBean>> getBonusInfo(@Body Map<String, String> map);
//
//    //获取提现帐户信息
//    @POST(GET_WITHDRAW_ACCOUNT_INFO)
//    Observable<BaseEntity<WithdrawAccountRepBean>> getWithdrawAccountInfo(@Body Map<String, String> map);
//
//    //验证XCROSS的Pin码
//    @POST(XCROSS_VERIFY_PIN)
//    Observable<BaseEntity<XcrossVerifyPinBean>> verifyXcrossPin(@Body Map<String, String> params);
//
//    //查询是否绑定PalmPay账户
//    @POST(EXIST_PALMPAY_ACCOUNT)
//    Observable<BaseEntity<IsExistPalmPayAccountBean>> queryExistPalmPayAccount(@Body Map<String, String> map);
//
//    //查询PalmPay钱包url
//    @POST(PALMPAY_WALLET_URL)
//    Observable<BaseEntity<PalmPayWalletUrlBean>> getPalmPayWalletUrl(@Body Map<String, String> map);
//
//    //PalmPay钱包开户
//    @POST(CREATE_PALMPAY_ACCOUNT)
//    Observable<BaseEntity<CreatePalmPayAccountBean>> createPalmPayAccount(@Body Map<String, String> map);
//
//    //提交短信反馈
//    @POST(SUBMIT_SMS_FEEDBACK)
//    Observable<BaseEntity<SMSFeedbackBean>> submitSmsFeedbackResult(@Body Map<String, String> map);
//
//    //提交bvn反馈
//    @POST(BVN_GIVE_UP_URL)
//    Observable<BaseEntity<Object>> submitGiveUp(@Body Map<String, String> map);
//
//    //检查BVN数据
//    @POST(CHECK_BVN_DATA_URL)
//    Observable<BaseEntity<CheckBvnDataReponseBean>> checkBvnData(@Body Map<String, String> map);
//
//    //flatterWave建客
//    @POST(FLW_CREATE_NEW_CUST_URL)
//    Observable<BaseEntity<VerifyCreateNewCustBean>> flwCreateNewCust(@Body Map<String, String> map);
//
//    //银行流水验证
//    @POST(BANK_STATEMENT_VERIFY)
//    Observable<BaseEntity<BankStatementVerifyBean>> bankStatementInfoVerifiy(@Body Map<String, String> params);
//
//    //上传银行流水
//    @POST(UPLOAD_BANK_STATEMENT_INFO)
//    Observable<BaseEntity<UploadBankStatementBean>> uploadBankStatementInfo(@Body Map<String, String> params);
//
//    //查询银行流水是否支持
//    @POST(BANK_SUPPORT_BANKSTATEMENT)
//    Observable<BaseEntity<List<BankSupportBankStatementBean>>> queryBankSupportBankStatement(@Body Map<String, String> params);
//
//    //发送验证码
//    @POST(SEND_MOBILE_CAPTCHA)
//    Observable<BaseEntity<Object>> sendMobileCaptcha(@Body Map<String, String> params);
//
//    //登陆第三方登陆控制显示
//    @POST(THIRD_LOGIN_SHOW)
//    Observable<BaseEntity<ThirdLoginShowBean>> getThirdLoginShow(@Body Map<String, String> params);
//
//    //语音和WhatsApp条目控制显示
//    @POST(THIRD_CAPTHCHA_WAY)
//    Observable<BaseEntity<ShowCaptchaItemBean>> getThirdCaptchaItemShow(@Body Map<String, String> params);
//
//    //登陆WhatsApp发送验证码
//    @POST(SEND_WHATSAPP_CAPTCHA_LOGIIN)
//    Observable<BaseEntity<SendCaptchaBean>> sendCaptchaFromWhatsAppForLogin(@Body Map<String, String> params);
//
//    //BVN验证WhatsApp发送验证码
//    @POST(SEND_WHATSAPP_CAPTCHA_BVN)
//    Observable<BaseEntity<SendCaptchaBean>> sendCaptchaFromWhatsAppForBVN(@Body Map<String, String> params);
//
//
//    //查询是否允许提额
//    @POST(IS_ALLOW_CREDIT_LIMIT)
//    Observable<BaseEntity<CreditLimitAllowBean>> queryAllowCreditLimit(@Body Map<String, String> params);
//
//    //查询绑卡提额剩余次数
//    @POST(GET_BIND_CARD_LIMIT_TIMES)
//    Observable<BaseEntity<BindCardLimitTimesBean>> getBindCardLimitTimes(@Body Map<String, String> params);
//
//    //获取默认银行账户
//    @POST(GET_DEFAULT_BANK_ACCOUNT)
//    Observable<BaseEntity<DefaultBankAccountBean>> getDefaultBankAccount(@Body Map<String, String> params);
//
//    //获取默认还款方式
//    @POST(GET_DEFAULT_REPAYMENT_METHODS)
//    Observable<BaseEntity<DefaultRepaymentMethodsBean>> getDefaultRepaymentMethods(@Body Map<String, String> params);
//
//    //提交还款方式
//    @POST(SUBMIT_REPAYMENT_METHODS)
//    Observable<BaseEntity<DefaultRepaymentMethodsBean>> submitRepaymentMethods(@Body Map<String, String> params);
//
//    //上传个人信息修改字段
//    @POST(UPLOAD_PROFILE_INFO)
//    Observable<BaseEntity<UploadProfileBean>> uploadProfileInfo(@Body Map<String, String> params);
//
//    //获取修改的个人信息
//    @POST(GET_PROFILE_INFO)
//    Observable<BaseEntity<MyProfileBean>> getProfileInfo(@Body Map<String, String> params);
//
//    //获取最新一笔的贷款详情
//    @POST(QUERY_PREVIOUSlOAN)
//    Observable<BaseEntity<PreviousLoanBean>> queryPreviousLoan();
//
//    //查询运营消息列表
//    @POST(OPERATOR_MESSAGE_LIST)
//    Observable<BaseEntity<OperatorMessageResponse>> queryOperatorMessage(@Body InformationListParams params);
//
//    //放弃绑账户调查
//    @POST(GIVE_UP_BIND_ACCOUNT)
//    Observable<BaseEntity<Object>> giveUpBindAccountSurvey(@Body Map<String, String> map);

    //放弃绑银行卡调查
    @POST(GIVE_UP_BIND_CARD)
    Observable<BaseEntity<Object>> giveUpBindCardSurvey(@Body Map<String, String> map);

    //欢迎页广告
    @POST(SPLASH_AD)
    Observable<BaseEntity<SplashAdInfoBean>> getSplashAdInfo(@Body Map<String, String> map);

//    /*************************XCROSS接口 必须添加Header{"url_name:xcross"} 开始**************************/
    @Headers(Contstant.HEADER_KEY+":xcross")
    @POST(SHOW_XCROSS_ENTER)
    Observable<BaseNewEntity<Object>> getXcrossOpenStatus(@Body Map<String, String> map);
//
//    @Headers(com.transsnet.commonbase.Contstant.Contstant.HEADER_KEY+":xcross")
//    @POST(IS_XCROSS_WHITELIST)
//    Observable<BaseNewEntity<Object>> IsXcrossWhiteList(@Body Map<String, String> map);
//
//    @Headers(com.transsnet.commonbase.Contstant.Contstant.HEADER_KEY+":xcross")
//    @POST(XCROSS_DEFAULT_RATE)
//    Observable<BaseNewEntity<Object>> getXcrossDefaultRate(@Body Map<String, String> map);
//
//    @Headers(com.transsnet.commonbase.Contstant.Contstant.HEADER_KEY+":xcross")
//    @POST(XCROSS_STAGE_STATUS)
//    Observable<BaseNewEntity<XcrossStageStatusBean>> getXcrossCustInfo(@Body Map<String, String> map);
//
//    @Headers(com.transsnet.commonbase.Contstant.Contstant.HEADER_KEY+":xcross")
//    @POST(XCROSS_CUST_LIMIT)
//    Observable<BaseNewEntity<XcrossCustLimitBean>> getXcrossCustLmtAmt(@Body Map<String, String> map);
//
//    @Headers(com.transsnet.commonbase.Contstant.Contstant.HEADER_KEY+":xcross")
//    @POST(XCROSS_LOAN_DETAIL)
//    Observable<BaseNewEntity<XcrossLoanDetailBean>> getXcrossLoanDetail(@Body Map<String, Object> map);
//    /*************************XCROSS接口 必须添加Header{"url_name:xcross"} 结束**************************/
}
