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

public class settings extends Activity implements B4AActivity{
	public static settings mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "quicklearn.collection.app", "quicklearn.collection.app.settings");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (settings).");
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
		activityBA = new BA(this, layout, processBA, "quicklearn.collection.app", "quicklearn.collection.app.settings");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.settings", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (settings) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (settings) Resume **");
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
//        try {
//            if (processBA.subExists("activity_actionbarhomeclick")) {
//                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
//                    getClass().getMethod("getActionBar").invoke(this), true);
//                BA.Log("adding event");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		return true;
	}   
// @Override
// public boolean onOptionsItemSelected(android.view.MenuItem item) {
//    if (item.getItemId() == 16908332) {
//        processBA.raiseEvent(null, "activity_actionbarhomeclick");
//        return true;
//    }
//    else
//        return super.onOptionsItemSelected(item); 
//}
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
		return settings.class;
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
        BA.LogInfo("** Activity (settings) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (settings) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _pnloverlay = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmsg = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtval = null;
public static boolean _blnsetting = false;
public anywheresoftware.b4a.objects.ButtonWrapper _btnverify = null;
public anywheresoftware.b4a.objects.ButtonWrapper _lblexit = null;
public static String _changelogtime = "";
public static String _changelogpass = "";
public static boolean _blnpass = false;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.mylibrary _mylibrary = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.widget _widget = null;
public quicklearn.collection.app.cycle _cycle = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"settings\")";
mostCurrent._activity.LoadLayout("settings",mostCurrent.activityBA);
 //BA.debugLineNum = 39;BA.debugLine="l_title.Typeface=font_yekan";
mostCurrent._l_title.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 40;BA.debugLine="l_icon1.Typeface=font_icon";
mostCurrent._l_icon1.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="l_firstitle1.Typeface=font_yekan";
mostCurrent._l_firstitle1.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="l_secondtitle1.Typeface=font_yekan";
mostCurrent._l_secondtitle1.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 43;BA.debugLine="l_icon2.Typeface=font_icon";
mostCurrent._l_icon2.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 44;BA.debugLine="l_firsttitle2.Typeface=font_yekan";
mostCurrent._l_firsttitle2.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 45;BA.debugLine="l_secondtitle2.Typeface=font_yekan";
mostCurrent._l_secondtitle2.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 46;BA.debugLine="l_icon3.Typeface=font_icon";
mostCurrent._l_icon3.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 47;BA.debugLine="l_firsttitle3.Typeface=font_yekan";
mostCurrent._l_firsttitle3.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="l_secondtitle3.Typeface=font_yekan";
mostCurrent._l_secondtitle3.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 49;BA.debugLine="txtval.Typeface=font_yekan";
mostCurrent._txtval.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 50;BA.debugLine="btnverify.Typeface=font_yekan";
mostCurrent._btnverify.setTypeface((android.graphics.Typeface)(mostCurrent._font_yekan.getObject()));
 //BA.debugLineNum = 51;BA.debugLine="lblexit.Typeface=font_icon";
mostCurrent._lblexit.setTypeface((android.graphics.Typeface)(mostCurrent._font_icon.getObject()));
 //BA.debugLineNum = 52;BA.debugLine="txtval.Color = Colors.RGB(236, 235, 235)";
mostCurrent._txtval.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (236),(int) (235),(int) (235)));
 //BA.debugLineNum = 53;BA.debugLine="IME.Initialize(\"\")";
mostCurrent._ime.Initialize("");
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 144;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 145;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 146;BA.debugLine="If pnloverlay.Visible = True Then";
if (mostCurrent._pnloverlay.getVisible()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 147;BA.debugLine="checkChange";
_checkchange();
 };
 //BA.debugLineNum = 149;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 150;BA.debugLine="StartActivity(mainpage)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._mainpage.getObject()));
 //BA.debugLineNum = 151;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 152;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btnicon_click() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub btnicon_Click";
 //BA.debugLineNum = 65;BA.debugLine="StartActivity(icon)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._icon.getObject()));
 //BA.debugLineNum = 66;BA.debugLine="myLibrary.SetAnimation(\"file2\",\"file1\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file2","file1");
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public static String  _btnpass_click() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub btnpass_Click";
 //BA.debugLineNum = 70;BA.debugLine="blnSetting = False";
_blnsetting = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 71;BA.debugLine="txtval.Hint = \"رمز عبور\"";
mostCurrent._txtval.setHint("رمز عبور");
 //BA.debugLineNum = 73;BA.debugLine="If myLibrary.getSetting(\"pn\") <> \"\" Then";
