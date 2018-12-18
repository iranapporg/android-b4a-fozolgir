Type=Activity
Version=3.82
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals

End Sub

Sub Globals
Private ScrollView1 As ScrollView
Private l_title As Label
Private lblexit As Button
Dim help1() As String
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmhelp")
l_title.Typeface = Typeface.LoadFromAssets("BYekan.ttf")
lblexit.Typeface=Typeface.LoadFromAssets("icomoon.ttf")
help1 = myLibrary.splitHelp

Dim sTop As Int
Dim su As StringUtils
ScrollView1.Panel.Color = Colors.RGB(250,250,250)
For i = 1 To help1.Length - 1
	 Dim p1 As Label
	 
	 p1.Initialize("")

	 p1.Text = help1(i)
	 
	 ScrollView1.Panel.AddView(p1,0dip,sTop,ScrollView1.Width-15,30dip)
	 myLibrary.AnimationView(p1)
	 DoEvents
	 p1.Gravity = Gravity.RIGHT
	 
	 p1.TextColor = Colors.Black
	 
	 p1.Typeface = Typeface.LoadFromAssets("byekan.ttf")
	 
	 If i Mod 2 <> 0 Then
	  p1.TextColor = Colors.RGB(227, 91, 26)
	  p1.TextSize = 21
	 End If
	 
	 p1.Height = su.MeasureMultilineTextHeight(p1,p1.Text)
	 sTop = sTop + p1.Height
Next 
ScrollView1.Panel.Height = sTop
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  Activity.Finish
  StartActivity(mainpage)
  myLibrary.SetAnimation("file3","file4")
  Return True
 End If
End Sub

Sub lblexit_Down
lblexit.TextColor = Colors.RGB(227, 31, 26)
End Sub

Sub lblexit_Up
lblexit.TextColor = Colors.RGB(227, 91, 26)
StartActivity(mainpage)
Activity.Finish
myLibrary.SetAnimation("file3","file4")
End Sub

