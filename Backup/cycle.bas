Type=Service
Version=3.82
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
#End Region

Sub Process_Globals
End Sub

Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)
Dim time As String
time = myLibrary.getSetting("time")
If time.Length = 0 Then
 time = 1
End If
StartActivity(cameras)
StartServiceAt("",DateTime.Now + time * 10000,True)
'StartServiceAt("",DateTime.Now + 10 * 1000,True)
End Sub

Sub Service_Destroy

End Sub
