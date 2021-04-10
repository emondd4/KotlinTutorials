package com.emon.emoji;

import android.app.Application;

import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        EmojiManager.install(new GoogleEmojiProvider());
    }

}
