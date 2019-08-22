package com.example.study.activity.android.openGl

import android.content.Context
import android.content.Intent
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import com.example.study.BaseActivity
import com.example.study.R
import kotlinx.android.synthetic.main.activity_open_gl.*
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class OpenGlActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, OpenGlActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var triangle: Triangle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_gl)

        setGL()

    }

    private fun setGL() {
        glsv.setEGLContextClientVersion(2)


        val mMVPMatrix = FloatArray(16)
        val mProjectionMatrix = FloatArray(16)
        val mViewMatrix = FloatArray(16)
        val mRotationMatrix = FloatArray(16)
        val scratch = FloatArray(16)
        glsv.setRenderer(object : GLSurfaceView.Renderer {
            override fun onDrawFrame(p0: GL10?) {
                Log.e("OpenGl", "onDrawFrame")
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
                // Set the camera position (View matrix)
                Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

                // Calculate the projection and view transformation
                Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)

                val time = SystemClock.uptimeMillis() % 4000L
                Log.e("time", "time:  $time")
                val angle = 0.090f * time
                Matrix.setRotateM(mRotationMatrix, 0, angle, 0f, 0f, 1f)
                Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0)
                // Draw shape
                triangle.draw(scratch)

            }

            override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {
                Log.e("OpenGl", "onSurfaceChanged")
                GLES20.glViewport(0, 0, p1, p2)
                val ratio = p1.toFloat() / p2
                Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
            }

            override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
                Log.e("OpenGl", "onSurfaceCreated")
                GLES20.glClearColor(0f, 0f, 0f, 0f)
                triangle = Triangle()

            }
        })
    }


    class Triangle {
        //每个顶点坐标的个数
        val COORDS_PER_VERTEX = 3
        val triangleCoords = floatArrayOf(
                // top
                0.0f, 0.5f, 0.2f,
                // bottom left
                -0.5f, -0.5f, 0.8f,
                // bottom right
                0.5f, -0.5f, 0.5f
        )
        val colors = floatArrayOf(0.6f, 0.6f, 0.5f, 1.0f)
        //顶点着色器代码
//        val vertexShaderCode = "attribute vec4 vPosition;" +
//                "void main() {" +
//                "  gl_Position = vPosition;" +
//                "}"

        val vertexShaderCode = // This matrix member variable provides a hook to manipulate
        // the coordinates of the objects that use this vertex shader
                "uniform mat4 uMVPMatrix;" +
                        "attribute vec4 vPosition;" +
                        "void main() {" +
                        // the matrix must be included as a modifier of gl_Position
                        // Note that the uMVPMatrix factor *must be first* in order
                        // for the matrix multiplication product to be correct.
                        "  gl_Position = uMVPMatrix * vPosition;" +
                        "}"


        //片段着色器代码
        val fragmentShaderCode = "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

        //数据转换
        //顶点着色器
        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode)
        //片段着色器
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode)

        // 创建空的OpenGL ES程序
        val mProgram = GLES20.glCreateProgram()
        var vertexBuffer: FloatBuffer

        init {
            val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4)
            bb.order(ByteOrder.nativeOrder())
            vertexBuffer = bb.asFloatBuffer()
            // 将坐标添加到FloatBuffer
            vertexBuffer.put(triangleCoords)
            // 设置缓冲区来读取第一个坐标
            vertexBuffer.position(0)

            // 添加顶点着色器到程序中
            GLES20.glAttachShader(mProgram, vertexShader)

            // 添加片段着色器到程序中
            GLES20.glAttachShader(mProgram, fragmentShader)

            // 创建OpenGL ES程序可执行文件
            GLES20.glLinkProgram(mProgram)
        }


        /**
         * 执行着色器代码
         */
        fun loadShader(type: Int, shaderCode: String): Int {
            // 创造顶点着色器类型(GLES20.GL_VERTEX_SHADER)
            // 或者是片段着色器类型 (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }

        val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
        val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

        fun draw(mvpMatrix: FloatArray) {


            // 将程序添加到OpenGL ES环境
            GLES20.glUseProgram(mProgram)

            // 获取顶点着色器的位置的句柄
            val mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")

            // 启用三角形顶点位置的句柄
            GLES20.glEnableVertexAttribArray(mPositionHandle)


            //准备三角形坐标数据
            GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                    GLES20.GL_FLOAT, false,
                    vertexStride, vertexBuffer)
            // 获取片段着色器的颜色的句柄
            val mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")

            // 设置绘制三角形的颜色
            GLES20.glUniform4fv(mColorHandle, 1, colors, 0)

            // 得到形状的变换矩阵的句柄
            val mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")

            // 将投影和视图转换传递给着色器
            GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0)

            // 绘制三角形
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

            // 禁用顶点数组
            GLES20.glDisableVertexAttribArray(mPositionHandle)
        }


    }


}
