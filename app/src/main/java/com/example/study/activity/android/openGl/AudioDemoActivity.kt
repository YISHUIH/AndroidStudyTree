package com.example.study.activity.android.openGl

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.*
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.example.study.BaseActivity
import com.example.study.R
import com.example.study.util.LogUtil
import kotlinx.android.synthetic.main.activity_audio_demo.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.SequenceInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

class AudioDemoActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, AudioDemoActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var record: AudioRecord
    var isRecord: Boolean = false
    lateinit var fileUri: Uri
    private lateinit var track: AudioTrack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_demo)
        onRequestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE))

        audioRecord.setOnClickListener {

            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                startRecord()
            }

        }
        audioTrack.setOnClickListener {
            track = createAudioTrack()
            track.play()
            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                val fis = contentResolver.openInputStream(fileUri)
                val buffer = ByteArray(bufferSizeInBytes)
                var read = fis.read(buffer, 0, bufferSizeInBytes)
                while (read != -1) {
                    if (track.playState == AudioTrack.PLAYSTATE_STOPPED) {
                        break
                    }
                    track.write(buffer, 0, read)
                    read = fis.read(buffer, 0, bufferSizeInBytes)
                }
                fis.close()
            }
        }

        stopRecord.setOnClickListener {
            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                stopRecord()
            }

        }
        stopTrack.setOnClickListener {
            if (track.state == AudioTrack.STATE_INITIALIZED) {
                track.stop()
                track.release()
            }
        }
    }
//
//    /**
//     * byte数组转换为二进制字符串,每个字节以","隔开
//     **/
//
//
//    fun bdsad(b: ByteArray): String {
//        val result = StringBuffer()
//        for (i in b) {
//            result.append("${i & xff}")
//        }
//
//        return result.toString().substring(0, result.length() - 1)
//    }

    private fun stopRecord() {
        if (record.state == AudioRecord.STATE_INITIALIZED) {
            record.stop()
            record.release()
            val wavUri = createFile("AudioTest", "test.wav")
            val wavOS = contentResolver.openOutputStream(wavUri)
            val fileIs = contentResolver.openInputStream(fileUri)
            val wh = WavHeader(44100, 1, 16)
            wh.mSubChunk2Size = fileIs.available()
            wavOS.write(wh.header)
            val bytes = ByteArray(bufferSizeInBytes)
            var read = fileIs.read(bytes, 0, bufferSizeInBytes)
            while (read > 0) {
                wavOS.write(bytes, 0, read)
                read = fileIs.read(bytes, 0, bufferSizeInBytes)
            }
            wavOS.close()
            fileIs.close()
            fileIs.close()
        }
    }

    fun byteArrayToShort(b: ByteArray): ByteBuffer {
        return ByteBuffer.wrap(b).order(ByteOrder.LITTLE_ENDIAN)
    }

    override fun onPermissionAllow(permission: String) {
        super.onPermissionAllow(permission)
        fileUri = createFile("AudioTest", "test.pcm")
    }


    fun startRecord() {
        record = createAudioRecord()
        isRecord = true
        record.startRecording()
        val buffer = ByteArray(bufferSizeInBytes)
        val os = contentResolver.openOutputStream(fileUri)
        while (isRecord && record.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            val s = record.read(buffer, 0, bufferSizeInBytes)
            if (s > 0) {
                os.write(buffer, 0, s)
            }
        }

        os.close()

        stopRecord()
    }



    //音频源，选择麦克风
    val audioSource = MediaRecorder.AudioSource.MIC
    //指定采样率 （MediaRecoder 的采样率通常是8000Hz AAC的通常是44100Hz。 设置采样率为44100，目前为常用的采样率，官方文档表示这个值可以兼容所有的设置）
    val sampleRateInHz = 44100
    //指定捕获音频的声道数目。在AudioFormat类中指定用于此的常量
    val channelConfig = AudioFormat.CHANNEL_IN_MONO //单声道
    //指定音频量化位数 ,在AudioFormaat类中指定了以下各种可能的常量。通常我们选择ENCODING_PCM_16BIT和ENCODING_PCM_8BIT PCM代表的是脉冲编码调制，它实际上是原始音频样本。
    //因此可以设置每个样本的分辨率为16位或者8位，16位将占用更多的空间和处理能力,表示的音频也更加接近真实。
    val audioFormat = AudioFormat.ENCODING_PCM_16BIT

    private val bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat)//计算最小缓冲区
    fun createAudioRecord(): AudioRecord {

        return AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes)
    }

    fun createAudioTrack(): AudioTrack {

        val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        val format = AudioFormat.Builder()
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .setSampleRate(44100)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .build()
        val mode = AudioTrack.MODE_STREAM
        val seesionid = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        return AudioTrack(attributes, format, bufferSizeInBytes, mode, seesionid.generateAudioSessionId())

    }
}
