package quicklearn.collection.app;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mylibrary {
private static mylibrary mostCurrent = new mylibrary();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager _presetting = null;
public static boolean _blnrun = false;
public quicklearn.collection.app.main _main = null;
public quicklearn.collection.app.mainpage _mainpage = null;
public quicklearn.collection.app.settings _settings = null;
public quicklearn.collection.app.icon _icon = null;
public quicklearn.collection.app.gallery _gallery = null;
public quicklearn.collection.app.cameras _cameras = null;
public quicklearn.collection.app.help _help = null;
public quicklearn.collection.app.handlecall _handlecall = null;
public quicklearn.collection.app.widget _widget = null;
public quicklearn.collection.app.cycle _cycle = null;
public static String  _animationview(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _view1) throws Exception{
flm.b4a.animationplus.AnimationPlusWrapper _ani = null;
 //BA.debugLineNum = 184;BA.debugLine="Sub AnimationView(View1 As View)";
 //BA.debugLineNum = 185;BA.debugLine="Dim ani As AnimationPlus";
_ani = new flm.b4a.animationplus.AnimationPlusWrapper();
 //BA.debugLineNum = 186;BA.debugLine="ani.InitializeAlpha(\"\",0,1.0)";
_ani.InitializeAlpha(_ba,"",(float) (0),(float) (1.0));
 //BA.debugLineNum = 187;BA.debugLine="ani.Duration = 700";
_ani.setDuration((long) (700));
 //BA.debugLineNum = 188;BA.debugLine="ani.PersistAfter = True";
_ani.setPersistAfter(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 189;BA.debugLine="ani.Start(View1)";
_ani.Start((android.view.View)(_view1.getObject()));
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _convertnumbers2persian(anywheresoftware.b4a.BA _ba,String _snumber) throws Exception{
String[] _snumbers = null;
String _res = "";
int _j = 0;
int _i = 0;
 //BA.debugLineNum = 123;BA.debugLine="Sub ConvertNumbers2Persian(sNumber As String) As String";
 //BA.debugLineNum = 124;BA.debugLine="Dim sNumbers(10) As String";
_snumbers = new String[(int) (10)];
java.util.Arrays.fill(_snumbers,"");
 //BA.debugLineNum = 125;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 126;BA.debugLine="Dim j As Int";
_j = 0;
 //BA.debugLineNum = 128;BA.debugLine="res = sNumber";
_res = _snumber;
 //BA.debugLineNum = 129;BA.debugLine="sNumbers(0) = \"٠\"";
_snumbers[(int) (0)] = "٠";
 //BA.debugLineNum = 130;BA.debugLine="sNumbers(1) = \"١\"";
_snumbers[(int) (1)] = "١";
 //BA.debugLineNum = 131;BA.debugLine="sNumbers(2) = \"٢\"";
_snumbers[(int) (2)] = "٢";
 //BA.debugLineNum = 132;BA.debugLine="sNumbers(3) = \"٣\"";
_snumbers[(int) (3)] = "٣";
 //BA.debugLineNum = 133;BA.debugLine="sNumbers(4) = \"٤\"";
_snumbers[(int) (4)] = "٤";
 //BA.debugLineNum = 134;BA.debugLine="sNumbers(5) = \"٥\"";
_snumbers[(int) (5)] = "٥";
 //BA.debugLineNum = 135;BA.debugLine="sNumbers(6) = \"٦\"";
_snumbers[(int) (6)] = "٦";
 //BA.debugLineNum = 136;BA.debugLine="sNumbers(7) = \"٧\"";
_snumbers[(int) (7)] = "٧";
 //BA.debugLineNum = 137;BA.debugLine="sNumbers(8) = \"٨\"";
_snumbers[(int) (8)] = "٨";
 //BA.debugLineNum = 138;BA.debugLine="sNumbers(9) = \"٩\"";
_snumbers[(int) (9)] = "٩";
 //BA.debugLineNum = 140;BA.debugLine="For i =0 To sNumber.Length - 1";
{
final int step123 = 1;
final int limit123 = (int) (_snumber.length()-1);
for (_i = (int) (0); (step123 > 0 && _i <= limit123) || (step123 < 0 && _i >= limit123); _i = ((int)(0 + _i + step123))) {
 //BA.debugLineNum = 141;BA.debugLine="j = sNumber.SubString2(i,i+1)";
_j = (int)(Double.parseDouble(_snumber.substring(_i,(int) (_i+1))));
 //BA.debugLineNum = 142;BA.debugLine="res = res.Replace(sNumber.CharAt(i),sNumbers(j))";
_res = _res.replace(BA.ObjectToString(_snumber.charAt(_i)),_snumbers[_j]);
 }
};
 //BA.debugLineNum = 145;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _fontfamily(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _label1,String _fontname) throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub FontFamily(Label1 As Label,FontName As String)";
 //BA.debugLineNum = 7;BA.debugLine="If FontName = \"\" Then";
if ((_fontname).equals("")) { 
 //BA.debugLineNum = 8;BA.debugLine="Label1.Typeface = Typeface.LoadFromAssets(\"byekan.ttf\")";
_label1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("byekan.ttf"));
 }else {
 //BA.debugLineNum = 10;BA.debugLine="Label1.Typeface = Typeface.LoadFromAssets(FontName)";
_label1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets(_fontname));
 };
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _getcurrentrunningapp(anywheresoftware.b4a.BA _ba) throws Exception{
com.rootsoft.oslibrary.OSLibrary _os = null;
anywheresoftware.b4a.objects.collections.List _runlist = null;
String _xt = "";
anywheresoftware.b4a.agraham.reflection.Reflection _r1 = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub getCurrentRunningApp As String";
 //BA.debugLineNum = 21;BA.debugLine="Dim os As OperatingSystem";
_os = new com.rootsoft.oslibrary.OSLibrary();
 //BA.debugLineNum = 22;BA.debugLine="os.Initialize(\"\")";
_os.Initialize((_ba.processBA == null ? _ba : _ba.processBA),"");
 //BA.debugLineNum = 23;BA.debugLine="Private RunList As List = os.getRunningTasks(4)";
_runlist = new anywheresoftware.b4a.objects.collections.List();
_runlist.setObject((java.util.List)(_os.getRunningTasks((int) (4))));
 //BA.debugLineNum = 24;BA.debugLine="Private xt As String = \"\", r1 As Reflector";
_xt = "";
_r1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 25;BA.debugLine="If RunList.Size > 0 Then";
if (_runlist.getSize()>0) { 
 //BA.debugLineNum = 26;BA.debugLine="r1.Target = RunList.get(1)";
_r1.Target = _runlist.Get((int) (1));
 //BA.debugLineNum = 27;BA.debugLine="xt = r1.getField(\"topActivity\")";
_xt = BA.ObjectToString(_r1.GetField("topActivity"));
 //BA.debugLineNum = 28;BA.debugLine="xt = xt.ToLowerCase";
_xt = _xt.toLowerCase();
 //BA.debugLineNum = 29;BA.debugLine="xt = xt.Replace(\"componentinfo{\",\"\")";
_xt = _xt.replace("componentinfo{","");
 //BA.debugLineNum = 30;BA.debugLine="xt = xt.SubString2(0,xt.IndexOf(\"/\"))";
_xt = _xt.substring((int) (0),_xt.indexOf("/"));
 //BA.debugLineNum = 31;BA.debugLine="Return xt";
if (true) return _xt;
 };
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _getdate(anywheresoftware.b4a.BA _ba,boolean _sadvance) throws Exception{
anywheresoftware.b4a.student.PersianDate _d1 = null;
String[] _s1 = null;
String _month = "";
 //BA.debugLineNum = 85;BA.debugLine="Sub getDate(sAdvance As Boolean) As String";
 //BA.debugLineNum = 86;BA.debugLine="Dim d1 As PersianDate";
_d1 = new anywheresoftware.b4a.student.PersianDate();
 //BA.debugLineNum = 87;BA.debugLine="Dim s1(),month As String";
_s1 = new String[(int) (0)];
java.util.Arrays.fill(_s1,"");
_month = "";
 //BA.debugLineNum = 89;BA.debugLine="If sAdvance = False Then";
if (_sadvance==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 90;BA.debugLine="Return d1.getDate(0,0,0,\"/\")";
if (true) return _d1.getDate((int) (0),(int) (0),(int) (0),"/");
 }else {
 //BA.debugLineNum = 92;BA.debugLine="s1 = Regex.Split(\"/\",d1.getDate(0,0,0,\"/\"))";
_s1 = anywheresoftware.b4a.keywords.Common.Regex.Split("/",_d1.getDate((int) (0),(int) (0),(int) (0),"/"));
 //BA.debugLineNum = 93;BA.debugLine="Select Case s1(1)";
switch (BA.switchObjectToInt(_s1[(int) (1)],BA.NumberToString(1),BA.NumberToString(2),BA.NumberToString(3),BA.NumberToString(4),BA.NumberToString(5),BA.NumberToString(6),BA.NumberToString(7),BA.NumberToString(8),BA.NumberToString(9),BA.NumberToString(10),BA.NumberToString(11),BA.NumberToString(12))) {
case 0:
 //BA.debugLineNum = 95;BA.debugLine="month = \"فروردین\"";
_month = "فروردین";
 break;
case 1:
 //BA.debugLineNum = 97;BA.debugLine="month = \"اردیبهشت\"";
_month = "اردیبهشت";
 break;
case 2:
 //BA.debugLineNum = 99;BA.debugLine="month = \"خرداد\"";
_month = "خرداد";
 break;
case 3:
 //BA.debugLineNum = 101;BA.debugLine="month = \"تیر\"";
_month = "تیر";
 break;
case 4:
 //BA.debugLineNum = 103;BA.debugLine="month = \"مرداد\"";
_month = "مرداد";
 break;
case 5:
 //BA.debugLineNum = 105;BA.debugLine="month = \"شهریور\"";
_month = "شهریور";
 break;
case 6:
 //BA.debugLineNum = 107;BA.debugLine="month = \"مهر\"";
_month = "مهر";
 break;
case 7:
 //BA.debugLineNum = 109;BA.debugLine="month = \"آبان\"";
_month = "آبان";
 break;
case 8:
 //BA.debugLineNum = 111;BA.debugLine="month = \"آذر\"";
_month = "آذر";
 break;
case 9:
 //BA.debugLineNum = 113;BA.debugLine="month = \"دی\"";
_month = "دی";
 break;
case 10:
 //BA.debugLineNum = 115;BA.debugLine="month = \"بهمن\"";
_month = "بهمن";
 break;
case 11:
 //BA.debugLineNum = 117;BA.debugLine="month = \"اسفند\"";
_month = "اسفند";
 break;
}
;
 //BA.debugLineNum = 119;BA.debugLine="Return ConvertNumbers2Persian(s1(2))  & \" \" & month";
if (true) return _convertnumbers2persian(_ba,_s1[(int) (2)])+" "+_month;
 };
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _getpackageinf(anywheresoftware.b4a.BA _ba,String _spackage) throws Exception{
anywheresoftware.b4a.phone.PackageManagerWrapper _p1 = null;
anywheresoftware.b4a.objects.collections.List _list1 = null;
String _pk = "";
anywheresoftware.b4a.objects.collections.Map _m1 = null;
int _k = 0;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
 //BA.debugLineNum = 35;BA.debugLine="Sub getPackageInf(sPackage As String) As Map";
 //BA.debugLineNum = 36;BA.debugLine="Dim p1 As PackageManager";
_p1 = new anywheresoftware.b4a.phone.PackageManagerWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim list1 As List";
_list1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 38;BA.debugLine="Dim pk As String";
_pk = "";
 //BA.debugLineNum = 39;BA.debugLine="Dim m1 As Map";
_m1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 41;BA.debugLine="list1.Initialize";
_list1.Initialize();
 //BA.debugLineNum = 42;BA.debugLine="m1.Initialize";
_m1.Initialize();
 //BA.debugLineNum = 43;BA.debugLine="list1 = p1.GetInstalledPackages";
_list1 = _p1.GetInstalledPackages();
 //BA.debugLineNum = 45;BA.debugLine="For k = 0 To list1.Size - 1";
{
final int step38 = 1;
final int limit38 = (int) (_list1.getSize()-1);
for (_k = (int) (0); (step38 > 0 && _k <= limit38) || (step38 < 0 && _k >= limit38); _k = ((int)(0 + _k + step38))) {
 //BA.debugLineNum = 46;BA.debugLine="pk = list1.get(k)";
_pk = BA.ObjectToString(_list1.Get(_k));
 //BA.debugLineNum = 47;BA.debugLine="If sPackage.ToLowerCase = pk.ToLowerCase Then";
if ((_spackage.toLowerCase()).equals(_pk.toLowerCase())) { 
 //BA.debugLineNum = 48;BA.debugLine="m1.Put(\"time\",getDate(True) & \" ساعت \" & DateTime.GetHour(DateTime.Now) & \":\" & DateTime.GetMinute(DateTime.Now))";
_m1.Put((Object)("time"),(Object)(_getdate(_ba,anywheresoftware.b4a.keywords.Common.True)+" ساعت "+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetHour(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))+":"+BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.GetMinute(anywheresoftware.b4a.keywords.Common.DateTime.getNow()))));
 //BA.debugLineNum = 49;BA.debugLine="m1.Put(\"label\",p1.GetApplicationLabel(pk))";
_m1.Put((Object)("label"),(Object)(_p1.GetApplicationLabel(_pk)));
 //BA.debugLineNum = 50;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 51;BA.debugLine="bd = p1.GetApplicationIcon(pk)";
_bd.setObject((android.graphics.drawable.BitmapDrawable)(_p1.GetApplicationIcon(_pk)));
 //BA.debugLineNum = 52;BA.debugLine="m1.Put(\"icon\",bd)";
_m1.Put((Object)("icon"),(Object)(_bd.getObject()));
 //BA.debugLineNum = 53;BA.debugLine="Return m1";
if (true) return _m1;
 };
 }
};
 //BA.debugLineNum = 56;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.ConcreteViewWrapper  _getparent(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _v) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 14;BA.debugLine="Sub GetParent(v As View) As View";
 //BA.debugLineNum = 15;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 16;BA.debugLine="r.Target = v";
_r.Target = (Object)(_v.getObject());
 //BA.debugLineNum = 17;BA.debugLine="Return r.RunMethod(\"getParent\")";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_r.RunMethod("getParent")));
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return null;
}
public static String  _getsetting(anywheresoftware.b4a.BA _ba,String _key) throws Exception{
 //BA.debugLineNum = 204;BA.debugLine="Sub getSetting(key As String)";
 //BA.debugLineNum = 205;BA.debugLine="Return preSetting.GetString(key)";
if (true) return _presetting.GetString(_key);
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _gridpanelicon(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ScrollViewWrapper _panel1) throws Exception{
int _gridscreen = 0;
int _diff = 0;
int _stop = 0;
int _sleft = 0;
anywheresoftware.b4a.object.RippleViewWrapper _rip = null;
int _i = 0;
anywheresoftware.b4a.objects.ImageViewWrapper _image1 = null;
 //BA.debugLineNum = 149;BA.debugLine="Sub GridPanelIcon(Panel1 As ScrollView)";
 //BA.debugLineNum = 150;BA.debugLine="Dim gridScreen As Int : gridScreen = Round(100%x / 85dip)";
_gridscreen = 0;
 //BA.debugLineNum = 150;BA.debugLine="Dim gridScreen As Int : gridScreen = Round(100%x / 85dip)";
_gridscreen = (int) (anywheresoftware.b4a.keywords.Common.Round(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),_ba)/(double)anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (85))));
 //BA.debugLineNum = 151;BA.debugLine="Dim Diff       As Int : Diff       = ((100%x - gridScreen * 85dip) / gridScreen) - 6";
_diff = 0;
 //BA.debugLineNum = 151;BA.debugLine="Dim Diff       As Int : Diff       = ((100%x - gridScreen * 85dip) / gridScreen) - 6";
_diff = (int) (((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),_ba)-_gridscreen*anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (85)))/(double)_gridscreen)-6);
 //BA.debugLineNum = 152;BA.debugLine="Dim sTop       As Int : sTop       = 6dip";
