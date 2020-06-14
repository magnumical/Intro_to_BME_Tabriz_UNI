package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_l_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[l_main/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="uni.SetLeftAndRight(58%x,90%X)"[l_main/General script]
views.get("uni").vw.setLeft((int)((58d / 100 * width)));
views.get("uni").vw.setWidth((int)((90d / 100 * width) - ((58d / 100 * width))));
//BA.debugLineNum = 4;BA.debugLine="uni.SetTopAndBottom(10%y,32%y)"[l_main/General script]
views.get("uni").vw.setTop((int)((10d / 100 * height)));
views.get("uni").vw.setHeight((int)((32d / 100 * height) - ((10d / 100 * height))));
//BA.debugLineNum = 6;BA.debugLine="biomed.SetLeftAndRight(10%x,35%x)"[l_main/General script]
views.get("biomed").vw.setLeft((int)((10d / 100 * width)));
views.get("biomed").vw.setWidth((int)((35d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 7;BA.debugLine="biomed.SetTopAndBottom(14%y,31%y)"[l_main/General script]
views.get("biomed").vw.setTop((int)((14d / 100 * height)));
views.get("biomed").vw.setHeight((int)((31d / 100 * height) - ((14d / 100 * height))));
//BA.debugLineNum = 9;BA.debugLine="teachers.SetLeftAndRight(59%x,90%X)"[l_main/General script]
views.get("teachers").vw.setLeft((int)((59d / 100 * width)));
views.get("teachers").vw.setWidth((int)((90d / 100 * width) - ((59d / 100 * width))));
//BA.debugLineNum = 10;BA.debugLine="teachers.SetTopAndBottom(37%y,57%y)"[l_main/General script]
views.get("teachers").vw.setTop((int)((37d / 100 * height)));
views.get("teachers").vw.setHeight((int)((57d / 100 * height) - ((37d / 100 * height))));
//BA.debugLineNum = 12;BA.debugLine="chart.SetLeftAndRight(10%x,37%x)"[l_main/General script]
views.get("chart").vw.setLeft((int)((10d / 100 * width)));
views.get("chart").vw.setWidth((int)((37d / 100 * width) - ((10d / 100 * width))));
//BA.debugLineNum = 13;BA.debugLine="chart.SetTopAndBottom(38%y,55%y)"[l_main/General script]
views.get("chart").vw.setTop((int)((38d / 100 * height)));
views.get("chart").vw.setHeight((int)((55d / 100 * height) - ((38d / 100 * height))));
//BA.debugLineNum = 15;BA.debugLine="info.SetLeftAndRight(37%x,59%X)"[l_main/General script]
views.get("info").vw.setLeft((int)((37d / 100 * width)));
views.get("info").vw.setWidth((int)((59d / 100 * width) - ((37d / 100 * width))));
//BA.debugLineNum = 16;BA.debugLine="info.SetTopAndBottom(60%y,73%y)"[l_main/General script]
views.get("info").vw.setTop((int)((60d / 100 * height)));
views.get("info").vw.setHeight((int)((73d / 100 * height) - ((60d / 100 * height))));
//BA.debugLineNum = 18;BA.debugLine="exitbtn.SetLeftAndRight(37%x,59%x)"[l_main/General script]
views.get("exitbtn").vw.setLeft((int)((37d / 100 * width)));
views.get("exitbtn").vw.setWidth((int)((59d / 100 * width) - ((37d / 100 * width))));
//BA.debugLineNum = 19;BA.debugLine="exitbtn.SetTopAndBottom(87%y,100%y)"[l_main/General script]
views.get("exitbtn").vw.setTop((int)((87d / 100 * height)));
views.get("exitbtn").vw.setHeight((int)((100d / 100 * height) - ((87d / 100 * height))));

}
}