package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class chartdarsi extends Activity implements B4AActivity{
	public static chartdarsi mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.chartdarsi");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (chartdarsi).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.chartdarsi");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.chartdarsi", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (chartdarsi) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (chartdarsi) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return chartdarsi.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (chartdarsi) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (chartdarsi) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.LabelWrapper _title = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scroller = null;
public b4a.example.main _main = null;
public b4a.example.moghaddame _moghaddame = null;
public b4a.example.university _university = null;
public b4a.example.teacher _teacher = null;
public b4a.example.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.sql.SQL _db = null;
anywheresoftware.b4a.sql.SQL.CursorWrapper _cur = null;
String _s = "";
int _i = 0;
 //BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 18;BA.debugLine="Activity.LoadLayout(\"l_chart\")";
mostCurrent._activity.LoadLayout("l_chart",mostCurrent.activityBA);
 //BA.debugLineNum = 20;BA.debugLine="Dim db As SQL";
_db = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 21;BA.debugLine="Dim cur As Cursor";
_cur = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 22;BA.debugLine="File.Copy(File.DirAssets,\"database.db\",File.DirIn";
anywheresoftware.b4a.keywords.Common.File.Copy(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"database.db",anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"database.db");
 //BA.debugLineNum = 24;BA.debugLine="If db.IsInitialized = False Then";
