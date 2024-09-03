package com.globant.utils;

import com.globant.screens.*;

import java.util.HashMap;
import java.util.Map;

public class ScreenMap {
    private static final Map<String, Class<?>> map = new HashMap<>();

    static {
        ScreenMap.map.put("home", HomeScreen.class);
        ScreenMap.map.put("webview", WebViewScreen.class);
        ScreenMap.map.put("login", LoginAndSignUpScreen.class);
        ScreenMap.map.put("forms", FormsScreen.class);
        ScreenMap.map.put("swipe", SwipeScreen.class);
        ScreenMap.map.put("drag", DragAndDropScreen.class);
    }

    public static Class<?> getClassByA11yId(String accessibilityId) {
        return ScreenMap.map.getOrDefault(accessibilityId.toLowerCase(), HomeScreen.class);
    }
}