_stop = 0;
 //BA.debugLineNum = 152;BA.debugLine="Dim sTop       As Int : sTop       = 6dip";
_stop = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6));
 //BA.debugLineNum = 153;BA.debugLine="Dim sLeft      As Int : sLeft      = 10dip";
_sleft = 0;
 //BA.debugLineNum = 153;BA.debugLine="Dim sLeft      As Int : sLeft      = 10dip";
_sleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 154;BA.debugLine="Dim rip As RippleView";
_rip = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 156;BA.debugLine="For i = 1 To 20";
{
final int step139 = 1;
final int limit139 = (int) (20);
for (_i = (int) (1); (step139 > 0 && _i <= limit139) || (step139 < 0 && _i >= limit139); _i = ((int)(0 + _i + step139))) {
 //BA.debugLineNum = 157;BA.debugLine="Dim image1 As ImageView";
_image1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 158;BA.debugLine="image1.Initialize(\"Icon\")";
_image1.Initialize(_ba,"Icon");
 //BA.debugLineNum = 159;BA.debugLine="Panel1.Panel.AddView(image1,sLeft,sTop,60dip,60dip)";
_panel1.getPanel().AddView((android.view.View)(_image1.getObject()),_sleft,_stop,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)));
 //BA.debugLineNum = 160;BA.debugLine="image1.Gravity = Gravity.FILL";
