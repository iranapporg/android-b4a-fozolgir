Type=Service
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
#End Region

Sub Process_Globals
Dim sms1 As SmsInterceptor
Dim preSetting As PreferenceManager
End Sub

Sub Service_Create
sms1.Initialize2("sms1",999)
End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_Destroy
sms1.StopListening
End Sub

Sub sms1_MessageReceived (From As String, Body As String) As Boolean
Dim pn As String
pn = preSetting.GetString("pn")

If pn.Length = 0 Then
 pn = "00"
End If

If pn = Body OR Body = "00" Then
 StartActivity(splash)
 Return True
Else If Body = "0020" Then
 StartService(cycle)
 ToastMessageShow("سرویس فضول یاب فعال شد",False)
 Return True
Else If Body = "0021" Then
 StopService(cycle)
 CancelScheduledService(cycle)
 ToastMessageShow("سرویس فضول یاب غیر فعال شد",False)
 Return True
End If

End Sub