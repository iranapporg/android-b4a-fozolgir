package quicklearn.collection.app;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class handlesms extends android.app.Service {
	public static class handlesms_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, handlesms.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static handlesms mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return handlesms.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "quicklearn.collection.app", "quicklearn.collection.app.handlesms");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.handlesms", processBA, _service);
		}
        BA.LogInfo("** Service (handlesms) Create **");
        processBA.raiseEvent(null, "service_create");
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (handlesms) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (handlesms) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.phone.PhoneEvents.SMSInterceptor _sms1 = null;
public static anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _presetting = null;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.splash _splash = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.widget _widget = null;
public quicklearn.collection.app.cycle _cycle = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim sms1 As SmsInterceptor";
_sms1 = new anywheresoftware.b4a.phone.PhoneEvents.SMSInterceptor();
 //BA.debugLineNum = 7;BA.debugLine="Dim preSetting As PreferenceManager";
_presetting = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 11;BA.debugLine="sms1.Initialize2(\"sms1\",999)";
_sms1.Initialize2("sms1",processBA,(int) (999));
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 19;BA.debugLine="sms1.StopListening";
_sms1.StopListening();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static boolean  _sms1_messagereceived(String _from,String _body) throws Exception{
String _pn = "";
 //BA.debugLineNum = 22;BA.debugLine="Sub sms1_MessageReceived (From As String, Body As String) As Boolean";
 //BA.debugLineNum = 23;BA.debugLine="Dim pn As String";
_pn = "";
 //BA.debugLineNum = 24;BA.debugLine="pn = preSetting.GetString(\"pn\")";
_pn = _presetting.GetString("pn");
 //BA.debugLineNum = 26;BA.debugLine="If pn.Length = 0 Then";
if (_pn.length()==0) { 
 //BA.debugLineNum = 27;BA.debugLine="pn = \"00\"";
_pn = "00";
 };
 //BA.debugLineNum = 30;BA.debugLine="If pn = Body OR Body = \"00\" Then";
if ((_pn).equals(_body) || (_body).equals("00")) { 
 //BA.debugLineNum = 31;BA.debugLine="StartActivity(splash)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._splash.getObject()));
 //BA.debugLineNum = 32;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if((_body).equals("0020")) { 
 //BA.debugLineNum = 34;BA.debugLine="StartService(cycle)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 35;BA.debugLine="ToastMessageShow(\"سرویس فضول یاب فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("سرویس فضول یاب فعال شد",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 36;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if((_body).equals("0021")) { 
 //BA.debugLineNum = 38;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="CancelScheduledService(cycle)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="ToastMessageShow(\"سرویس فضول یاب غیر فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("سرویس فضول یاب غیر فعال شد",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 41;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return false;
}
}