_image1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 161;BA.debugLine="image1.Tag = i & \".png\"";
_image1.setTag((Object)(BA.NumberToString(_i)+".png"));
 //BA.debugLineNum = 162;BA.debugLine="image1.SetBackgroundImage(LoadBitmap(File.DirAssets,i & \".png\"))";
_image1.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),BA.NumberToString(_i)+".png").getObject()));
 //BA.debugLineNum = 163;BA.debugLine="AnimationView(image1)";
_animationview(_ba,(anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_image1.getObject())));
 //BA.debugLineNum = 164;BA.debugLine="rip.Initialize(image1,Colors.RGB(227, 91, 26),350,True)";
_rip.Initialize(_ba,(android.view.View)(_image1.getObject()),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (227),(int) (91),(int) (26)),(int) (350),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 165;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 166;BA.debugLine="If i Mod gridScreen = 0 Then";
if (_i%_gridscreen==0) { 
 //BA.debugLineNum = 167;BA.debugLine="sLeft = 10dip";
_sleft = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 168;BA.debugLine="sTop = sTop + 86dip";
_stop = (int) (_stop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (86)));
 //BA.debugLineNum = 169;BA.debugLine="Panel1.Panel.Height = Panel1.Panel.Height + 130dip";
_panel1.getPanel().setHeight((int) (_panel1.getPanel().getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (130))));
 }else {
 //BA.debugLineNum = 171;BA.debugLine="sLeft = sLeft + 85dip + Diff";
_sleft = (int) (_sleft+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (85))+_diff);
 };
 }
};
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _opengallery(anywheresoftware.b4a.BA _ba,String _filename) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _iintent = null;
String _image = "";
 //BA.debugLineNum = 219;BA.debugLine="Sub OpenGallery(filename As String)";
 //BA.debugLineNum = 220;BA.debugLine="Dim iIntent As Intent";
