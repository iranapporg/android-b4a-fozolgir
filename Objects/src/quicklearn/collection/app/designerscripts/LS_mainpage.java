package quicklearn.collection.app.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_mainpage{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
views.get("container").vw.setTop((int)((38d / 100 * height) - (views.get("container").vw.getHeight() / 2)));
//BA.debugLineNum = 4;BA.debugLine="btnservice.HorizontalCenter = 50%x"[mainpage/General script]
views.get("btnservice").vw.setLeft((int)((50d / 100 * width) - (views.get("btnservice").vw.getWidth() / 2)));
//BA.debugLineNum = 5;BA.debugLine="lblservice.SetLeftAndRight(0,35%x)"[mainpage/General script]
views.get("lblservice").vw.setLeft((int)(0d));
views.get("lblservice").vw.setWidth((int)((35d / 100 * width) - (0d)));

}
}