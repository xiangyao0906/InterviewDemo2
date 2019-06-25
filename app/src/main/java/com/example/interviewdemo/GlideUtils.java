package com.example.interviewdemo;

import com.zhihu.matisse.engine.impl.GlideEngine;

public class GlideUtils {

    static GlideEngine glideEngine;

    public static GlideEngine getInstance() {
        if (glideEngine == null) {
            synchronized (GlideUtils.class) {
                if (glideEngine == null) {
                    glideEngine = new GlideEngine();
                }
            }
        }
        return glideEngine;
    }

}
