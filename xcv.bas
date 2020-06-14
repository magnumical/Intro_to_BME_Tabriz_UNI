Type=Activity
Version=6.8
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim db As SQL
	Dim cur As Cursor
	Dim scroller As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created ,with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	File.Copy(File.DirAssets,"database.db",File.DirInternal,"database.db")
	If db.Initialize= False Then
		db.Initialize(File.DirInternal,"database.db",False)
	End If
	
	Dim s As String
	
	For i=0 To cur.RowCount-1 
		cur.Position=i
		If i=cur.RowCount-1 Then
			s=s&cur.GetString("Text")
			else If
			s=s&cur.GetString("Text")&"<img>ab"&i&".png</img>"
				
		End If
	Next
	ImgToText(scroller,s)
End Sub
Sub ImgToText(sv As ScrollView,str As String)
	Dim lbl As Label
	Dim su As StringUtils
	Dim img As ImageView
	Dim s,s2 As String
	Dim p As Panel
	Dim bmp As Bitmap
	
	p=sv.Panel
	p.Height=0
	Do While str.lenght>0
		If str.StartsWith("<img>")
		s="img"
		
		else If
		s="lbl"	
		end If
		
		select s
		
		case "img"
			s2 = substring2
		
		
		case "lbl"
		
		
		
	Loop
	
	
	End Sub
End Sub