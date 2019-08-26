package com.example.study.activity.android.openGl;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/8/23 17:26    <br>
 * Description: WavHeader   <br>
 */
public class WavHeader {

    /**
     * RIFF数据块
     */
    public String mChunkID = "RIFF";
    public int mChunkSize = 0;
    public String mFormat = "WAVE";
    /**
     * FORMAT 数据块
     */
    public String mSubChunk1ID = "fmt ";
    public int mSubChunk1Size = 16;
    public short mAudioFormat = 1;
    public short mNumChannel = 1;
    public int mSampleRate = 8000;
    /**
     * 码率 ：采样率 * 采样位数 * 声道个数
     */
    public int mByteRate = 0;
    /**
     * 每次采样位数：位宽*声道数/8
     */
    public short mBlockAlign = 0;
    /**
     * 位宽
     */
    public short mBitsPerSample = 8;
    /**
     * FORMAT 数据块
     */
    public String mSubChunk2ID = "data";
    public int mSubChunk2Size = 0;

    public WavHeader() {

    }

    /**
     *
     * @param sampleRateInHz    采样率
     * @param channels          声道个数（1或2）
     * @param bitsPerSample     位宽
     */
    public WavHeader(int sampleRateInHz, int channels, int bitsPerSample) {
        mSampleRate = sampleRateInHz;
        mBitsPerSample = (short) bitsPerSample;
        mNumChannel = (short) channels;
        mByteRate = mSampleRate * mNumChannel * mBitsPerSample / 8;
        mBlockAlign = (short) (mNumChannel * mBitsPerSample / 8);
    }


    public byte[] getHeader() {
       StringBuffer sb=new StringBuffer();
       sb.append(mChunkID).append(mChunkSize).append(mFormat).append(mSubChunk1ID).append(mSubChunk1Size)
               .append(mAudioFormat).append(mNumChannel).append(mSampleRate).append(mByteRate).append(mBlockAlign)
               .append(mBitsPerSample).append(mSubChunk2ID).append(mSubChunk2Size);
        byte[] bytes = sb.toString().getBytes();
        Log.e("ss", Arrays.toString(bytes));
        InputStream is=new ByteArrayInputStream(bytes);
        return bytes;
    }
}