if (_db.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 25;BA.debugLine="db.initialize(File.DirInternal,\"database.db\",Fal";
_db.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"database.db",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 28;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 29;BA.debugLine="cur = db.execquery(\"SELECT * FROM chart\")";
_cur.setObject((android.database.Cursor)(_db.ExecQuery("SELECT * FROM chart")));
 //BA.debugLineNum = 31;BA.debugLine="For i = 0 To cur.RowCount -1";
{
final int step10 = 1;
final int limit10 = (int) (_cur.getRowCount()-1);
for (_i = (int) (0) ; (step10 > 0 && _i <= limit10) || (step10 < 0 && _i >= limit10); _i = ((int)(0 + _i + step10)) ) {
 //BA.debugLineNum = 32;BA.debugLine="cur.Position = i";
_cur.setPosition(_i);
 //BA.debugLineNum = 33;BA.debugLine="If i = cur.RowCount - 1 Then";
if (_i==_cur.getRowCount()-1) { 
 //BA.debugLineNum = 34;BA.debugLine="s = s&cur.GetString(\"Text\")";
_s = _s+_cur.GetString("Text");
 }else {
 //BA.debugLineNum = 36;BA.debugLine="s = s&cur.GetString(\"Text\")&\"<img>ch\"&i&\".png</";
_s = _s+_cur.GetString("Text")+"<img>ch"+BA.NumberToString(_i)+".png</img>";
 };
 }
};
 //BA.debugLineNum = 39;BA.debugLine="ImgToText (scroller,s)";
_imgtotext(mostCurrent._scroller,_s);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 52;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 53;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub backbtn_Click";
 //BA.debugLineNum = 58;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Private title As Label";
mostCurrent._title = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private scroller As ScrollView";
mostCurrent._scroller = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _imgtotext(anywheresoftware.b4a.objects.ScrollViewWrapper _sv,String _str) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
String _s = "";
String _s2 = "";
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 61;BA.debugLine="Sub ImgToText(sv As ScrollView,str As String)";
 //BA.debugLineNum = 62;BA.debugLine="Dim lbl As Label";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 64;BA.debugLine="Dim s,s2 As String";
_s = "";
_s2 = "";
 //BA.debugLineNum = 65;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 66;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 68;BA.debugLine="p = sv.Panel";
_p = _sv.getPanel();
 //BA.debugLineNum = 69;BA.debugLine="p.Height = 0";
_p.setHeight((int) (0));
 //BA.debugLineNum = 71;BA.debugLine="Do While str.Length > 0";
while (_str.length()>0) {
 //BA.debugLineNum = 73;BA.debugLine="If str.StartsWith(\"<img>\") Then";
if (_str.startsWith("<img>")) { 
 //BA.debugLineNum = 74;BA.debugLine="s = \"img\"";
_s = "img";
 }else {
 //BA.debugLineNum = 76;BA.debugLine="s = \"lbl\"";
_s = "lbl";
 };
 //BA.debugLineNum = 79;BA.debugLine="Select s";
switch (BA.switchObjectToInt(_s,"img","lbl")) {
case 0: {
 //BA.debugLineNum = 82;BA.debugLine="s2 = str.SubString2(str.IndexOf(\"<img>\") + 5,s";
_s2 = _str.substring((int) (_str.indexOf("<img>")+5),_str.indexOf("</img>"));
 //BA.debugLineNum = 83;BA.debugLine="str = str.SubString(str.IndexOf(\"</img>\") + 6)";
_str = _str.substring((int) (_str.indexOf("</img>")+6));
 //BA.debugLineNum = 84;BA.debugLine="bmp.Initialize(File.DirAssets,s2)";
_bmp.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_s2);
 //BA.debugLineNum = 85;BA.debugLine="img.Initialize(\"img\")";
_img.Initialize(mostCurrent.activityBA,"img");
 //BA.debugLineNum = 86;BA.debugLine="img.Bitmap = bmp";
_img.setBitmap((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 87;BA.debugLine="p.AddView(img,0,p.Height,bmp.Width,bmp.Height)";
_p.AddView((android.view.View)(_img.getObject()),(int) (0),_p.getHeight(),_bmp.getWidth(),_bmp.getHeight());
 //BA.debugLineNum = 89;BA.debugLine="img.Height = img.Height * (sv.Width - sv.Left)";
_img.setHeight((int) (_img.getHeight()*(_sv.getWidth()-_sv.getLeft())));
 //BA.debugLineNum = 90;BA.debugLine="img.Height = img.Height / img.Width";
_img.setHeight((int) (_img.getHeight()/(double)_img.getWidth()));
 //BA.debugLineNum = 91;BA.debugLine="img.Width = sv.Width - sv.Left";
_img.setWidth((int) (_sv.getWidth()-_sv.getLeft()));
 //BA.debugLineNum = 94;BA.debugLine="img.Gravity = Gravity.CENTER_VERTICAL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 95;BA.debugLine="img.Gravity = Gravity.FILL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 97;BA.debugLine="p.Height = p.Height + img.Height";
_p.setHeight((int) (_p.getHeight()+_img.getHeight()));
 break; }
case 1: {
 //BA.debugLineNum = 99;BA.debugLine="If str.Contains(\"<img>\") Then";
if (_str.contains("<img>")) { 
 //BA.debugLineNum = 100;BA.debugLine="s2 = str.SubString2(0,str.IndexOf(\"<img>\"))";
_s2 = _str.substring((int) (0),_str.indexOf("<img>"));
 //BA.debugLineNum = 101;BA.debugLine="str = str.SubString(str.IndexOf(\"<img>\"))";
_str = _str.substring(_str.indexOf("<img>"));
 //BA.debugLineNum = 102;BA.debugLine="str = str.SubString(str.IndexOf(\"<img>\"))";
_str = _str.substring(_str.indexOf("<img>"));
 }else {
 //BA.debugLineNum = 104;BA.debugLine="s2 = str";
_s2 = _str;
 //BA.debugLineNum = 105;BA.debugLine="str = str.SubString(str.Length)";
_str = _str.substring(_str.length());
 //BA.debugLineNum = 106;BA.debugLine="s = \"\"";
_s = "";
 };
 //BA.debugLineNum = 108;BA.debugLine="lbl.Initialize(\"lbl\")";
_lbl.Initialize(mostCurrent.activityBA,"lbl");
 //BA.debugLineNum = 109;BA.debugLine="lbl.Text = s2";
_lbl.setText(BA.ObjectToCharSequence(_s2));
 //BA.debugLineNum = 110;BA.debugLine="p.AddView(lbl,sv.Left,p.Height,sv.Width - sv.L";
_p.AddView((android.view.View)(_lbl.getObject()),_sv.getLeft(),_p.getHeight(),(int) (_sv.getWidth()-_sv.getLeft()),(int) (0));
 //BA.debugLineNum = 114;BA.debugLine="lbl.TextColor = Colors.White";
_lbl.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 115;BA.debugLine="lbl.Gravity = Gravity.RIGHT";
_lbl.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 116;BA.debugLine="lbl.TextSize=20";
_lbl.setTextSize((float) (20));
 //BA.debugLineNum = 117;BA.debugLine="lbl.Height = su.MeasureMultilineTextHeight(lbl";
_lbl.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl.getObject()),_s2));
 //BA.debugLineNum = 118;BA.debugLine="p.Height = p.Height + lbl.Height";
_p.setHeight((int) (_p.getHeight()+_lbl.getHeight()));
 break; }
}
;
 }
;
 //BA.debugLineNum = 121;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
