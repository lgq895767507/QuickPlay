//
// Created by lewis on 2019-08-26.
//

#ifndef QUICKPLAYER_WANGYIFFMPEG_H
#define QUICKPLAYER_WANGYIFFMPEG_H
#include <pthread.h>
#include <android/native_window.h>
#include "VideoChannel.h"
#include "AudioChannel.h"
#include "BaseChannel.h"


extern "C" {
#include <libavformat/avformat.h>
#include <libavutil/time.h>
}

//控制层
class WangYiFFmpeg{

public:
    WangYiFFmpeg(JavaCallHelper *javaCallHelper, const char *dataSource);
    ~WangYiFFmpeg();

    void prepare();
    void prepareFFmpeg();
    void start();
    void play();
    void setRenderCallback(RenderFrame renderFrame);

private:
    bool isPlaying;
    char *url;
    pthread_t pid_prepare;//销毁
    pthread_t pid_play;//知道播放完毕
    VideoChannel *videoChannel;
    AudioChannel *audioChannel;
    AVFormatContext *formatContext;
    JavaCallHelper *javaCallHelper;
    RenderFrame renderFrame;
};

#endif //QUICKPLAYER_WANGYIFFMPEG_H
