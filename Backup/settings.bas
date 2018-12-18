Type=Activity
Version=3.82
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
	Private pnloverlay As Panel
	Dim IME As IME
	Private lblmsg As Label
	Private txtval As EditText
	Dim blnSetting As Boolean
	Private btnverify As Button
	Private lblexit As Button
	Private changelogTime,changelogPass As String
	Dim blnPass As Boolean
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("settings")
	l_title.Typeface=font_yekan
	l_icon1.Typeface=font_icon
	l_firstitle1.Typeface=font_yekan
	l_secondtitle1.Typeface=font_yekan
	l_icon2.Typeface=font_icon
	l_firsttitle2.Typeface=font_yekan
	l_secondtitle2.Typeface=font_yekan
	l_icon3.Typeface=font_icon
	l_firsttitle3.Typeface=font_yekan
	l_secondtitle3.Typeface=font_yekan
	txtval.Typeface=font_yekan
	btnverify.Typeface=font_yekan
	lblexit.Typeface=font_icon
	txtval.Color = Colors.RGB(236, 235, 235)
	IME.Initialize("")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btnicon_Click
 StartActivity(icon)
 myLibrary.SetAnimation("file2","file1")
End Sub

Sub btnpass_Click
	blnSetting = False
	txtval.Hint = "رمز عبور"

	If myLibrary.getSetting("pn") <> "" Then
	 txtval.Text = myLibrary.getSetting("pn")
	 changelogPass = txtval.Text
	Else
	 txtval.Text = "**98"
	 changelogPass = "**98"
	End If

	lblmsg.Text = "با رمز بالا ميتوانيد وارد محيط برنامه شويد. در صورت تغيير رمز حتما آنرا به خاطر بسپاريد، در صورت فراموشي به هيچ عنوان نميتوانيد وارد برنامه شويد"
	pnloverlay.Visible = True
	txtval.RequestFocus
	txtval.SelectAll
	IME.ShowKeyboard(txtval)
	blnPass = True
End Sub

Sub btntime_Click
	blnSetting = True
	txtval.Hint = "فاصله زمانی"

	If myLibrary.getSetting("time") <> "" Then
	 txtval.Text = myLibrary.getSetting("time")
	 changelogTime = txtval.Text
	Else
	 txtval.Text = 1
	 changelogTime = 1
	End If

	lblmsg.Text = "زمانی را به عنوان فاصله بین عکس گرفتن وارد کنید. مثلا اگر 5 را بنویسید برنامه هر 5 دقیقه یکبار از فضول گوشیتون عکس میگیرد"
	pnloverlay.Visible = True
	txtval.RequestFocus
	txtval.SelectAll
	IME.ShowKeyboard(txtval)
	blnPass = False
End Sub

Sub btnverify_Click
	If blnSetting = False Then
	 changePassword
	Else
	 changeTime
	End If
	pnloverlay.Visible = False
	txtval.Text = ""
	IME.HideKeyboard
End Sub

Sub changeTime

	If IsNumber(txtval.Text) = False Then
	 ToastMessageShow("خطا: لطفا فاصله زمانی را به دقیقه و درست وارد کنید",False)
	 Return
	End If

	myLibrary.saveTime(txtval.Text)
	ToastMessageShow("فاصله زمانی تعین شده تغیر یافت",False)

End Sub

Sub changePassword

	If txtval.Text.Length < 1 Then
	 ToastMessageShow("خطا: لطفا رمز عبور را وارد کنید",False)
	 Return
	End If

	myLibrary.savePhoneNumber(txtval.Text)
	ToastMessageShow("رمز عبور اجرای برنامه با موفقیت تغییر داده شد",False)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	 If KeyCode = KeyCodes.KEYCODE_BACK Then
	  If pnloverlay.Visible = True Then
	   checkChange
	  End If
	  Activity.Finish
	  StartActivity(mainpage)
	  myLibrary.SetAnimation("file3","file4")
	  Return True
	 End If
End Sub

Sub checkChange
	If blnPass = True Then
	 If changelogPass <> txtval.Text Then
	  If Msgbox2("آیا رمز عبور جدید ذخیره شود؟","توجه","آری","خیر","",Null) = DialogResponse.POSITIVE Then
	   changePassword
	  End If
	 End If
	Else If blnPass = False Then
	 If changelogTime <> txtval.Text Then
	  If Msgbox2("آیا فاصله زمانی جدید ذخیره شود؟","توجه","آری","خیر","",Null) = DialogResponse.POSITIVE Then
	   changeTime
	  End If
	 End If
	End If
End Sub

Sub lblexit_Down
	lblexit.TextColor = Colors.RGB(227, 31, 26)
End Sub

Sub lblexit_Up
	lblexit.TextColor = Colors.RGB(227, 91, 26)
	Activity.Finish
	StartActivity(mainpage)
	myLibrary.SetAnimation("file3","file4")
End Sub

Sub pnloverlay_Click
	pnloverlay.Visible = False
	IME.HideKeyboard
End Sub