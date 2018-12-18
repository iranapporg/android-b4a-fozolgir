Type=StaticCode
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
Sub Process_Globals
Dim preSetting As PreferenceManager
Dim blnOpen As Boolean
End Sub

Sub FontFamily(Label1 As Label,FontName As String)
If FontName = "" Then
 Label1.Typeface = Typeface.LoadFromAssets("byekan.ttf")
Else
 Label1.Typeface = Typeface.LoadFromAssets(FontName)
End If
End Sub

Sub GetParent(v As View) As View
   Dim r As Reflector
   r.Target = v
   Return r.RunMethod("getParent")
End Sub

Sub getCurrentRunningApp As String
Dim os As OperatingSystem
os.Initialize("")
Private RunList As List = os.getRunningTasks(4)
Private xt As String = "", r1 As Reflector
If RunList.Size > 0 Then
   r1.Target = RunList.get(1)
   xt = r1.getField("topActivity")
   xt = xt.ToLowerCase
   xt = xt.Replace("componentinfo{","")
   xt = xt.SubString2(0,xt.IndexOf("/"))
   Return xt
End If
End Sub

Sub getPackageInf(sPackage As String) As Map
Dim p1 As PackageManager
Dim list1 As List
Dim pk As String
Dim m1 As Map

list1.Initialize
m1.Initialize
list1 = p1.GetInstalledPackages

For k = 0 To list1.Size - 1
 pk = list1.get(k)
 If sPackage.ToLowerCase = pk.ToLowerCase Then
   m1.Put("time",getDate(True) & " ساعت " & DateTime.GetHour(DateTime.Now) & ":" & DateTime.GetMinute(DateTime.Now))
   m1.Put("label",p1.GetApplicationLabel(pk))
   Dim bd As BitmapDrawable
   bd = p1.GetApplicationIcon(pk)
   m1.Put("icon",bd)
  Return m1
 End If
Next
Return Null
End Sub

Sub WatermarkPicture(filename As String,pnl As Panel)
Dim map1 As Map
map1.Initialize
map1 = getPackageInf(getCurrentRunningApp)

Dim c1 As Canvas
Dim dest,bar As Rect
dest.Initialize(5dip,pnl.Height - 40dip,44dip,pnl.Height-7dip)
bar.Initialize(0,pnl.Height - 50dip,pnl.Width,pnl.Height)

pnl.SetBackgroundImage(LoadBitmap(File.DirInternal & "/gallery",filename))

Dim bd As BitmapDrawable
c1.Initialize(pnl)
bd = map1.Get("icon")
c1.DrawRect(bar,Colors.ARGB(100,60,17,92),True,2)
c1.DrawBitmap(bd.Bitmap,Null,dest)
c1.DrawText(map1.Get("label"),51dip,pnl.Height - 25,Typeface.LoadFromAssets("byekan.ttf"),13,Colors.White,"LEFT")
c1.DrawText(map1.Get("time"),80%x,pnl.Height - 30 ,Typeface.LoadFromAssets("byekan.ttf"),15,Colors.White,"RIGHT")

Dim ou As OutputStream
ou = File.OpenOutput(File.DirInternal & "/gallery",filename,False)
c1.Bitmap.WriteToStream(ou,100,"JPEG")
ou.Close
End Sub

Sub getDate(sAdvance As Boolean) As String
Dim d1 As PersianDate
Dim s1(),month As String

If sAdvance = False Then
 Return d1.getDate(0,0,0,"/")
Else
s1 = Regex.Split("/",d1.getDate(0,0,0,"/"))
Select Case s1(1)
 Case 1
  month = "فروردین"
 Case 2
  month = "اردیبهشت"
 Case 3
  month = "خرداد"
 Case 4
  month = "تیر"
 Case 5
  month = "مرداد"
 Case 6
  month = "شهریور"
 Case 7
  month = "مهر"
 Case 8
  month = "آبان"
 Case 9
  month = "آذر"
 Case 10
  month = "دی"
 Case 11
  month = "بهمن"
 Case 12
  month = "اسفند"
End Select
Return ConvertNumbers2Persian(s1(2))  & " " & month
End If
End Sub

Sub ConvertNumbers2Persian(sNumber As String) As String
Dim sNumbers(10) As String
Dim res As String
Dim j As Int

