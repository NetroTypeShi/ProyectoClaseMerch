package com.example.ejercicioclase.data

import android.media.Image
import androidx.annotation.DrawableRes

data class Product(@param:DrawableRes val image : Int, val name : String, val price : String, val category : Category, val id : Int)
