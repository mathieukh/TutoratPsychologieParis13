package com.mathieukh.tutopsychop13.data.entities

import java.util.*

/*
* Data classe News qui retiendra un id, sa date de publication, son message et une possible u
 */
data class News(val id: String, val published: Date, val message: String, val urlImage: String)
