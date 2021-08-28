package com.example.mvvm.Database

import androidx.room.TypeConverter
import com.example.mvvm.DataClass.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}