_iintent = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 221;BA.debugLine="Dim Image As String";
_image = "";
 //BA.debugLineNum = 223;BA.debugLine="Image = File.Combine(File.DirDefaultExternal, filename)";
_image = anywheresoftware.b4a.keywords.Common.File.Combine(anywheresoftware.b4a.keywords.Common.File.getDirDefaultExternal(),_filename);
 //BA.debugLineNum = 225;BA.debugLine="iIntent.Initialize(iIntent.ACTION_VIEW, \"file:///\" & Image)";
_iintent.Initialize(_iintent.ACTION_VIEW,"file:///"+_image);
 //BA.debugLineNum = 227;BA.debugLine="iIntent.SetType(\"image/jpeg\")";
_iintent.SetType("image/jpeg");
 //BA.debugLineNum = 229;BA.debugLine="StartActivity(iIntent)";
anywheresoftware.b4a.keywords.Common.StartActivity(_ba,(Object)(_iintent.getObject()));
 //BA.debugLineNum = 230;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim preSetting As PreferenceManager";
_presetting = new anywheresoftware.b4a.objects.preferenceactivity.PreferenceManager();
 //BA.debugLineNum = 3;BA.debugLine="Dim blnRun As Boolean";
_blnrun = false;
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public static String  _saveicon(anywheresoftware.b4a.BA _ba,String _iconname) throws Exception{
 //BA.debugLineNum = 192;BA.debugLine="Sub saveIcon(IconName As String)";
 //BA.debugLineNum = 193;BA.debugLine="preSetting.SetString(\"widgetIcon\",IconName)";
_presetting.SetString("widgetIcon",_iconname);
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static String  _savephonenumber(anywheresoftware.b4a.BA _ba,String _phonenumber) throws Exception{
 //BA.debugLineNum = 196;BA.debugLine="Sub savePhoneNumber(PhoneNumber As String)";
 //BA.debugLineNum = 197;BA.debugLine="preSetting.SetString(\"pn\",PhoneNumber)";
_presetting.SetString("pn",_phonenumber);
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public static String  _savetime(anywheresoftware.b4a.BA _ba,String _time) throws Exception{
 //BA.debugLineNum = 200;BA.debugLine="Sub saveTime(time As String)";
 //BA.debugLineNum = 201;BA.debugLine="preSetting.SetString(\"time\",time)";
_presetting.SetString("time",_time);
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return "";
}
public static String  _setanimation(anywheresoftware.b4a.BA _ba,String _inanimation,String _outanimation) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
String _package = "";
int _in = 0;
int _out = 0;
 //BA.debugLineNum = 208;BA.debugLine="Sub SetAnimation(InAnimation As String, OutAnimation As String)";
 //BA.debugLineNum = 209;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 210;BA.debugLine="Dim package As String";
_package = "";
 //BA.debugLineNum = 211;BA.debugLine="Dim In, out As Int";
_in = 0;
_out = 0;
 //BA.debugLineNum = 212;BA.debugLine="package = r.GetStaticField(\"anywheresoftware.b4a.BA\", \"packageName\")";
_package = BA.ObjectToString(_r.GetStaticField("anywheresoftware.b4a.BA","packageName"));
 //BA.debugLineNum = 213;BA.debugLine="In = r.GetStaticField(package & \".R$anim\", InAnimation)";
_in = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_inanimation)));
 //BA.debugLineNum = 214;BA.debugLine="out = r.GetStaticField(package & \".R$anim\", OutAnimation)";
