package quicklearn.collection.app;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class widget extends android.app.Service {
	public static class widget_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, widget.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static widget mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return widget.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "quicklearn.collection.app", "quicklearn.collection.app.widget");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.widget", processBA, _service);
		}
        BA.LogInfo("** Service (widget) Create **");
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
    	BA.LogInfo("** Service (widget) Start **");
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
        BA.LogInfo("** Service (widget) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.RemoteViewsWrapper _r1 = null;
public static anywheresoftware.b4a.objects.Timer _timer1 = null;
public static anywheresoftware.b4a.phone.PhoneEvents _ph = null;
public static int _i1 = 0;
public static anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _presetting = null;
public static String _iconname = "";
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.splash _splash = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.cycle _cycle = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public quicklearn.collection.app.handlesms _handlesms = null;
public static String  _changeicon() throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Sub changeIcon";
 //BA.debugLineNum = 59;BA.debugLine="IconName = preSetting.GetString(\"widgetIcon\")";
_iconname = _presetting.GetString("widgetIcon");
 //BA.debugLineNum = 60;BA.debugLine="If IconName.Length > 0 Then";
if (_iconname.length()>0) { 
 //BA.debugLineNum = 61;BA.debugLine="r1.SetImage(\"img1\",LoadBitmap(File.DirAssets,IconName))";
_r1.SetImage(processBA,"img1",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_iconname).getObject()));
 //BA.debugLineNum = 62;BA.debugLine="r1.UpdateWidget";
_r1.UpdateWidget(processBA);
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _evnwidget_requestupdate() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub evnWidget_RequestUpdate";
 //BA.debugLineNum = 45;BA.debugLine="r1.UpdateWidget";
_r1.UpdateWidget(processBA);
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _img1_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub img1_Click";
 //BA.debugLineNum = 67;BA.debugLine="timer1.Enabled = False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 68;BA.debugLine="If IsPaused(cycle) = True Then";
if (anywheresoftware.b4a.keywords.Common.IsPaused(processBA,(Object)(mostCurrent._cycle.getObject()))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 69;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 70;BA.debugLine="CancelScheduledService(cycle)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 71;BA.debugLine="ToastMessageShow(\"برنامه غیر فعال شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("برنامه غیر فعال شد",anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 73;BA.debugLine="ToastMessageShow(\"دیگه دیر شده\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("دیگه دیر شده",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim r1 As RemoteViews";
_r1 = new anywheresoftware.b4a.objects.RemoteViewsWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Dim timer1 As Timer";
_timer1 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 8;BA.debugLine="Dim ph As PhoneEvents";
_ph = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 9;BA.debugLine="Dim i1 As Int : i1 = 1";
_i1 = 0;
 //BA.debugLineNum = 9;BA.debugLine="Dim i1 As Int : i1 = 1";
_i1 = (int) (1);
 //BA.debugLineNum = 10;BA.debugLine="Dim preSetting As PreferenceManager";
_presetting = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 11;BA.debugLine="Dim IconName As String";
_iconname = "";
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _screen_screenoff(anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub screen_ScreenOff (Intent As Intent)";
 //BA.debugLineNum = 28;BA.debugLine="timer1.Enabled = False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 29;BA.debugLine="i1 = 1";
_i1 = (int) (1);
 //BA.debugLineNum = 30;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 31;BA.debugLine="CancelScheduledService(cycle)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(processBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _screen_screenon(anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub screen_ScreenOn (Intent As Intent)";
 //BA.debugLineNum = 21;BA.debugLine="If myLibrary.getSettingBoolean(\"service\") = True Then";
if ((mostCurrent._mylibrary._getsettingboolean(processBA,"service")).equals(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 22;BA.debugLine="timer1.Initialize(\"t1\",1000)";
_timer1.Initialize(processBA,"t1",(long) (1000));
 //BA.debugLineNum = 23;BA.debugLine="timer1.Enabled = True";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 15;BA.debugLine="r1 = ConfigureHomeWidget(\"widget\",\"evnWidget\",0,\"\",False)";
_r1 = anywheresoftware.b4a.objects.RemoteViewsWrapper.createRemoteViews(processBA, R.layout.widget_layout, "widget","evnWidget");
 //BA.debugLineNum = 16;BA.debugLine="ph.Initialize(\"screen\")";
_ph.Initialize(processBA,"screen");
 //BA.debugLineNum = 17;BA.debugLine="changeIcon";
_changeicon();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 55;BA.debugLine="StopService(\"\")";
anywheresoftware.b4a.keywords.Common.StopService(processBA,(Object)(""));
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 49;BA.debugLine="If r1.HandleWidgetEvents(StartingIntent) Then";
if (_r1.HandleWidgetEvents(processBA,(android.content.Intent)(_startingintent.getObject()))) { 
 //BA.debugLineNum = 50;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _t1_tick() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub t1_Tick";
 //BA.debugLineNum = 35;BA.debugLine="i1 = i1 + 1";
_i1 = (int) (_i1+1);
 //BA.debugLineNum = 36;BA.debugLine="If i1 = 10 Then";
if (_i1==10) { 
 //BA.debugLineNum = 37;BA.debugLine="timer1.Enabled = False";
_timer1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 38;BA.debugLine="If myLibrary.getSettingBoolean(\"service\") = True Then";
if ((mostCurrent._mylibrary._getsettingboolean(processBA,"service")).equals(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.True))) { 
 //BA.debugLineNum = 39;BA.debugLine="StartService(cycle)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._cycle.getObject()));
 };
 };
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
}
