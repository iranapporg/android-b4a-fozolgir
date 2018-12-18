Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals

End Sub

Sub Globals
Private l_title As Label
Private ScrollView1 As ScrollView
	Private Label1 As Label
	Private lblexit As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmicon")
l_title.Typeface = Typeface.LoadFromAssets("BYekan.ttf")
lblexit.Typeface=Typeface.LoadFromAssets("icomoon.ttf")
myLibrary.GridPanelIcon(ScrollView1)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
If UserClosed = False Then
 Activity.Finish
End If
End Sub

Sub Icon_Click
Dim i1 As ImageView
i1 = Sender
myLibrary.saveIcon(i1.Tag)
myLibrary.AnimationView(i1,True,1700)
CallSub(widget,"changeIcon")
ToastMessageShow("آیکون ویجت صفحه اصلی تغییر یافت",False)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  Activity.Finish
  StartActivity(settings)
  myLibrary.SetAnimation("file3","file4")
  Return True
 End If
End Sub

Sub lblexit_Down
lblexit.TextColor = Colors.RGB(227, 31, 26)
End Sub

Sub lblexit_Up
lblexit.TextColor = Colors.RGB(227, 91, 26)
Activity.Finish
StartActivity(settings)
myLibrary.SetAnimation("file3","file4")
End Sub