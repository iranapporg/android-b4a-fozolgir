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

public class gallery extends Activity implements B4AActivity{
	public static gallery mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "quicklearn.collection.app", "quicklearn.collection.app.gallery");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (gallery).");
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
		activityBA = new BA(this, layout, processBA, "quicklearn.collection.app", "quicklearn.collection.app.gallery");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "quicklearn.collection.app.gallery", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (gallery) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (gallery) Resume **");
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
		return gallery.class;
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
        BA.LogInfo("** Activity (gallery) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (gallery) Resume **");
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
public anywheresoftware.b4a.objects.LabelWrapper _l_title = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _lblexit = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _currentpicture = null;
public static String _gallerypath = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btndelete = null;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
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
int _stop = 0;
 //BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"frmgallery\")";
mostCurrent._activity.LoadLayout("frmgallery",mostCurrent.activityBA);
 //BA.debugLineNum = 24;BA.debugLine="Dim sTop As Int : sTop = 0";
_stop = 0;
 //BA.debugLineNum = 24;BA.debugLine="Dim sTop As Int : sTop = 0";
_stop = (int) (0);
 //BA.debugLineNum = 25;BA.debugLine="l_title.Typeface = Typeface.LoadFromAssets(\"BYekan.ttf\")";
mostCurrent._l_title.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("BYekan.ttf"));
 //BA.debugLineNum = 26;BA.debugLine="btndelete.Typeface = Typeface.LoadFromAssets(\"BYekan.ttf\")";
mostCurrent._btndelete.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("BYekan.ttf"));
 //BA.debugLineNum = 27;BA.debugLine="lblexit.Typeface=Typeface.LoadFromAssets(\"icomoon.ttf\")";
mostCurrent._lblexit.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("icomoon.ttf"));
 //BA.debugLineNum = 28;BA.debugLine="loadImage";
_loadimage();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 96;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 97;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 98;BA.debugLine="StartActivity(mainpage)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._mainpage.getObject()));
 //BA.debugLineNum = 99;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 100;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 68;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 64;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 66;BA.debugLine="End Sub";
