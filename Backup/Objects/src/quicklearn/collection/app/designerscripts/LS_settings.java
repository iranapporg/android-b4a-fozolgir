package quicklearn.collection.app.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_settings{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="pnlpass.HorizontalCenter = 50%x"[settings/General script]
views.get("pnlpass").vw.setLeft((int)((50d / 100 * width) - (views.get("pnlpass").vw.getWidth() / 2)));
//BA.debugLineNum = 4;BA.debugLine="pnlpass.VerticalCenter = 50%y"[settings/General script]
views.get("pnlpass").vw.setTop((int)((50d / 100 * height) - (views.get("pnlpass").vw.getHeight() / 2)));
//BA.debugLineNum = 5;BA.debugLine="container.VerticalCenter=38%y"[settings/General script]
views.get("container").vw.setTop((int)((38d / 100 * height) - (views.get("container").vw.getHeight() / 2)));

}
}