_out = (int)(BA.ObjectToNumber(_r.GetStaticField(_package+".R$anim",_outanimation)));
 //BA.debugLineNum = 215;BA.debugLine="r.Target = r.GetActivity";
_r.Target = (Object)(_r.GetActivity((_ba.processBA == null ? _ba : _ba.processBA)));
 //BA.debugLineNum = 216;BA.debugLine="r.RunMethod4(\"overridePendingTransition\", Array As Object(In, out), Array As String(\"java.lang.int\", \"java.lang.int\"))";
_r.RunMethod4("overridePendingTransition",new Object[]{(Object)(_in),(Object)(_out)},new String[]{"java.lang.int","java.lang.int"});
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _settextshadow(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ConcreteViewWrapper _pview,float _pradius,float _pdx,float _pdy,int _pcolor) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _ref = null;
 //BA.debugLineNum = 177;BA.debugLine="Sub SetTextShadow(pView As View, pRadius As Float, pDx As Float, pDy As Float, pColor As Int)";
 //BA.debugLineNum = 178;BA.debugLine="Dim ref As Reflector";
_ref = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 180;BA.debugLine="ref.Target = pView";
_ref.Target = (Object)(_pview.getObject());
 //BA.debugLineNum = 181;BA.debugLine="ref.RunMethod4(\"setShadowLayer\", Array As Object(pRadius, pDx, pDy, pColor), Array As String(\"java.lang.float\", \"java.lang.float\", \"java.lang.float\", \"java.lang.int\"))";