res = sNumber
sNumbers(0) = "٠"
sNumbers(1) = "١"
sNumbers(2) = "٢"
sNumbers(3) = "٣"
sNumbers(4) = "٤"
sNumbers(5) = "٥"
sNumbers(6) = "٦"
sNumbers(7) = "٧"
sNumbers(8) = "٨"
sNumbers(9) = "٩"

For i =0 To sNumber.Length - 1
 j = sNumber.SubString2(i,i+1)
 res = res.Replace(sNumber.CharAt(i),sNumbers(j))
Next

Return res

End Sub

Sub GridPanelIcon(Panel1 As ScrollView)
 Dim gridScreen As Int : gridScreen = Round(100%x / 85dip)
 Dim Diff       As Int : Diff       = ((100%x - gridScreen * 85dip) / gridScreen) - 6
 Dim sTop       As Int : sTop       = 6dip
 Dim sLeft      As Int : sLeft      = 10dip
 Dim rip As RippleView

 For i = 1 To 20
   Dim image1 As ImageView
   image1.Initialize("Icon")
   Panel1.Panel.AddView(image1,sLeft,sTop,60dip,60dip)
   image1.Gravity = Gravity.FILL
   image1.Tag = i & ".png"
   image1.SetBackgroundImage(LoadBitmap(File.DirAssets,i & ".png"))
   AnimationView(image1,True,1700)
   rip.Initialize(image1,Colors.RGB(227, 91, 26),350,True)
   DoEvents
   If i Mod gridScreen = 0 Then
    sLeft = 10dip
    sTop = sTop + 86dip
    Panel1.Panel.Height = Panel1.Panel.Height + 130dip
   Else
    sLeft = sLeft + 85dip + Diff
   End If
 Next
 
End Sub

Sub SetTextShadow(pView As View, pRadius As Float, pDx As Float, pDy As Float, pColor As Int)
   Dim ref As Reflector
   
   ref.Target = pView
   ref.RunMethod4("setShadowLayer", Array As Object(pRadius, pDx, pDy, pColor), Array As String("java.lang.float", "java.lang.float", "java.lang.float", "java.lang.int"))
End Sub

Sub AnimationView(View1 As View,blnPersist As Boolean,duration As Int)
Dim ani As AnimationPlus
ani.InitializeAlpha("ani",0,1.0)
ani.duration = duration
ani.PersistAfter = blnPersist
ani.Start(View1)
End Sub

Sub setBoolean(key As String,val As Boolean)
preSetting.setBoolean(key,val)
End Sub

Sub setString(key As String,val As String)
preSetting.setString(key,val)
End Sub

Sub saveIcon(IconName As String)
setString("widgetIcon",IconName)
End Sub

Sub savePhoneNumber(PhoneNumber As String)
setString("pn",PhoneNumber)
End Sub

Sub saveTime(time As String)
setString("time",time)
End Sub

Sub saveService(state As Boolean)
setBoolean("service",state)
End Sub

Sub getSetting(key As String)
Return preSetting.GetString(key)
End Sub

Sub getSettingBoolean(key As String)
Return preSetting.GetBoolean(key)
End Sub

Sub SetAnimation(InAnimation As String, OutAnimation As String)
    Dim r As Reflector
    Dim package As String
    Dim In, out As Int
    package = r.GetStaticField("anywheresoftware.b4a.BA", "packageName")
    In = r.GetStaticField(package & ".R$anim", InAnimation)
    out = r.GetStaticField(package & ".R$anim", OutAnimation)
    r.Target = r.GetActivity
    r.RunMethod4("overridePendingTransition", Array As Object(In, out), Array As String("java.lang.int", "java.lang.int"))
End Sub

Sub OpenGallery(filename As String)
Dim iIntent As Intent
Dim Image As String

Image = File.Combine(File.DirDefaultExternal, filename)

iIntent.Initialize(iIntent.ACTION_VIEW, "file:///" & Image)

iIntent.SetType("image/jpeg")

StartActivity(iIntent)
End Sub

Sub splitHelp As String()
Dim k1() As String
k1 = Regex.Split("NLine",File.ReadString(File.DirAssets,"guide.txt"))
Return k1
End Sub