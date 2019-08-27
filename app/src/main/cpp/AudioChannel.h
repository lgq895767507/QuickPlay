//
// Created by lewis on 2019-08-26.
//

#ifndef QUICKPLAYER_AUDIOCHANNEL_H
#define QUICKPLAYER_AUDIOCHANNEL_H

#include <SLES/OpenSLES_Android.h>
#include "BaseChannel.h"
extern "C" {
#include <libswresample/swresample.h>
}

class AudioChannel :public BaseChannel {

public:
    AudioChannel(int id, JavaCallHelper *javaCallHelper, AVCodecContext *avCodecContext,AVRational time_base);

    virtual void play();

    virtual void stop();

    void initOpenSL();
    void decode();
    int getPcm();
private:
    pthread_t pid_audio_play;
    pthread_t pid_audio_decode;
    SwrContext *swr_ctx = NULL;
    int out_channels;
    int out_samplesize;
    int out_sample_rate;
public:
    uint8_t *buffer;
};

#endif //QUICKPLAYER_AUDIOCHANNEL_H
