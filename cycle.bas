Type=Service
Version=4
ModulesStructureVersion=1
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

If IsPaused(Main) AND IsPaused(splash) AND IsPaused(mainpage) AND IsPaused(settings) AND IsPaused(icon) AND IsPaused(gallery) AND IsPaused(help) Then
 StartActivity(cameras)
'StartServiceAt("",DateTime.Now + (time * 60) * 1000,True)
End If
 
 If myLibrary.getSettingBoolean("service") = False Then
  StopService("")
  CancelScheduledService("")
  Return
 End If
 
 StartServiceAt("",DateTime.Now + (time * 60) * 1000,True)
 
End Sub

Sub Service_Destroy

End Sub