return "";
}
public static String  _btndelete_click() throws Exception{
anywheresoftware.b4a.objects.collections.List _l1 = null;
int _i = 0;
 //BA.debugLineNum = 115;BA.debugLine="Sub btndelete_Click";
 //BA.debugLineNum = 116;BA.debugLine="If Msgbox2(\"آیا مایل به حذف همه تصاویر هستید؟\",\"توجه\",\"آری\",\"خیر\",\"\",Null)	 = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا مایل به حذف همه تصاویر هستید؟","توجه","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 117;BA.debugLine="Dim l1 As List";
_l1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 118;BA.debugLine="l1.Initialize";
_l1.Initialize();
 //BA.debugLineNum = 119;BA.debugLine="l1 = File.ListFiles(File.DirInternal & \"/gallery\")";
_l1 = anywheresoftware.b4a.keywords.Common.File.ListFiles(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/gallery");
 //BA.debugLineNum = 120;BA.debugLine="For i = 0 To l1.Size - 1";
{
final int step97 = 1;
final int limit97 = (int) (_l1.getSize()-1);
for (_i = (int) (0); (step97 > 0 && _i <= limit97) || (step97 < 0 && _i >= limit97); _i = ((int)(0 + _i + step97))) {
 //BA.debugLineNum = 121;BA.debugLine="File.Delete(File.DirInternal & \"/gallery\",l1.Get(i))";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/gallery",BA.ObjectToString(_l1.Get(_i)));
 }
};
 //BA.debugLineNum = 123;BA.debugLine="loadImage";
_loadimage();
 //BA.debugLineNum = 124;BA.debugLine="ToastMessageShow(\"همه تصاویر گالری حذف شدند\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("همه تصاویر گالری حذف شدند",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _getfiles() throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub getFiles As List";
 //BA.debugLineNum = 32;BA.debugLine="Return File.ListFiles(galleryPath)";
if (true) return anywheresoftware.b4a.keywords.Common.File.ListFiles(mostCurrent._gallerypath);
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Private l_title As Label";
mostCurrent._l_title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lblexit As Button";
mostCurrent._lblexit = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private currentPicture As ImageView";
mostCurrent._currentpicture = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private galleryPath As String = File.DirInternal & \"/gallery/\"";
mostCurrent._gallerypath = anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/gallery/";
 //BA.debugLineNum = 18;BA.debugLine="Private btndelete As Button";
mostCurrent._btndelete = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _icon_click() throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _i1 = null;
com.rootsoft.rspopupmenu.RSPopupMenu _rs = null;
 //BA.debugLineNum = 72;BA.debugLine="Sub Icon_Click";
 //BA.debugLineNum = 73;BA.debugLine="Dim i1 As ImageView";
_i1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 74;BA.debugLine="i1 = Sender";
_i1.setObject((android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 75;BA.debugLine="currentPicture = i1";
mostCurrent._currentpicture = _i1;
 //BA.debugLineNum = 77;BA.debugLine="Dim rs As RSPopupMenu";
_rs = new com.rootsoft.rspopupmenu.RSPopupMenu();
 //BA.debugLineNum = 78;BA.debugLine="rs.Initialize(\"rs\",i1)";
_rs.Initialize(mostCurrent.activityBA,"rs",(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_i1.getObject())));
 //BA.debugLineNum = 79;BA.debugLine="rs.AddMenuItem(0,1,\"مشاهده\")";
_rs.AddMenuItem((int) (0),(int) (1),"مشاهده");
 //BA.debugLineNum = 80;BA.debugLine="rs.AddMenuItem(1,2,\"حذف\")";
_rs.AddMenuItem((int) (1),(int) (2),"حذف");
 //BA.debugLineNum = 81;BA.debugLine="rs.Show";
_rs.Show();
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_down() throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub lblexit_Down";
 //BA.debugLineNum = 105;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 31, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (31),(int) (26)));
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _lblexit_up() throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub lblexit_Up";
 //BA.debugLineNum = 109;BA.debugLine="lblexit.TextColor = Colors.RGB(227, 91, 26)";
mostCurrent._lblexit.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (91),(int) (26)));
 //BA.debugLineNum = 110;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 111;BA.debugLine="StartActivity(mainpage)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._mainpage.getObject()));
 //BA.debugLineNum = 112;BA.debugLine="myLibrary.SetAnimation(\"file3\",\"file4\")";
mostCurrent._mylibrary._setanimation(mostCurrent.activityBA,"file3","file4");
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _loadimage() throws Exception{
int _divid = 0;
int _stop = 0;
int _sleft = 0;
anywheresoftware.b4a.objects.collections.List _files = null;
anywheresoftware.b4a.object.RippleViewWrapper _rip = null;
int _i = 0;
anywheresoftware.b4a.objects.ImageViewWrapper _image1 = null;
 //BA.debugLineNum = 35;BA.debugLine="Sub loadImage";
 //BA.debugLineNum = 36;BA.debugLine="Dim divid      As Int = 45%x";
_divid = anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (45),mostCurrent.activityBA);
 //BA.debugLineNum = 37;BA.debugLine="Dim sTop       As Int : sTop       = 6dip";
_stop = 0;
 //BA.debugLineNum = 37;BA.debugLine="Dim sTop       As Int : sTop       = 6dip";
_stop = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6));
 //BA.debugLineNum = 38;BA.debugLine="Dim sLeft      As Int : sLeft      = 13dip";
_sleft = 0;
 //BA.debugLineNum = 38;BA.debugLine="Dim sLeft      As Int : sLeft      = 13dip";
_sleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13));
 //BA.debugLineNum = 39;BA.debugLine="Dim files      As List";
_files = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 40;BA.debugLine="Dim rip        As RippleView";
_rip = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="ScrollView1.Panel.RemoveAllViews";
mostCurrent._scrollview1.getPanel().RemoveAllViews();
 //BA.debugLineNum = 43;BA.debugLine="files = getFiles";
