package com.example.study.activity.android.openGl

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.media.*
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.study.BaseActivity
import com.example.study.BuildConfig
import com.example.study.R
import kotlinx.android.synthetic.main.activity_audio_demo.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
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
    lateinit var os: OutputStream
    lateinit var fis: FileInputStream
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
                createFile()
                val buffer = ByteArray(bufferSizeInBytes)
                var read = fis.read(buffer, 0, bufferSizeInBytes)
                while (read != -1) {
                    if (track.playState == AudioTrack.PLAYSTATE_STOPPED) {
                        break
                    }
                    track.write(buffer, 0, read)
                    read = fis.read(buffer, 0, bufferSizeInBytes)
                    Log.e("ssssss", Arrays.toString(buffer))
                }
                fis.close()
            }
        }

        stopRecord.setOnClickListener {
            if (record.state == AudioRecord.STATE_INITIALIZED) {
                record.stop()
                record.release()
            }

        }
        stopTrack.setOnClickListener {
            if (track.state == AudioTrack.STATE_INITIALIZED) {
                track.stop()
                track.release()
            }
        }
    }

    override fun onPermissionAllow(permission: String) {
        super.onPermissionAllow(permission)
        os = createFile()
    }

//    fun createAudioTracj(): AudioTrack {
//        val format = AudioFormat.Builder()
//                .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
//                .setSampleRate(44100)
//                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
//                .build()
//        return AudioTrack.Builder().setAudioFormat(format).build()
//    }

    fun startRecord() {
        record = createAudioRecord()
        isRecord = true
        record.startRecording()
        val buffer = ByteArray(bufferSizeInBytes)

        while (isRecord && record.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            val s = record.read(buffer, 0, bufferSizeInBytes)
            if (s > 0) {
                os.write(buffer, 0, s)
            }
        }

        os.close()

    }

    private fun createFile(): OutputStream {
        val uri = MediaStore.Files.getContentUri("external")
        var fileUri: Uri
        val values = ContentValues()
        values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, "dd.pcm")
        values.put(MediaStore.Files.FileColumns.TITLE, "dd.pcm")
        values.put(MediaStore.Files.FileColumns.MIME_TYPE, "NA")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Files.FileColumns.RELATIVE_PATH, "Download/tt")
            val cursor = contentResolver.query(uri, arrayOf(MediaStore.Files.FileColumns.DISPLAY_NAME, MediaStore.Files.FileColumns._ID)
                    , "${MediaStore.Files.FileColumns.DISPLAY_NAME} =?", arrayOf("dd.pcm"), null)
            fileUri = if (!cursor.moveToNext()) {
                contentResolver.insert(uri, values)
            } else {
                val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID))
                ContentUris.appendId(uri.buildUpon(), id).build()
            }

            fis = FileInputStream(contentResolver.openFileDescriptor(fileUri, "r").fileDescriptor)
            return contentResolver.openOutputStream(fileUri)
        } else {
            val dirs = File("/storage/emulated/0", "/Test/")
            if (!dirs.exists()) {
                dirs.mkdirs()
            }

            val file = File(dirs, "dd.pcm")
            if (!file.exists()) {
                file.createNewFile()
            }
            fis = FileInputStream(file)
            return FileOutputStream(file, false)
        }
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
