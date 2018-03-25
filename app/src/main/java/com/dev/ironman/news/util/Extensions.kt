package com.dev.ironman.news.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by User on 24.03.2018.
 */
fun String.convertDateToLong() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(this).time

fun Long.convertLongToString() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(this))
