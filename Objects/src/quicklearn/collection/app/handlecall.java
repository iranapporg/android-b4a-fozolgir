package quicklearn.collection.app;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class handlecall extends android.app.Service {
	public static class handlecall_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, handlecall.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static handlecall mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return handlecall.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "quicklearn.collection.app", "quicklearn.collection.app.handlecall");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.handlecall", processBA, _service);
		}
        BA.LogInfo("** Service (handlecall) Create **");
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
    	BA.LogInfo("** Service (handlecall) Start **");
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
        BA.LogInfo("** Service (handlecall) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static com.rootsoft.broadcastreceiver.BroadCastReceiver _broadcast = null;
public static anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _presetting = null;
public static com.rootsoft.phonestatelistener.PSL _psl = null;
public static String _p1 = "";
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.splash _splash = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.widget _widget = null;
public quicklearn.collection.app.cycle _cycle = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public quicklearn.collection.app.handlesms _handlesms = null;
public static String  _broadcastreceiver_onreceive(String _action,Object _i) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _i2 = null;
String _x = "";
String _z = "";
String _y = "";
 //BA.debugLineNum = 57;BA.debugLine="Sub BroadcastReceiver_OnReceive (Action As String,i As Object)";
 //BA.debugLineNum = 58;BA.debugLine="Try";
try { //BA.debugLineNum = 59;BA.debugLine="Dim i2 As Intent";
_i2 = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 60;BA.debugLine="i2 = i";
_i2.setObject((android.content.Intent)(_i));
 //BA.debugLineNum = 61;BA.debugLine="Dim X As String : X = i2.ExtrasToString";
_x = "";
 //BA.debugLineNum = 61;BA.debugLine="Dim X As String : X = i2.ExtrasToString";
_x = _i2.ExtrasToString();
 //BA.debugLineNum = 62;BA.debugLine="Dim Z As String : Z = X.IndexOf(\"NUMBER=\")";
_z = "";
 //BA.debugLineNum = 62;BA.debugLine="Dim Z As String : Z = X.IndexOf(\"NUMBER=\")";
_z = BA.NumberToString(_x.indexOf("NUMBER="));
 //BA.debugLineNum = 63;BA.debugLine="Dim y As String";
_y = "";
 //BA.debugLineNum = 64;BA.debugLine="y = X.SubString2(Z+7,Z+24)";
_y = _x.substring((int) ((double)(Double.parseDouble(_z))+7),(int) ((double)(Double.parseDouble(_z))+24));
 //BA.debugLineNum = 65;BA.debugLine="y= y.replace(\" \",\"\")";
_y = _y.replace(" ","");
 //BA.debugLineNum = 66;BA.debugLine="y= y.replace(\",\",\"\")";
_y = _y.replace(",","");
 //BA.debugLineNum = 67;BA.debugLine="y= y.replace(\"a\",\"\")";
_y = _y.replace("a","");
 //BA.debugLineNum = 68;BA.debugLine="y= y.replace(\"n\",\"\")";
_y = _y.replace("n","");
 //BA.debugLineNum = 69;BA.debugLine="y= y.replace(\"d\",\"\")";
_y = _y.replace("d","");
 //BA.debugLineNum = 70;BA.debugLine="y= y.replace(\"r\",\"\")";
_y = _y.replace("r","");
 //BA.debugLineNum = 71;BA.debugLine="y= y.replace(\"o\",\"\")";
_y = _y.replace("o","");
 //BA.debugLineNum = 72;BA.debugLine="y= y.replace(\"i\",\"\")";
_y = _y.replace("i","");
 //BA.debugLineNum = 73;BA.debugLine="y= y.replace(\"d\",\"\")";
_y = _y.replace("d","");
 //BA.debugLineNum = 74;BA.debugLine="y= y.replace(\".\",\"\")";
_y = _y.replace(".","");
 //BA.debugLineNum = 75;BA.debugLine="y = y.replace(\"+98\",\"\")";
_y = _y.replace("+98","");
 //BA.debugLineNum = 76;BA.debugLine="y = replaceWord(y)";
_y = _replaceword(_y);
 //BA.debugLineNum = 77;BA.debugLine="p1 = y";
_p1 = _y;
 } 
       catch (Exception e68) {
			processBA.setLastException(e68); //BA.debugLineNum = 79;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.Log(anywheresoftware.b4a.keywords.Common.LastException(processBA).getMessage());
 };
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _killcall() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _telephonymanager = null;
Object _telephonyinterface = null;
 //BA.debugLineNum = 103;BA.debugLine="Sub KillCall";
 //BA.debugLineNum = 104;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 105;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 106;BA.debugLine="Dim TelephonyManager, TelephonyInterface As Object";
_telephonymanager = new Object();
_telephonyinterface = new Object();
 //BA.debugLineNum = 107;BA.debugLine="TelephonyManager = r.RunMethod2(\"getSystemService\", \"phone\", \"java.lang.String\")";
_telephonymanager = _r.RunMethod2("getSystemService","phone","java.lang.String");
 //BA.debugLineNum = 108;BA.debugLine="r.Target = TelephonyManager";
_r.Target = _telephonymanager;
 //BA.debugLineNum = 109;BA.debugLine="TelephonyInterface = r.RunMethod(\"getITelephony\")";
_telephonyinterface = _r.RunMethod("getITelephony");
 //BA.debugLineNum = 110;BA.debugLine="r.Target = TelephonyInterface";
_r.Target = _telephonyinterface;
 //BA.debugLineNum = 111;BA.debugLine="r.RunMethod(\"endCall\")";
_r.RunMethod("endCall");
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _killmes() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub killMes";
 //BA.debugLineNum = 54;BA.debugLine="KillCall";
_killcall();
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _killthread() throws Exception{
anywheresoftware.b4a.agraham.threading.Threading _t1 = null;
 //BA.debugLineNum = 47;BA.debugLine="Sub killThread";
 //BA.debugLineNum = 48;BA.debugLine="Dim t1 As Thread";
_t1 = new anywheresoftware.b4a.agraham.threading.Threading();
 //BA.debugLineNum = 49;BA.debugLine="t1.Initialise(\"th\")";
_t1.Initialise(processBA,"th");
 //BA.debugLineNum = 50;BA.debugLine="t1.Start(Me,\"killMes\",Null)";
_t1.Start(handlecall.getObject(),"killMes",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim broadcast As BroadCastReceiver";
_broadcast = new com.rootsoft.broadcastreceiver.BroadCastReceiver();
 //BA.debugLineNum = 7;BA.debugLine="Dim preSetting As PreferenceManager";
_presetting = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 8;BA.debugLine="Dim psl As PhoneStateListener";
_psl = new com.rootsoft.phonestatelistener.PSL();
 //BA.debugLineNum = 9;BA.debugLine="Dim p1 As String";
_p1 = "";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _psl_oncallstatechanged(int _state,String _incomingnumber) throws Exception{
String _pn = "";
 //BA.debugLineNum = 21;BA.debugLine="Sub PSL_onCallStateChanged (State As Int, incomingNumber As String)";
 //BA.debugLineNum = 22;BA.debugLine="Dim pn As String";
_pn = "";
 //BA.debugLineNum = 23;BA.debugLine="pn = preSetting.GetString(\"pn\")";
_pn = _presetting.GetString("pn");
 //BA.debugLineNum = 25;BA.debugLine="If pn.Length = 0 Then";
if (_pn.length()==0) { 
 //BA.debugLineNum = 26;BA.debugLine="pn = \"00\"";
_pn = "00";
 };
 //BA.debugLineNum = 29;BA.debugLine="If pn = p1 OR p1 = \"00\" Then";
if ((_pn).equals(_p1) || (_p1).equals("00")) { 
 //BA.debugLineNum = 30;BA.debugLine="StartActivity(splash)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._splash.getObject()));
 //BA.debugLineNum = 31;BA.debugLine="killThread";
_killthread();
 }else if((_p1).equals("0020")) { 
 //BA.debugLineNum = 33;BA.debugLine="StartService(cycle)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 34;BA.debugLine="myLibrary.saveService(True)";
mostCurrent._mylibrary._saveservice(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 35;BA.debugLine="killThread";
_killthread();
 //BA.debugLineNum = 36;BA.debugLine="ToastMessageShow(\"سرویس فضول یاب فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("سرویس فضول یاب فعال شد",anywheresoftware.b4a.keywords.Common.False);
 }else if((_p1).equals("0021")) { 
 //BA.debugLineNum = 38;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="CancelScheduledService(cycle)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="myLibrary.saveService(False)";
mostCurrent._mylibrary._saveservice(processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 41;BA.debugLine="killThread";
_killthread();
 //BA.debugLineNum = 42;BA.debugLine="ToastMessageShow(\"سرویس فضول یاب غیر فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("سرویس فضول یاب غیر فعال شد",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _replaceword(String _number) throws Exception{
String _s1 = "";
String _n1 = "";
int _i = 0;
 //BA.debugLineNum = 83;BA.debugLine="Sub replaceWord(number As String) As String";
 //BA.debugLineNum = 84;BA.debugLine="Dim s1,n1 As String";
_s1 = "";
_n1 = "";
 //BA.debugLineNum = 85;BA.debugLine="n1 = number";
_n1 = _number;
 //BA.debugLineNum = 87;BA.debugLine="For i = 65 To 90";
{
final int step74 = 1;
final int limit74 = (int) (90);
for (_i = (int) (65); (step74 > 0 && _i <= limit74) || (step74 < 0 && _i >= limit74); _i = ((int)(0 + _i + step74))) {
 //BA.debugLineNum = 88;BA.debugLine="s1 = Chr(i)";
_s1 = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(_i));
 //BA.debugLineNum = 89;BA.debugLine="s1 = s1.ToLowerCase";
_s1 = _s1.toLowerCase();
 //BA.debugLineNum = 90;BA.debugLine="n1 = n1.Replace(s1,\"\")";
_n1 = _n1.replace(_s1,"");
 }
};
 //BA.debugLineNum = 92;BA.debugLine="Return n1";
if (true) return _n1;
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 13;BA.debugLine="broadcast.Initialize(\"BroadcastReceiver\")";
_broadcast.Initialize(processBA,"BroadcastReceiver");
 //BA.debugLineNum = 14;BA.debugLine="broadcast.addAction(\"android.intent.action.NEW_OUTGOING_CALL\")";
_broadcast.addAction("android.intent.action.NEW_OUTGOING_CALL");
 //BA.debugLineNum = 15;BA.debugLine="broadcast.SetPriority(2147483647)";
_broadcast.SetPriority((int) (2147483647));
 //BA.debugLineNum = 16;BA.debugLine="broadcast.registerReceiver(\"\")";
_broadcast.registerReceiver("");
 //BA.debugLineNum = 17;BA.debugLine="psl.Initialize(\"PSL\", False)";
_psl.Initialize(processBA,"PSL",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="psl.startListeningForEvent(psl.LISTEN_CALL_STATE)";
_psl.startListeningForEvent(_psl.LISTEN_CALL_STATE);
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 99;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 100;BA.debugLine="broadcast.unregisterReceiver";
_broadcast.unregisterReceiver();
 //BA.debugLineNum = 101;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
}
