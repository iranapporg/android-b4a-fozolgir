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
Dim cameraEX As CameraExClass
Dim p1 As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
p1.Initialize("")
'Activity.AddView(p1,-100%x,-100%y,-50,-50)
Activity.Color = Colors.Transparent
Activity.AddView(p1,-980,-980,480dip,480dip)
End Sub

Sub camera_Ready (Success As Boolean)
If Success Then
 cameraEX.StartPreview
 cameraEX.TakePicture
End If
End Sub

Sub camera_PictureTaken (Data() As Byte)
Dim ou As OutputStream
Dim filename As String
filename = DateTime.Now & ".jpg"
ou = File.OpenOutput(File.DirInternal & "/gallery",filename,False)
ou.WriteBytes(Data,0,Data.Length)
ou.Close
cameraEX.StopPreview
myLibrary.WatermarkPicture(filename,p1)
Activity.Finish
End Sub

Sub Activity_Resume
cameraEX.Initialize(p1,True,Me,"camera")
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub