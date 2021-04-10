package com.emon.emoji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

public class MainActivity extends AppCompatActivity {

    ImageButton emoticon;
    EmojiEditText emojiEditText;
    ViewGroup rootView;
    private EmojiPopup emojiPopup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emojiEditText = findViewById(R.id.main_activity_chat_bottom_message_edittext);
        rootView = findViewById(R.id.main_activity_root_view);
        emoticon = findViewById(R.id.main_activity_emoji);

        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).build(emojiEditText);

        emoticon.setOnClickListener(v -> {

            emojiPopup.show();

        });

    }

    @Override
    public void onBackPressed() {

        emojiPopup.dismiss();
    }
}