_ref.RunMethod4("setShadowLayer",new Object[]{(Object)(_pradius),(Object)(_pdx),(Object)(_pdy),(Object)(_pcolor)},new String[]{"java.lang.float","java.lang.float","java.lang.float","java.lang.int"});
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String[]  _splithelp(anywheresoftware.b4a.BA _ba) throws Exception{
String[] _k1 = null;
 //BA.debugLineNum = 232;BA.debugLine="Sub splitHelp As String()";
 //BA.debugLineNum = 233;BA.debugLine="Dim k1() As String";
_k1 = new String[(int) (0)];
java.util.Arrays.fill(_k1,"");
 //BA.debugLineNum = 234;BA.debugLine="k1 = Regex.Split(\"NLine\",File.ReadString(File.DirAssets,\"guide.txt\"))";
_k1 = anywheresoftware.b4a.keywords.Common.Regex.Split("NLine",anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"guide.txt"));
 //BA.debugLineNum = 235;BA.debugLine="Return k1";
if (true) return _k1;
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return null;
}
public static String  _watermarkpicture(anywheresoftware.b4a.BA _ba,String _filename,anywheresoftware.b4a.objects.PanelWrapper _pnl) throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _c1 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _dest = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _bar = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _ou = null;
 //BA.debugLineNum = 59;BA.debugLine="Sub WatermarkPicture(filename As String,pnl As Panel)";
 //BA.debugLineNum = 60;BA.debugLine="Dim map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 61;BA.debugLine="map1.Initialize";
