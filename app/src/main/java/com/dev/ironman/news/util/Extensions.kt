package com.dev.ironman.news.util

import java.text.SimpleDateFormat
import java.util.*

fun String.convertDateToLong() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(this).time

fun Long.convertLongToString() = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(this))

