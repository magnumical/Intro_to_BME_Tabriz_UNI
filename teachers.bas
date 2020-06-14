Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	
	Private scroller1 As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.LoadLayout ("l_teachers")
	
	
	Dim db1 As SQL
	Dim cur1 As Cursor
	File.Copy(File.DirAssets,"database.db",File.DirInternal,"database.db")
	
	If db1.IsInitialized = False Then
		db1.initialize(File.DirInternal,"database.db",False)
	End If
	
	Dim s As String
	cur1 = db1.execquery("SELECT * FROM teachers")

	For i = 0 To cur1.RowCount -1
		cur1.Position = i
		If i = cur1.RowCount - 1 Then
			s = s&cur1.GetString("Text")
		Else
			s = s&cur1.GetString("Text")&"<img>imp"&i&".png</img>"
		End If
	Next
	ImgToText (scroller1,s)
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
	End If
End Sub



Sub ImgToText(sv As ScrollView,str As String)
	Dim lbl1 As Label
	Dim img1 As ImageView
	Dim s1,s22 As String
	Dim p1 As Panel
	Dim bmp As Bitmap
	Dim su As StringUtils
	p1 = sv.Panel
	p1.Height = 0

	Do While str.Length > 0

		If str.StartsWith("<img>") Then
			s1 = "img"
		Else
			s1 = "lbl"
		End If

		Select s1

			Case "img"
				s22 = str.SubString2(str.IndexOf("<img>") + 5,str.IndexOf("</img>"))
				str = str.SubString(str.IndexOf("</img>") + 6)
				bmp.Initialize(File.DirAssets,s22)
				img1.Initialize("img")
				img1.Bitmap = bmp
				p1.AddView(img1,0,p1.Height,bmp.Width,bmp.Height)
				'	If img.Width > (sv.Width - sv.Left) Then
				img1.Height = img1.Height * (sv.Width - sv.Left)
				img1.Height = img1.Height / img1.Width
				img1.Width = sv.Width - sv.Left
				'	End If
	
				img1.Gravity = Gravity.CENTER_VERTICAL
				img1.Gravity = Gravity.FILL
	
				p1.Height = p1.Height + img1.Height
			Case "lbl"
				If str.Contains("<img>") Then
					s22 = str.SubString2(0,str.IndexOf("<img>"))
					str = str.SubString(str.IndexOf("<img>"))
					str = str.SubString(str.IndexOf("<img>"))
				Else
					s22 = str
					str = str.SubString(str.Length)
					s1 = ""
				End If
				lbl1.Initialize("lbl")
				lbl1.Text = s22
				p1.AddView(lbl1,sv.Left,p1.Height,sv.Width - sv.Left,0)

				'Add Labels Setting

				lbl1.TextColor = Colors.White
				lbl1.Gravity = Gravity.RIGHT
				
				lbl1.Height = su.MeasureMultilineTextHeight(lbl1,s22)
				p1.Height = p1.Height + lbl1.Height
		End Select
	Loop
End Sub