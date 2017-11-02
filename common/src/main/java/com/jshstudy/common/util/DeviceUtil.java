package com.jshstudy.common.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.UUID;

public class DeviceUtil {
	
	private static final int MAC_ADDRESS_LENGTH			= 17;
    private static final int MAC_ADDRESS_TIMEOUT		= 5000;
    
    private static final String INTERFACE_NAME_WLAN		= "wlan0";
    private static final String INTERFACE_NAME_ETHERNET	= "eth0";
    
    public static String getDeviceBoard() {
		return Build.BOARD;
	}

	public static String getDeviceBrand() {
		return Build.BRAND;
	}

	public static String getDeviceCpuAbi() {
		return Build.CPU_ABI;
	}

	public static String getDevice() {
		return Build.DEVICE;
	}

	public static String getDeviceDisplay() {
		return Build.DISPLAY;
	}

	public static String getDeviceFingerprint() {
		return Build.FINGERPRINT;
	}

	public static String getDeviceHost() {
		return Build.HOST;
	}

	public static String getDeviceId() {
		return Build.ID;
	}

	public static String getDeviceManufacturer() {
		return Build.MANUFACTURER;
	}

	public static String getDeviceProduct() {
		return Build.PRODUCT;
	}

	public static String getDeviceTags() {
		return Build.TAGS;
	}

	public static String getDeviceType() {
		return Build.TYPE;
	}

	public static long getDeviceTime() {
		return Build.TIME;
	}

	public static String getDeviceUser() {
		return Build.USER;
	}

	public static String getDeviceModel() {
		return Build.MODEL;
	}
	
	/**
	 * 음성상태 조회
	 * @param context
	 * @return CALL_STATE_IDLE/CALL_STATE_OFFHOOK/CALL_STATE_RINGING 등의 값을 반환
	 */
	public static String getCallState(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(tm.getCallState());
	}
	
	/**
	 * 데이터통신 상태 조회
	 * @param context
	 * @return DATA_DISCONNECTED/DATA_CONNECTING/DATA_CONNECTED/DATA_SUSPENDED 등의 값을 반환
	 */
	public static String getDataActivity(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(tm.getDataActivity());
	}
	
	/**
	 * 단말기 ID 조회
	 * @param context
	 * @return GSM방식의 IMEI/SV와 같은 SW버전을 반환
	 */
	public static String getDeviceId(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	
	/**
	 * 전화번호 조회
	 * @param context
	 * @return GSM방식의 MSISDN과 같은 전화번호 반환
	 */
	public static String getLineNumber(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}
	
	/**
	 * 국가코드 조회
	 * @param context
	 * @return 현재 등록된 망 사업자의 MCC(Mobile Country Code)에 대한 ISO 국가코드 반환
	 */
	public static String getNetworkCountryIso(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getNetworkCountryIso();
	}
	
	/**
	 * 망 사업자코드 조회
	 * @param context
	 * @return 현재 등록된 망 사업자의 MCC+MNC(Mobile Network Code) 반환
	 */
	public static String getNetworkOperator(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getNetworkOperator();
	}
	
	/**
	 * 망 사업자명 조회
	 * @param context
	 * @return
	 */
	public static String getNetworkOperatorName(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getNetworkOperatorName();
	}
	
	/**
	 * 망 시스템 방식 조회
	 * @param context
	 * @return 현재 단말기에서 사용중인 망 시스템 방식을 반환
	 * ETWORK_TYPE_UNKNOWN/
		GSM방식 :  NETWORK_TYPE_GPRS/NETWORK_TYPE_EDGE/NETWORK_TYPE_UMTS/
			NETWORK_TYPE_HSDPA/NETWORK_TYPE_HSUPA/NETWORK_TYPE_HSPA
		CDMA방식 : NETWORK_TYPE_CDMA/NETWORK_TYPE_EVDO_0/NETWORK_TYPE_EVDO_A/NETWORK_TYPE_1xRTT
	 */
	public static String getNetworkType(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(tm.getNetworkType());
	}
	
	/**
	 * 단말기 종류 조회
	 * @param context
	 * @return 단말기에서 지원하는 망의 종류를 반환 PHONE_TYPE_NONE/PHONE_TYPE_GSM/PHONE_TYPE_CDMA 등의 값을 반환
	 */
	public static String getPhoneType(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(tm.getPhoneType());
	}
	
	/**
	 * SIM카드 Serial Number 조회
	 * @param context
	 * @return
	 */
	public static String getSimSerialNumber(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimSerialNumber();
	}
	
	/**
	 * SIM카드 상태 조회
	 * @param context
	 * @return SIM_STATE_UNKNOWN/SIM_STATE_ABSENT/SIM_STATE_PIN_REQUIRED/SIM_STATE_PUK_REQUIRED/
				SIM_STATE_NETWORK_LOCKED/SIM_STATE_READY 등의 값을 반환
	 */
	public static String getSimState(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return String.valueOf(tm.getSimState());
	}
	
	/**
	 * 가입자 ID 조회
	 * @param context
	 * @return
	 */
	public static String getSubscriberId(Context context){
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSubscriberId();
	}
    
	/**
	 * 단말 폰 번호를 조회한다.
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context) {
    	String number = null;
		try{
			TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			number = telephonyManager.getLine1Number();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		
    	return number;		
	}
	
	/**
	 * 단말 IMEI를 조회한다.
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
    	String imei = null;
		try{
			TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			imei = telephonyManager.getDeviceId();
		}catch(Exception e)	{
			e.printStackTrace();
		}
		
    	return imei;
	}
	
	/**
	 * unique한 UUID를 생성하여 반환한다.
	 * @param context
	 * @return
	 */
	public static UUID getUUID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
//		String deviceId = deviceUuid.toString();
		
		return deviceUuid;
	}
	
