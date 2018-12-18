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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	Private l_title As Label
	Private ScrollView1 As ScrollView
	Private lblexit As Button
	Private currentPicture As ImageView
	Private galleryPath As String = File.DirInternal & "/gallery/"
	Private btndelete As Button
 	Dim files      As List
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmgallery")
files = getFiles

Try
 If files.Size = 0 Then
  btndelete.Visible = False
 End If
Catch
End Try

l_title.Typeface = Typeface.LoadFromAssets("BYekan.ttf")
btndelete.Typeface = Typeface.LoadFromAssets("BYekan.ttf")
lblexit.Typeface=Typeface.LoadFromAssets("icomoon.ttf")
loadImage
End Sub

Sub getFiles As List
Try
 Return File.ListFiles(galleryPath)
Catch
 File.MakeDir(File.DirInternal , "gallery")
 Return Null
End Try
End Sub

Sub loadImage
Dim divid      As Int = 45%x
 Dim sTop       As Int : sTop       = 6dip
 Dim sLeft      As Int : sLeft      = 13dip
 Dim rip        As RippleView
 
 ScrollView1.Panel.RemoveAllViews
 
 Try
	 For i = 0 To files.Size - 1
	   Dim image1 As ImageView
	   image1.Initialize("Icon")
	   ScrollView1.Panel.AddView(image1,sLeft,sTop,divid,divid)
	   rip.Initialize(image1,Colors.RGB(100,221,23),430,True)
	   image1.Gravity = Gravity.FILL
	   image1.Tag =  files.Get(i)
	   image1.SetBackgroundImage(LoadBitmapSample(galleryPath, files.Get(i),45%x,45%x))
	   myLibrary.AnimationView(image1,True,700)
	   DoEvents
	   If (i+1) Mod 2 = 0 Then
	    sLeft = 13dip
	    sTop = sTop + divid + 10dip
	    ScrollView1.Panel.Height = ScrollView1.Panel.Height + image1.Height + 10dip
	   Else
	    sLeft = sLeft + divid + 8dip
	   End If
      Next
 Catch
 
 End Try
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
currentPicture = i1

Dim rs As RSPopupMenu
rs.Initialize("rs",i1)
rs.AddMenuItem(0,1,"مشاهده")
rs.AddMenuItem(1,2,"حذف")
rs.Show
End Sub

Sub rs_MenuItemClick (ItemId As Int) As Boolean
    If (ItemId = 0) Then
     File.Copy(galleryPath,currentPicture.Tag,File.DirDefaultExternal,currentPicture.Tag)
     myLibrary.OpenGallery(currentPicture.Tag)
    Else If (ItemId = 1) Then
     File.Delete(galleryPath,currentPicture.Tag)
	currentPicture.SetBackgroundImage(LoadBitmap(File.DirAssets,"no_image.jpg"))
    End If
    Return False
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
Activity.Finish
StartActivity(mainpage)
myLibrary.SetAnimation("file3","file4")
End Sub

Sub btndelete_Click
If Msgbox2("آیا مایل به حذف همه تصاویر هستید؟","توجه","آری","خیر","",Null)	 = DialogResponse.POSITIVE Then
 Dim l1 As List
 l1.Initialize
 Try
  l1 = File.ListFiles(File.DirInternal & "/gallery")
  For i = 0 To l1.Size - 1
   File.Delete(File.DirInternal & "/gallery",l1.Get(i))
  Next
  loadImage
  ToastMessageShow("همه تصاویر گالری حذف شدند",False)
 Catch
 End Try
End If
End Sub