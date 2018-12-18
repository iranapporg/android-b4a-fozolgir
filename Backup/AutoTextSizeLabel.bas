Type=Class
Version=3.82
B4A=true
@EndOfDesignText@
'Class module
Sub Class_Globals
	Private cvs As Canvas
	Private mLbl As Label
	Private su As StringUtils
End Sub

Public Sub Initialize (Target As Object, EventName As String)

End Sub



Public Sub setText(value As String)
	mLbl.Text = value
	Dim multipleLines As Boolean = mLbl.Text.Contains(CRLF)
	Dim size As Float
	For size = 2 To 80
		If CheckSize(size, multipleLines) Then Exit
	Next
	size = size - 0.5
	If CheckSize(size, multipleLines) Then size = size - 0.5
	mLbl.TextSize = size
End Sub

'returns true if the size is too large
Private Sub CheckSize(size As Float, MultipleLines As Boolean) As Boolean
	mLbl.TextSize = size
	If MultipleLines Then
		Return su.MeasureMultilineTextHeight(mLbl, mLbl.Text) > mLbl.Height
	Else
		Return cvs.MeasureStringWidth(mLbl.Text, mLbl.Typeface, size) > mLbl.Width OR _
			su.MeasureMultilineTextHeight(mLbl, mLbl.Text) > mLbl.Height
	End If
End Sub

Public Sub getText As String
	Return mLbl.Text
End Sub