_files = _getfiles();
 //BA.debugLineNum = 44;BA.debugLine="For i = 0 To files.Size - 1";
{
final int step32 = 1;
final int limit32 = (int) (_files.getSize()-1);
for (_i = (int) (0); (step32 > 0 && _i <= limit32) || (step32 < 0 && _i >= limit32); _i = ((int)(0 + _i + step32))) {
 //BA.debugLineNum = 45;BA.debugLine="Dim image1 As ImageView";
_image1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="image1.Initialize(\"Icon\")";
_image1.Initialize(mostCurrent.activityBA,"Icon");
 //BA.debugLineNum = 47;BA.debugLine="ScrollView1.Panel.AddView(image1,sLeft,sTop,divid,divid)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(_image1.getObject()),_sleft,_stop,_divid,_divid);
 //BA.debugLineNum = 48;BA.debugLine="rip.Initialize(image1,Colors.RGB(100,221,23),430,True)";
_rip.Initialize(mostCurrent.activityBA,(android.view.View)(_image1.getObject()),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (100),(int) (221),(int) (23)),(int) (430),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 49;BA.debugLine="image1.Gravity = Gravity.FILL";
_image1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 50;BA.debugLine="image1.Tag =  files.Get(i)";
_image1.setTag(_files.Get(_i));
 //BA.debugLineNum = 51;BA.debugLine="image1.SetBackgroundImage(LoadBitmap(galleryPath, files.Get(i)))";
_image1.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(mostCurrent._gallerypath,BA.ObjectToString(_files.Get(_i))).getObject()));
 //BA.debugLineNum = 52;BA.debugLine="myLibrary.AnimationView(image1)";
mostCurrent._mylibrary._animationview(mostCurrent.activityBA,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_image1.getObject())));
 //BA.debugLineNum = 53;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 54;BA.debugLine="If (i+1) Mod 2 = 0 Then";
if ((_i+1)%2==0) { 
 //BA.debugLineNum = 55;BA.debugLine="sLeft = 13dip";
_sleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13));
 //BA.debugLineNum = 56;BA.debugLine="sTop = sTop + divid + 10dip";
_stop = (int) (_stop+_divid+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 57;BA.debugLine="ScrollView1.Panel.Height = ScrollView1.Panel.Height + image1.Height + 10dip";
mostCurrent._scrollview1.getPanel().setHeight((int) (mostCurrent._scrollview1.getPanel().getHeight()+_image1.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 }else {
 //BA.debugLineNum = 59;BA.debugLine="sLeft = sLeft + divid + 8dip";
_sleft = (int) (_sleft+_divid+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (8)));
 };
 }
};
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static boolean  _rs_menuitemclick(int _itemid) throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Sub rs_MenuItemClick (ItemId As Int) As Boolean";
 //BA.debugLineNum = 85;BA.debugLine="If (ItemId = 0) Then";
if ((_itemid==0)) { 
 //BA.debugLineNum = 86;BA.debugLine="File.Copy(galleryPath,currentPicture.Tag,File.DirDefaultExternal,currentPicture.Tag)";
anywheresoftware.b4a.keywords.Common.File.Copy(mostCurrent._gallerypath,BA.ObjectToString(mostCurrent._currentpicture.getTag()),anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),BA.ObjectToString(mostCurrent._currentpicture.getTag()));
 //BA.debugLineNum = 87;BA.debugLine="myLibrary.OpenGallery(currentPicture.Tag)";
mostCurrent._mylibrary._opengallery(mostCurrent.activityBA,BA.ObjectToString(mostCurrent._currentpicture.getTag()));
 }else if((_itemid==1)) { 
 //BA.debugLineNum = 89;BA.debugLine="File.Delete(galleryPath,currentPicture.Tag)";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._gallerypath,BA.ObjectToString(mostCurrent._currentpicture.getTag()));
 //BA.debugLineNum = 90;BA.debugLine="currentPicture.SetBackgroundImage(LoadBitmap(File.DirAssets,\"no_image.jpg\"))";
mostCurrent._currentpicture.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.jpg").getObject()));
 };
 //BA.debugLineNum = 92;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return false;
}
}
