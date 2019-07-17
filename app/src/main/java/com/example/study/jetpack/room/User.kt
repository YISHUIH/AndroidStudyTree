package com.example.study.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Copyright , 2015-2019,  <br>
 * Author: 陈刘磊 1070379530@qq.com <br>
 * Date: 2019/7/15 16:18    <br>
 * Description: User   <br>
 */

@Entity
class User() {
    @PrimaryKey(autoGenerate = true)
    public var id: Int = 0
    @ColumnInfo(name = "name")
    public var name: String? = null
    @Ignore
    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return "User(id=$id, name='$name')"
    }


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}