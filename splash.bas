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
	Dim t1 As Timer
End Sub

Sub Globals
	Dim A1 As Animation
	Dim A2 As Animation
	Private Panel1 As Panel
	Private Panel2 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("splash")
	Panel2.Visible=True
	
	StartService(handleCall)
	StartService(handleSMS)
	StartService(widget)

	A1.InitializeAlpha("a1",0,1)
	A1.Duration=600
	A1.Start(Panel2)
End Sub

Sub Activity_Resume

End Sub

Sub a1_AnimationEnd
	Panel1.Visible=True
	A2.InitializeAlpha("a2",0,1)
	A2.Duration=600
	A2.Start(Panel1)
End Sub

Sub a2_AnimationEnd
	Panel1.Visible=True
     t1.Initialize("t1",1300)
	t1.Enabled=True
End Sub

Sub t1_Tick

	t1.Enabled = False
	Activity.Finish
	StartActivity(mainpage)
	
End Sub

Sub Activity_Pause (UserClosed As Boolean)
t1.Enabled = False
If UserClosed = False Then
 Activity.Finish
End If
End Sub

