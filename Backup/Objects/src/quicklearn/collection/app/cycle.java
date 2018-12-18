package quicklearn.collection.app;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class cycle extends android.app.Service {
	public static class cycle_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, cycle.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static cycle mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return cycle.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "quicklearn.collection.app", "quicklearn.collection.app.cycle");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.cycle", processBA, _service);
		}
        BA.LogInfo("** Service (cycle) Create **");
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
    	BA.LogInfo("** Service (cycle) Start **");
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
        BA.LogInfo("** Service (cycle) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.widget _widget = null;
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
String _time = "";
 //BA.debugLineNum = 12;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 13;BA.debugLine="Dim time As String";
_time = "";
 //BA.debugLineNum = 14;BA.debugLine="time = myLibrary.getSetting(\"time\")";
_time = mostCurrent._mylibrary._getsetting(processBA,"time");
 //BA.debugLineNum = 15;BA.debugLine="If time.Length = 0 Then";
if (_time.length()==0) { 
 //BA.debugLineNum = 16;BA.debugLine="time = 1";
_time = BA.NumberToString(1);
 };
 //BA.debugLineNum = 18;BA.debugLine="StartActivity(cameras)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._cameras.getObject()));
 //BA.debugLineNum = 19;BA.debugLine="StartServiceAt(\"\",DateTime.Now + time * 10000,True)";
anywheresoftware.b4a.keywords.Common.StartServiceAt(processBA,(Object)(""),(long) (anywheresoftware.b4a.keywords.Common.DateTime.getNow()+(double)(Double.parseDouble(_time))*10000),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
}