if ((mostCurrent._mylibrary._getsetting(mostCurrent.activityBA,"pn")).equals("") == false) { 
 //BA.debugLineNum = 74;BA.debugLine="txtval.Text = myLibrary.getSetting(\"pn\")";
mostCurrent._txtval.setText((Object)(mostCurrent._mylibrary._getsetting(mostCurrent.activityBA,"pn")));
 //BA.debugLineNum = 75;BA.debugLine="changelogPass = txtval.Text";
mostCurrent._changelogpass = mostCurrent._txtval.getText();
 }else {
 //BA.debugLineNum = 77;BA.debugLine="txtval.Text = \"**98\"";
mostCurrent._txtval.setText((Object)("**98"));
 //BA.debugLineNum = 78;BA.debugLine="changelogPass = \"**98\"";
mostCurrent._changelogpass = "**98";
 };
 //BA.debugLineNum = 81;BA.debugLine="lblmsg.Text = \"با رمز بالا ميتوانيد وارد محيط برنامه شويد. در صورت تغيير رمز حتما آنرا به خاطر بسپاريد، در صورت فراموشي به هيچ عنوان نميتوانيد وارد برنامه شويد\"";
mostCurrent._lblmsg.setText((Object)("با رمز بالا ميتوانيد وارد محيط برنامه شويد. در صورت تغيير رمز حتما آنرا به خاطر بسپاريد، در صورت فراموشي به هيچ عنوان نميتوانيد وارد برنامه شويد"));
 //BA.debugLineNum = 82;BA.debugLine="pnloverlay.Visible = True";
mostCurrent._pnloverlay.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 83;BA.debugLine="txtval.RequestFocus";
mostCurrent._txtval.RequestFocus();
 //BA.debugLineNum = 84;BA.debugLine="txtval.SelectAll";
mostCurrent._txtval.SelectAll();
 //BA.debugLineNum = 85;BA.debugLine="IME.ShowKeyboard(txtval)";
