package com.example.quickplayer;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Data: 2019-08-26
 * author: lewis
 */
class QuickPlayer implements SurfaceHolder.Callback {

    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private native void native_prepare(String dataSource);
    private native void native_start();
    private native void native_set_surface(Surface surface);

    private String dataSource;
    private SurfaceHolder surfaceHolder;
    public void setSurfaceView(SurfaceView surfaceView) {
        if (null != this.surfaceHolder) {
            this.surfaceHolder.removeCallback(this);
        }
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceHolder.addCallback(this);
    }

    public void prepare() {
        native_prepare(dataSource);
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    //设置回调接口
    private OnPrepareListener onPrepareListener;
    private OnErrorListener onErrorListener;
    private OnProgressListener onProgressListener;
    public void setOnPrepareListener(OnPrepareListener onPrepareListener) {
        this.onPrepareListener = onPrepareListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }
    //    不断调用
    public void onProgress(int progress) {
        if (null != onProgressListener) {
            onProgressListener.onProgress(progress);
        }

    }
    public void onPrepare() {
        if (null != onPrepareListener) {
            onPrepareListener.onPrepared();
        }

    }
    //
    public void onError(int errorCode) {
        if (null != onErrorListener) {
            onErrorListener.onError(errorCode);
        }

    }

    public void start() {
        native_start();
    }

    public interface OnPrepareListener {
        void onPrepared();
    }

    public interface OnErrorListener {
        void onError(int error);
    }

    public interface OnProgressListener {
        void onProgress(int progress);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        native_set_surface(surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


}
