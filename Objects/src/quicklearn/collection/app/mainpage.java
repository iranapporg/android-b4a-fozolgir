package quicklearn.collection.app;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class mainpage extends Activity implements B4AActivity{
	public static mainpage mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "quicklearn.collection.app", "quicklearn.collection.app.mainpage");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (mainpage).");
				p.finish();
			}
		}
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "quicklearn.collection.app", "quicklearn.collection.app.mainpage");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.mainpage", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (mainpage) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (mainpage) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return mainpage.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (mainpage) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (mainpage) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_yekan = null;
public anywheresoftware.b4a.keywords.constants.TypefaceWrapper _font_icon = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_title = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_icon1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_firstitle1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_secondtitle1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_firsttitle2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_secondtitle2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_icon2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_firsttitle3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_icon3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _l_secondtitle3 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _lblexit = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _btnservice = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblservice = null;
public anywheresoftware.b4a.object.RippleViewWrapper _a1 = null;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.splash _splash = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.widget _widget = null;
public quicklearn.collection.app.cycle _cycle = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public quicklearn.collection.app.handlesms _handlesms = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 33;BA.debugLine="Activity.LoadLayout(\"mainpage\")";
mostCurrent._activity.LoadLayout("mainpage",mostCurrent.activityBA);
 //BA.debugLineNum = 34;BA.debugLine="l_title.Typeface=font_yekan";
mostCurrent._l_title.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 35;BA.debugLine="l_icon1.Typeface=font_icon";
mostCurrent._l_icon1.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 36;BA.debugLine="lblexit.Typeface=font_icon";
mostCurrent._lblexit.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 37;BA.debugLine="l_firstitle1.Typeface=font_yekan";
mostCurrent._l_firstitle1.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 38;BA.debugLine="l_secondtitle1.Typeface=font_yekan";
mostCurrent._l_secondtitle1.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="l_icon2.Typeface=font_icon";
mostCurrent._l_icon2.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="l_firsttitle2.Typeface=font_yekan";
mostCurrent._l_firsttitle2.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="l_secondtitle2.Typeface=font_yekan";
mostCurrent._l_secondtitle2.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="l_icon3.Typeface=font_icon";
mostCurrent._l_icon3.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 43;BA.debugLine="l_firsttitle3.Typeface=font_yekan";
mostCurrent._l_firsttitle3.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 44;BA.debugLine="l_secondtitle3.Typeface=font_yekan";
mostCurrent._l_secondtitle3.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 45;BA.debugLine="lblservice.Typeface=font_yekan";
mostCurrent._lblservice.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 47;BA.debugLine="If IsPaused(cycle) = True Then";
if (anywheresoftware.b4a.keywords.Common.IsPaused(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 48;BA.debugLine="btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,\"run.png\"))";
mostCurrent._btnservice.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"run.png").getObject()));
 //BA.debugLineNum = 49;BA.debugLine="lblservice.Text = \"متوقف شد\"";
mostCurrent._lblservice.setText((Object)("متوقف شد"));
 }else {
 //BA.debugLineNum = 51;BA.debugLine="btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,\"stop.png\"))";
mostCurrent._btnservice.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"stop.png").getObject()));
 //BA.debugLineNum = 52;BA.debugLine="lblservice.Text = \"در حال اجرا\"";
mostCurrent._lblservice.setText((Object)("در حال اجرا"));
 };
 //BA.debugLineNum = 55;BA.debugLine="a1.Initialize(lblservice,Colors.Red,9000,True)";
mostCurrent._a1.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._lblservice.getObject()),anywheresoftware.b4a.keywords.Common.Colors.Red,(int) (9000),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 56;BA.debugLine="myLibrary.AnimationView(lblservice,False,800)";
mostCurrent._mylibrary._animationview(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._lblservice.getObject())),anywheresoftware.b4a.keywords.Common.False,(int) (800));
 //BA.debugLineNum = 58;BA.debugLine="StartService(handleCall)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._handlecall.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="StartService(handleSMS)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._handlesms.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 78;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 79;BA.debugLine="If Msgbox2(\"آیا مایل به خروج هستید؟\",\"خروج\",\"آری\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا مایل به خروج هستید؟","خروج","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 80;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 82;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_HOME) { 
 //BA.debugLineNum = 84;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 72;BA.debugLine="If UserClosed = False Then";
