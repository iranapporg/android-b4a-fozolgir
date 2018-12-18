Type=Service
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: True
#End Region

Sub Process_Globals
Dim r1 As RemoteViews
Dim timer1 As Timer
Dim ph As PhoneEvents
Dim i1 As Int : i1 = 1
Dim preSetting As PreferenceManager
Dim IconName As String
End Sub

Sub Service_Create
r1 = ConfigureHomeWidget("widget","evnWidget",0,"",False)
ph.Initialize("screen")
changeIcon
End Sub

Sub screen_ScreenOn (Intent As Intent)
 If myLibrary.getSettingBoolean("service") = True Then
  timer1.Initialize("t1",1000)
  timer1.Enabled = True
 End If
End Sub

Sub screen_ScreenOff (Intent As Intent)
timer1.Enabled = False
i1 = 1
StopService(cycle)
CancelScheduledService(cycle)
End Sub

Sub t1_Tick
i1 = i1 + 1
If i1 = 10 Then
 timer1.Enabled = False
 If myLibrary.getSettingBoolean("service") = True Then
  StartService(cycle)
 End If
End If
End Sub

Sub evnWidget_RequestUpdate
 r1.UpdateWidget
End Sub

Sub Service_Start (StartingIntent As Intent)
 If r1.HandleWidgetEvents(StartingIntent) Then
  Return
 End If
End Sub

Sub Service_Destroy
StopService("")
End Sub

Sub changeIcon
IconName = preSetting.GetString("widgetIcon")
If IconName.Length > 0 Then
 r1.SetImage("img1",LoadBitmap(File.DirAssets,IconName))
 r1.UpdateWidget
End If
End Sub

Sub img1_Click
timer1.Enabled = False
If IsPaused(cycle) = True Then
 StopService(cycle)
 CancelScheduledService(cycle)
 ToastMessageShow("برنامه غیر فعال شد",False)
Else
 ToastMessageShow("دیگه دیر شده",False)
End If
End Sub