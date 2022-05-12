/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.afs.thinker.main.business;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.afs.thinker.R;

import java.io.IOException;
import java.io.InputStream;


public class LoginActivityV3 extends AppCompatActivity {

    public static String TAG = "MediaPlayActivity==========";

    private Activity mActivity;
    private MediaPlayer mMediaPlayer;
    boolean mIsInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_login_v3);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!mIsInit) {
            mIsInit = true;
            try {
                InputStream inputStream = getAssets().open("img.png");
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView iv = findViewById(R.id.iv_img);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                SurfaceView surfaceView = findViewById(R.id.surface_view);
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                AssetFileDescriptor file = AssetFileUtil.getRawResFile(this, R.raw.base_login_bg, "base_login_bg");
                AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.base_login_bg);
                mMediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                mMediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mMediaPlayer.setLooping(true);
                SurfaceHolder holder = surfaceView.getHolder();
                holder.addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.setDisplay(holder);
                            mMediaPlayer.start();
                        }
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.pause();
                        }
                    }
                });
                holder.setKeepScreenOn(true);
                mMediaPlayer.setDisplay(holder);
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepare();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

}
