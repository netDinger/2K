package com.dingar.twok.core;

import android.app.Activity;

public interface IntentRoute {
    void setData(String data);
    void present(Activity root);
}
