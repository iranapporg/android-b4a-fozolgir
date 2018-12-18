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
Private Panel1 As Panel
Private l_title As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmpad")
l_title.Typeface = Typeface.LoadFromAssets("BYekan.ttf")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub lblnumber_Click
Dim s1 As Label
s1 = Sender
s1.Color = Colors.RGB(41,98,255)
End Sub