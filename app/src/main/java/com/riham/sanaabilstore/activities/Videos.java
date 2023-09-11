package com.riham.sanaabilstore.activities;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

import com.riham.sanaabilstore.R;
import com.riham.sanaabilstore.databinding.ActivityVideosBinding;
public class Videos extends DrawerBase {

    ActivityVideosBinding activityVideosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityVideosBinding = ActivityVideosBinding.inflate(getLayoutInflater());
        setContentView(activityVideosBinding.getRoot());
        allocateActivityTitle("Videos");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonPlayVideo2 = (Button)findViewById(R.id.button1);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        VideoView mVideoView2 = (VideoView)findViewById(R.id.videoView1);

        String uriPath2 = "android.resource://com.riham.sanaabilstore/"+R.raw.sports;
        Uri uri2 = Uri.parse(uriPath2);
        mVideoView2.setVideoURI(uri2);
        mVideoView2.requestFocus();
        mVideoView2.start();

        buttonPlayVideo2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoView mVideoView2 = (VideoView) findViewById(R.id.videoView1);
                // VideoView mVideoView = new VideoView(this);
                String uriPath = "android.resource://com.riham.sanaabilstore/" + R.raw.sports;
                Uri uri2 = Uri.parse(uriPath);
                mVideoView2.setVideoURI(uri2);
                mVideoView2.requestFocus();
                mVideoView2.start();
            }
        });
    }

}