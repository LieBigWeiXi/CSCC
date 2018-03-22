package com.example.atlas.cscc20.Activity;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.atlas.cscc20.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends BaseActivity {
    private VideoView mVideoView;
    public static String path = "http://183.215.138.233:8100/vod/2017/12/02/20171202102219_23.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vod_play);
        Vitamio.initialize(this);
        playfunction();
        myOnClick();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null)
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        super.onConfigurationChanged(newConfig);
    }
    /**
     * 加载视频
     */
    void playfunction() {
        String path = PlayActivity.path;
        mVideoView = (VideoView) findViewById(R.id.videoView);
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(PlayActivity.this, "路径错误", Toast.LENGTH_LONG).show();
            return;
        } else {
            mVideoView.setVideoPath(path);
            MediaController mediaController = new MediaController(this,mVideoView,this);
            //添加播放控制条
            mVideoView.setMediaController(mediaController);
            mVideoView.requestFocus();
            //注册在媒体文件加载完毕，可以播放时调用的回调函数
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setPlaybackSpeed(1.0f);

                }
            });
        }
    }

    //设置对VideoView控件的监听
    private void myOnClick(){
        //播放准备好
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog2.dismiss();
                mVideoView.resume();
                mVideoView.start();

            }
        });
        //播放完成
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(PlayActivity.this,"播放结束",Toast.LENGTH_SHORT).show();
            }
        });
        //播放出错
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });
/**
 * 缓冲设置
 *
 */
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            private ProgressBar progressBar = (ProgressBar)findViewById(R.id.prograss_bar);
            private  boolean needResume ;
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    //开始缓存，暂停播放
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (mVideoView.isPlaying()) {
                            mVideoView.pause();
                            progressBar.setVisibility(View.VISIBLE);
                            needResume = true;
                        }
                        break;
                    //缓存完成，恢复播放
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        if(needResume)
                            mVideoView.start();
                        progressBar.setVisibility(View.GONE);
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        break;
                }
                return true;
            }


        });
        mVideoView.setBufferSize(1024);
    }

    //  显示界面后，展示dailog
    ProgressDialog progressDialog2=null;
    @Override
    protected void onResume() {
        super.onResume();
        progressDialog2=new ProgressDialog(PlayActivity.this);
        progressDialog2.setMessage("正在加载视频");
        progressDialog2.setCancelable(true);
        progressDialog2.show();//对话框显示
    }
}

