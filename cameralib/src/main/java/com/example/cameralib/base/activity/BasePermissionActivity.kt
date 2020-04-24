package com.example.cameralib.base.activity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Date: 2019/8/9 17:16    <br>
 * Description: 权限申请的基类   <br>
 */
@SuppressLint("Registered")
open class BasePermissionActivity : AppCompatActivity() {

    var permissionAllowed=false

    fun onRequestPermissions(permissions: Array<out String>) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val denyList = ArrayList<String>()
            for (permission in permissions) {
                if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                    //说明已经统一不需要申请
                    onPermissionAllow(permission)
                } else {
                    denyList.add(permission)
                }
            }
            if (denyList.isEmpty()) {
                return
            }
            requestPermissions(denyList.toTypedArray(), 5)

        }else{
            //23一下
            onPermissionAllow("ALL_ALLOWED")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (permission in permissions) {
            val grant = grantResults[permissions.indexOf(permission)]
            if (grant == PackageManager.PERMISSION_DENIED) {
                onPermissionDeny(permission, requestCode)
            } else {
                onPermissionAllow(permission, requestCode)
            }
        }

    }

    /**
     * 权限被拒绝
     */
    open fun onPermissionDeny(permission: String, requestCode: Int) {
        permissionAllowed=false
        onPermissionDeny(permission)

        Log.e("permission", "$permission  权限被拒绝")
    }

    /**
     * 权限被允许
     */
    open fun onPermissionAllow(permission: String, requestCode: Int) {
        permissionAllowed=true
        onPermissionAllow(permission)
        Log.e("permission", "$permission  权限被允许")
    }

    /**
     * 权限被拒绝
     */
    open fun onPermissionDeny(permission: String) {
        permissionAllowed=false
        Log.e("permission", "$permission  权限被拒绝")
    }

    /**
     * 权限被允许
     */
    open fun onPermissionAllow(permission: String) {
        permissionAllowed=true
        Log.e("permission", "$permission  权限被允许")
    }
}