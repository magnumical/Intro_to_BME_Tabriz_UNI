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
	Private title As Label
	Private scroller As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.LoadLayout ("l_moghaddame")
	
	Dim db As SQL
	Dim cur As Cursor
	File.Copy(File.DirAssets,"database.db",File.DirInternal,"database.db")
	
	If db.IsInitialized = False Then
		db.initialize(File.DirInternal,"database.db",False)
	End If
	
	Dim s As String
	cur = db.execquery("SELECT * FROM biom")

	For i = 0 To cur.RowCount -1
		cur.Position = i
		If i = cur.RowCount - 1 Then
			s = s&cur.GetString("Text")
		Else
			s = s&cur.GetString("Text")&"<img>ab"&i&".png</img>"
		End If
	Next
	ImgToText (scroller,s)
	
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

Sub backbtn_Click
	Activity.Finish
End Sub

Sub ImgToText(sv As ScrollView,str As String)
	Dim lbl As Label
	Dim img As ImageView
	Dim s,s2 As String
	Dim p As Panel
	Dim bmp As Bitmap
	Dim su As StringUtils
	p = sv.Panel
	p.Height = 0

	Do While str.Length > 0

		If str.StartsWith("<img>") Then
			s = "img"
		Else
			s = "lbl"
		End If

		Select s

			Case "img"
				s2 = str.SubString2(str.IndexOf("<img>") + 5,str.IndexOf("</img>"))
				str = str.SubString(str.IndexOf("</img>") + 6)
				bmp.Initialize(File.DirAssets,s2)
				img.Initialize("img")
				img.Bitmap = bmp
				p.AddView(img,0,p.Height,bmp.Width,bmp.Height)
				'	If img.Width > (sv.Width - sv.Left) Then
				img.Height = img.Height * (sv.Width - sv.Left)
				img.Height = img.Height / img.Width
				img.Width = sv.Width - sv.Left
				'	End If
	
				img.Gravity = Gravity.CENTER_VERTICAL
				img.Gravity = Gravity.FILL
	
				p.Height = p.Height + img.Height
			Case "lbl"
				If str.Contains("<img>") Then
					s2 = str.SubString2(0,str.IndexOf("<img>"))
					str = str.SubString(str.IndexOf("<img>"))
					str = str.SubString(str.IndexOf("<img>"))
				Else
					s2 = str
					str = str.SubString(str.Length)
					s = ""
				End If
				lbl.Initialize("lbl")
				lbl.Text = s2
				p.AddView(lbl,sv.Left,p.Height,sv.Width - sv.Left,0)

				'Add Labels Setting

				lbl.TextColor = Colors.White
				lbl.Gravity = Gravity.RIGHT
				lbl.TextSize=20
				
				lbl.Height = su.MeasureMultilineTextHeight(lbl,s2)
				p.Height = p.Height + lbl.Height
		End Select
	Loop
End Sub