if (_userclosed==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 73;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _ani_animationend() throws Exception{
 //BA.debugLineNum = 63;BA.debugLine="Sub ani_AnimationEnd";
 //BA.debugLineNum = 64;BA.debugLine="lblservice.Visible = False";
mostCurrent._lblservice.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _btnhelp_click() throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub btnhelp_Click";
 //BA.debugLineNum = 112;BA.debugLine="StartActivity(help)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._help.getObject()));
 //BA.debugLineNum = 113;BA.debugLine="myLibrary.SetAnimation(\"file2\",\"file1\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file2","file1");
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _btnpic_click() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Sub btnpic_Click";
 //BA.debugLineNum = 94;BA.debugLine="StartActivity(gallery)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._gallery.getObject()));
 //BA.debugLineNum = 95;BA.debugLine="myLibrary.SetAnimation(\"file2\",\"file1\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file2","file1");
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _btnservice_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub btnservice_Click";
 //BA.debugLineNum = 117;BA.debugLine="If IsPaused(cycle) = True Then";
if (anywheresoftware.b4a.keywords.Common.IsPaused(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()))==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 118;BA.debugLine="btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,\"stop.png\"))";
mostCurrent._btnservice.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"stop.png").getObject()));
 //BA.debugLineNum = 119;BA.debugLine="lblservice.Text = \"در حال اجرا\"";
mostCurrent._lblservice.setText((Object)("در حال اجرا"));
 //BA.debugLineNum = 120;BA.debugLine="myLibrary.saveService(True)";
mostCurrent._mylibrary._saveservice(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 121;BA.debugLine="StartService(cycle)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()));
 }else {
 //BA.debugLineNum = 123;BA.debugLine="btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,\"run.png\"))";
mostCurrent._btnservice.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"run.png").getObject()));
 //BA.debugLineNum = 124;BA.debugLine="lblservice.Text = \"متوقف شد\"";
mostCurrent._lblservice.setText((Object)("متوقف شد"));
 //BA.debugLineNum = 125;BA.debugLine="myLibrary.saveService(False)";
mostCurrent._mylibrary._saveservice(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 126;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 127;BA.debugLine="CancelScheduledService(cycle)";
anywheresoftware.b4a.keywords.Common.CancelScheduledService(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()));
 };
 //BA.debugLineNum = 129;BA.debugLine="lblservice.Visible = True";
mostCurrent._lblservice.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 130;BA.debugLine="myLibrary.AnimationView(lblservice,False,800)";
mostCurrent._mylibrary._animationview(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._lblservice.getObject())),anywheresoftware.b4a.keywords.Common.False,(int) (800));
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
public static String  _btnsetting_click() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub btnsetting_Click";
 //BA.debugLineNum = 89;BA.debugLine="StartActivity(settings)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._settings.getObject()));
 //BA.debugLineNum = 90;BA.debugLine="myLibrary.SetAnimation(\"file2\",\"file1\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file2","file1");
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim font_yekan As Typeface";
mostCurrent._font_yekan = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 12;BA.debugLine="font_yekan=Typeface.LoadFromAssets(\"BYekan.ttf\")";
mostCurrent._font_yekan.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("BYekan.ttf")));
 //BA.debugLineNum = 13;BA.debugLine="Dim font_icon As Typeface";
mostCurrent._font_icon = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
 //BA.debugLineNum = 14;BA.debugLine="font_icon=Typeface.LoadFromAssets(\"icomoon.ttf\")";
mostCurrent._font_icon.setObject((android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("icomoon.ttf")));
 //BA.debugLineNum = 15;BA.debugLine="Private l_title As Label";
mostCurrent._l_title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private l_icon1 As Label";
mostCurrent._l_icon1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private l_firstitle1 As Label";
mostCurrent._l_firstitle1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private l_secondtitle1 As Label";
mostCurrent._l_secondtitle1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private l_firsttitle2 As Label";
mostCurrent._l_firsttitle2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private l_secondtitle2 As Label";
mostCurrent._l_secondtitle2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Private l_icon2 As Label";
mostCurrent._l_icon2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private l_firsttitle3 As Label";
mostCurrent._l_firsttitle3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private l_icon3 As Label";
mostCurrent._l_icon3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private l_secondtitle3 As Label";
mostCurrent._l_secondtitle3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lblexit As Button";
mostCurrent._lblexit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private btnservice As ImageView";
mostCurrent._btnservice = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lblservice As Label";
mostCurrent._lblservice = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim a1 As RippleView";
mostCurrent._a1 = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_click() throws Exception{
 //BA.debugLineNum = 102;BA.debugLine="Sub lblexit_Click";
 //BA.debugLineNum = 103;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 31, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (31),(int) (26)));
 //BA.debugLineNum = 104;BA.debugLine="If Msgbox2(\"آیا مایل به خروج هستید؟\",\"خروج\",\"آری\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا مایل به خروج هستید؟","خروج","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 105;BA.debugLine="StopService(cycle)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._cycle.getObject()));
 //BA.debugLineNum = 106;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 108;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 91, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (91),(int) (26)));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_down() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub lblexit_Down";
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
