Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Dim font_yekan As Typeface
	font_yekan=Typeface.LoadFromAssets("BYekan.ttf")
	Dim font_icon As Typeface
	font_icon=Typeface.LoadFromAssets("icomoon.ttf")
	Private l_title As Label
	Private l_icon1 As Label
	Private l_firstitle1 As Label
	Private l_secondtitle1 As Label
	Private l_firsttitle2 As Label
	Private l_secondtitle2 As Label
	Private l_icon2 As Label
	Private l_firsttitle3 As Label
	Private l_icon3 As Label
	Private l_secondtitle3 As Label
	Private lblexit As Button
	Private btnservice As ImageView
	Private lblservice As Label
	Dim a1 As RippleView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout("mainpage")
	l_title.Typeface=font_yekan
	l_icon1.Typeface=font_icon
	lblexit.Typeface=font_icon
	l_firstitle1.Typeface=font_yekan
	l_secondtitle1.Typeface=font_yekan
	l_icon2.Typeface=font_icon
	l_firsttitle2.Typeface=font_yekan
	l_secondtitle2.Typeface=font_yekan
	l_icon3.Typeface=font_icon
	l_firsttitle3.Typeface=font_yekan
	l_secondtitle3.Typeface=font_yekan
	lblservice.Typeface=font_yekan
	
	If IsPaused(cycle) = True Then
	 btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,"run.png"))
	 lblservice.Text = "متوقف شد"
	Else
	 btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,"stop.png"))
	 lblservice.Text = "در حال اجرا"
	End If
	
	a1.Initialize(lblservice,Colors.Red,9000,True)
	myLibrary.AnimationView(lblservice,False,800)

	StartService(handleCall)
	StartService(handleSMS)

End Sub

Sub ani_AnimationEnd
lblservice.Visible = False	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
If UserClosed = False Then
 Activity.Finish
End If
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  If Msgbox2("آیا مایل به خروج هستید؟","خروج","آری","خیر","",Null) = DialogResponse.POSITIVE Then
   Activity.Finish
  End If
  Return True
 Else If KeyCode = KeyCodes.KEYCODE_HOME Then
  Return True
 End If
End Sub

Sub btnsetting_Click
StartActivity(settings)
myLibrary.SetAnimation("file2","file1")
End Sub

Sub btnpic_Click
StartActivity(gallery)
myLibrary.SetAnimation("file2","file1")
End Sub

Sub lblexit_Down

End Sub

Sub lblexit_Click
lblexit.TextColor = Colors.RGB(227, 31, 26)
If Msgbox2("آیا مایل به خروج هستید؟","خروج","آری","خیر","",Null) = DialogResponse.POSITIVE Then
 StopService(cycle)
 Activity.Finish
End If
lblexit.TextColor = Colors.RGB(227, 91, 26)
End Sub

Sub btnhelp_Click
StartActivity(help)
myLibrary.SetAnimation("file2","file1")
End Sub

Sub btnservice_Click
If IsPaused(cycle) = True Then
 btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,"stop.png"))
 lblservice.Text = "در حال اجرا"
 myLibrary.saveService(True)
 StartService(cycle)
Else
 btnservice.SetBackgroundImage(LoadBitmap(File.DirAssets,"run.png"))
 lblservice.Text = "متوقف شد"
 myLibrary.saveService(False)
 StopService(cycle)
 CancelScheduledService(cycle)
End If
lblservice.Visible = True
myLibrary.AnimationView(lblservice,False,800)
End Sub