	/**
	 * 단말 LCD 넓이를 반환한다..
	 * @param context
	 * @return
	 */
	public static int getLCDWidth(Context context)
	{
		return DisplayUtil.getScreenWidth(context);
	}
	
	/**
	 * 단말 LCD 높이를 반환한다.
	 * @param context
	 * @return
	 */
	public static int getLCDHeight(Context context)
	{
		return DisplayUtil.getScreenHeight(context);
	}	
	
	/**
	 * 단말 OS 버전을 반환한다.
	 * @param context
	 * @return
	 */
	public static String getOSVersion(Context context)
	{
		return Build.VERSION.RELEASE;
	}
	
	/**
	 * 와이파이가 가능여부를 반환한다.
	 * @param context
	 * @return
	 */
	public static boolean isEnableWifi( Context context )
    {
        WifiManager wifiMgr = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo 	= wifiMgr.getConnectionInfo();

        boolean bEnabled = (wifiMgr.isWifiEnabled() == true && wInfo.getSSID() != null );

        return bEnabled;
    }
	
	/**
	 * 단말에서 (EtherNet or WIFI) MAC Address 가져온다.
	 * (WIFI가 꺼져 있을 경우 켜서 가져오도록 한다.)
	 * 00:0F:E4:01:BD:D9 -> 000FE401BDD9
	 */
	private static String getMacAddress(Context context)
	{
		String strMacAddress = "";
		//1) Wifi 의 맥주소
		strMacAddress = getWifiMacAddress(context, MAC_ADDRESS_TIMEOUT);
		if(strMacAddress != null && strMacAddress.length() == MAC_ADDRESS_LENGTH) {
			strMacAddress = toMacAddressByColon(strMacAddress);
//			String encodeMacAddress = StringEncoder.encrypt(strMacAddress);
//			CONFIG.APP_INFO.setString(context, CONFIG.KEY_STR_MAC_ADDRESS, encodeMacAddress);	
			return strMacAddress;
		}
		
		//2) Ethernet 의 맥주소
//		strMacAddress = getMacAddress(INTERFACE_NAME_ETHERNET);
//		if(strMacAddress != null && strMacAddress.length() == MAC_ADDRESS_LENGTH) {
//			strMacAddress = toMacAddressByColon(strMacAddress);
//			String encodeMacAddress = StringEncoder.encrypt(strMacAddress);
//			CONFIG.APP_INFO.setString(context, CONFIG.KEY_STR_MAC_ADDRESS, encodeMacAddress);	
//			Trace.Debug("-- Utility.getMacAddress(" + strMacAddress + ")");
//		 
//			return strMacAddress;
//		}
		return "";
	}
	
	private static String getWifiMacAddress(Context context, int nTimeoutMillisecond) {
		
		String strWifiMacAddress = "";

		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();

		strWifiMacAddress = wInfo.getMacAddress();       
        if( strWifiMacAddress != null && strWifiMacAddress.length() == MAC_ADDRESS_LENGTH ) {
        	return strWifiMacAddress;
        } 
                   
        boolean bIsWifiEnable = wifiManager.isWifiEnabled();
        if(!bIsWifiEnable) {
        	wifiManager.setWifiEnabled(true);
        }

        for (int cnt = 0; cnt < (nTimeoutMillisecond/100); cnt++) {
        	wInfo = wifiManager.getConnectionInfo();
        	strWifiMacAddress = wInfo.getMacAddress();
        	
        	if( strWifiMacAddress != null && strWifiMacAddress.length() == MAC_ADDRESS_LENGTH ) break;
        	
			try {
				Thread.sleep(100);
			} catch(Exception e) {e.printStackTrace();}
        }
        
        if(!bIsWifiEnable) {
        	wifiManager.setWifiEnabled(false);
        }
        
        return strWifiMacAddress;
	}
	
	private static String toMacAddressByColon(String strMac)
	{
		String strMacAddress = "";
		
		if(strMac != null && (strMac.length() > 0)) {
			strMacAddress += strMac.substring(0, 2);
			strMacAddress += strMac.substring(3, 5);
			strMacAddress += strMac.substring(6, 8);
			strMacAddress += strMac.substring(9, 11);
			strMacAddress += strMac.substring(12, 14);
			strMacAddress += strMac.substring(15, 17);
			strMacAddress = strMacAddress.toUpperCase();
		}

		return strMacAddress;		
	}
}