mostCurrent._ime.ShowKeyboard((android.view.View)(mostCurrent._txtval.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="blnPass = True";
_blnpass = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _btntime_click() throws Exception{
 //BA.debugLineNum = 89;BA.debugLine="Sub btntime_Click";
 //BA.debugLineNum = 90;BA.debugLine="blnSetting = True";
_blnsetting = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 91;BA.debugLine="txtval.Hint = \"فاصله زمانی\"";
mostCurrent._txtval.setHint("فاصله زمانی");
 //BA.debugLineNum = 93;BA.debugLine="If myLibrary.getSetting(\"time\") <> \"\" Then";
if ((mostCurrent._mylibrary._getsetting(mostCurrent.activityBA,"time")).equals("") == false) { 
 //BA.debugLineNum = 94;BA.debugLine="txtval.Text = myLibrary.getSetting(\"time\")";
mostCurrent._txtval.setText((Object)(mostCurrent._mylibrary._getsetting(mostCurrent.activityBA,"time")));
 //BA.debugLineNum = 95;BA.debugLine="changelogTime = txtval.Text";
mostCurrent._changelogtime = mostCurrent._txtval.getText();
 }else {
 //BA.debugLineNum = 97;BA.debugLine="txtval.Text = 1";
mostCurrent._txtval.setText((Object)(1));
 //BA.debugLineNum = 98;BA.debugLine="changelogTime = 1";
mostCurrent._changelogtime = BA.NumberToString(1);
 };
 //BA.debugLineNum = 101;BA.debugLine="lblmsg.Text = \"زمانی را به عنوان فاصله بین عکس گرفتن وارد کنید. مثلا اگر 5 را بنویسید برنامه هر 5 دقیقه یکبار از فضول گوشیتون عکس میگیرد\"";
mostCurrent._lblmsg.setText((Object)("زمانی را به عنوان فاصله بین عکس گرفتن وارد کنید. مثلا اگر 5 را بنویسید برنامه هر 5 دقیقه یکبار از فضول گوشیتون عکس میگیرد"));
 //BA.debugLineNum = 102;BA.debugLine="pnloverlay.Visible = True";
mostCurrent._pnloverlay.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 103;BA.debugLine="txtval.RequestFocus";
mostCurrent._txtval.RequestFocus();
 //BA.debugLineNum = 104;BA.debugLine="txtval.SelectAll";
mostCurrent._txtval.SelectAll();
 //BA.debugLineNum = 105;BA.debugLine="IME.ShowKeyboard(txtval)";
mostCurrent._ime.ShowKeyboard((android.view.View)(mostCurrent._txtval.getObject()));
 //BA.debugLineNum = 106;BA.debugLine="blnPass = False";
_blnpass = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _btnverify_click() throws Exception{
 //BA.debugLineNum = 109;BA.debugLine="Sub btnverify_Click";
 //BA.debugLineNum = 110;BA.debugLine="If blnSetting = False Then";
if (_blnsetting==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 111;BA.debugLine="changePassword";
_changepassword();
 }else {
 //BA.debugLineNum = 113;BA.debugLine="changeTime";
_changetime();
 };
 //BA.debugLineNum = 115;BA.debugLine="pnloverlay.Visible = False";
mostCurrent._pnloverlay.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 116;BA.debugLine="txtval.Text = \"\"";
mostCurrent._txtval.setText((Object)(""));
 //BA.debugLineNum = 117;BA.debugLine="IME.HideKeyboard";
mostCurrent._ime.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _changepassword() throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="Sub changePassword";
 //BA.debugLineNum = 134;BA.debugLine="If txtval.Text.Length < 1 Then";
if (mostCurrent._txtval.getText().length()<1) { 
 //BA.debugLineNum = 135;BA.debugLine="ToastMessageShow(\"خطا: لطفا رمز عبور را وارد کنید\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("خطا: لطفا رمز عبور را وارد کنید",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 136;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 139;BA.debugLine="myLibrary.savePhoneNumber(txtval.Text)";
mostCurrent._mylibrary._savephonenumber(mostCurrent.activityBA,mostCurrent._txtval.getText());
 //BA.debugLineNum = 140;BA.debugLine="ToastMessageShow(\"رمز عبور اجرای برنامه با موفقیت تغییر داده شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("رمز عبور اجرای برنامه با موفقیت تغییر داده شد",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public static String  _changetime() throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub changeTime";
 //BA.debugLineNum = 122;BA.debugLine="If IsNumber(txtval.Text) = False Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtval.getText())==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 123;BA.debugLine="ToastMessageShow(\"خطا: لطفا فاصله زمانی را به دقیقه و درست وارد کنید\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("خطا: لطفا فاصله زمانی را به دقیقه و درست وارد کنید",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 124;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 127;BA.debugLine="myLibrary.saveTime(txtval.Text)";
mostCurrent._mylibrary._savetime(mostCurrent.activityBA,mostCurrent._txtval.getText());
 //BA.debugLineNum = 128;BA.debugLine="ToastMessageShow(\"فاصله زمانی تعین شده تغیر یافت\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("فاصله زمانی تعین شده تغیر یافت",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _checkchange() throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Sub checkChange";
 //BA.debugLineNum = 157;BA.debugLine="If blnPass = True Then";
if (_blnpass==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 158;BA.debugLine="If changelogPass <> txtval.Text Then";
if ((mostCurrent._changelogpass).equals(mostCurrent._txtval.getText()) == false) { 
 //BA.debugLineNum = 159;BA.debugLine="If Msgbox2(\"آیا رمز عبور جدید ذخیره شود؟\",\"توجه\",\"آری\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا رمز عبور جدید ذخیره شود؟","توجه","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 160;BA.debugLine="changePassword";
_changepassword();
 };
 };
 }else if(_blnpass==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 164;BA.debugLine="If changelogTime <> txtval.Text Then";
if ((mostCurrent._changelogtime).equals(mostCurrent._txtval.getText()) == false) { 
 //BA.debugLineNum = 165;BA.debugLine="If Msgbox2(\"آیا فاصله زمانی جدید ذخیره شود؟\",\"توجه\",\"آری\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا فاصله زمانی جدید ذخیره شود؟","توجه","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 166;BA.debugLine="changeTime";
_changetime();
 };
 };
 };
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 25;BA.debugLine="Private pnloverlay As Panel";
mostCurrent._pnloverlay = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Dim IME As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 27;BA.debugLine="Private lblmsg As Label";
mostCurrent._lblmsg = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private txtval As EditText";
mostCurrent._txtval = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim blnSetting As Boolean";
_blnsetting = false;
 //BA.debugLineNum = 30;BA.debugLine="Private btnverify As Button";
mostCurrent._btnverify = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private lblexit As Button";
mostCurrent._lblexit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private changelogTime,changelogPass As String";
mostCurrent._changelogtime = "";
mostCurrent._changelogpass = "";
 //BA.debugLineNum = 33;BA.debugLine="Dim blnPass As Boolean";
_blnpass = false;
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_down() throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Sub lblexit_Down";
 //BA.debugLineNum = 173;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 31, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (31),(int) (26)));
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_up() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub lblexit_Up";
 //BA.debugLineNum = 177;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 91, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (91),(int) (26)));
 //BA.debugLineNum = 178;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 179;BA.debugLine="StartActivity(mainpage)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._mainpage.getObject()));
 //BA.debugLineNum = 180;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 181;BA.debugLine="End Sub";
return "";
}
public static String  _pnloverlay_click() throws Exception{
 //BA.debugLineNum = 183;BA.debugLine="Sub pnloverlay_Click";
 //BA.debugLineNum = 184;BA.debugLine="pnloverlay.Visible = False";
mostCurrent._pnloverlay.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 185;BA.debugLine="IME.HideKeyboard";
mostCurrent._ime.HideKeyboard(mostCurrent.activityBA);
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