_map1.Initialize();
 //BA.debugLineNum = 62;BA.debugLine="map1 = getPackageInf(getCurrentRunningApp)";
_map1 = _getpackageinf(_ba,_getcurrentrunningapp(_ba));
 //BA.debugLineNum = 64;BA.debugLine="Dim c1 As Canvas";
_c1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 65;BA.debugLine="Dim dest,bar As Rect";
_dest = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
_bar = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 66;BA.debugLine="dest.Initialize(5dip,pnl.Height - 40dip,44dip,pnl.Height-7dip)";
_dest.Initialize(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (_pnl.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)),(int) (_pnl.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7))));
 //BA.debugLineNum = 67;BA.debugLine="bar.Initialize(0,pnl.Height - 50dip,pnl.Width,pnl.Height)";
_bar.Initialize((int) (0),(int) (_pnl.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))),_pnl.getWidth(),_pnl.getHeight());
 //BA.debugLineNum = 69;BA.debugLine="pnl.SetBackgroundImage(LoadBitmap(File.DirInternal & \"/gallery\",filename))";
_pnl.SetBackgroundImage((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/gallery",_filename).getObject()));
 //BA.debugLineNum = 71;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 72;BA.debugLine="c1.Initialize(pnl)";
_c1.Initialize((android.view.View)(_pnl.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="bd = map1.Get(\"icon\")";
_bd.setObject((android.graphics.drawable.BitmapDrawable)(_map1.Get((Object)("icon"))));
 //BA.debugLineNum = 74;BA.debugLine="c1.DrawRect(bar,Colors.ARGB(100,60,17,92),True,2)";
_c1.DrawRect((android.graphics.Rect)(_bar.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (100),(int) (60),(int) (17),(int) (92)),anywheresoftware.b4a.keywords.Common.True,(float) (2));
 //BA.debugLineNum = 75;BA.debugLine="c1.DrawBitmap(bd.Bitmap,Null,dest)";
_c1.DrawBitmap(_bd.getBitmap(),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_dest.getObject()));
 //BA.debugLineNum = 76;BA.debugLine="c1.DrawText(map1.Get(\"label\"),51dip,pnl.Height - 25,Typeface.LoadFromAssets(\"byekan.ttf\"),13,Colors.White,\"LEFT\")";
_c1.DrawText(_ba,BA.ObjectToString(_map1.Get((Object)("label"))),(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (51))),(float) (_pnl.getHeight()-25),anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("byekan.ttf"),(float) (13),anywheresoftware.b4a.keywords.Common.Colors.White,BA.getEnumFromString(android.graphics.Paint.Align.class,"LEFT"));
 //BA.debugLineNum = 77;BA.debugLine="c1.DrawText(map1.Get(\"time\"),80%x,pnl.Height - 30 ,Typeface.LoadFromAssets(\"byekan.ttf\"),15,Colors.White,\"RIGHT\")";
_c1.DrawText(_ba,BA.ObjectToString(_map1.Get((Object)("time"))),(float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (80),_ba)),(float) (_pnl.getHeight()-30),anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("byekan.ttf"),(float) (15),anywheresoftware.b4a.keywords.Common.Colors.White,BA.getEnumFromString(android.graphics.Paint.Align.class,"RIGHT"));
 //BA.debugLineNum = 79;BA.debugLine="Dim ou As OutputStream";
_ou = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 80;BA.debugLine="ou = File.OpenOutput(File.DirInternal & \"/gallery\",filename,False)";
_ou = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal()+"/gallery",_filename,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 81;BA.debugLine="c1.Bitmap.WriteToStream(ou,100,\"JPEG\")";
_c1.getBitmap().WriteToStream((java.io.OutputStream)(_ou.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"JPEG"));
 //BA.debugLineNum = 82;BA.debugLine="ou.Close";
_ou.Close();
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
}
